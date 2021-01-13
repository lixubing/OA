package com.edu.oa.service.impl;

import com.edu.oa.mdo.LeaveInfoDo;
import com.edu.oa.mdo.ProcessInstDo;
import com.edu.oa.service.IDealProcessService;
import com.edu.oa.service.IProcessService;
import com.edu.oa.service.IStartProcessService;
import com.edu.oa.util.CommonInfo;
import com.edu.oa.util.Constant;
import com.edu.oa.util.SwapAreaUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 执行流程
 */
@Service
public class DealProcessServiceImpl implements IDealProcessService {
    @Resource
    private IStartProcessService startProcessService;
    @Resource
    private IProcessService processService;

    /**
     * 执行流程-审批同意-审批拒绝
     * @param description
     * @param decision
     * @param processInstId
     */
    public void dealProcess(String description, String decision, String processInstId){
        //1.获取当前流程实例
        ProcessInstDo processInstDo = getProcessInst(processInstId);
        SwapAreaUtils.getCommonInfo().setFirstUser(processInstDo.getOwnerId());
        startProcessService.getTemplateInfo(processInstDo.getTplNo());
        //2.查找下级操作员
        String avyId = processInstDo.getAvyId();
        int i = Integer.parseInt(avyId) + 1;

        processInstDo.setAvyId(Integer.toString(i));
        i++;
        System.out.println("下一步 i= " + i);
        List<String> nextExecutorList = processService.getNextExecutorList(Integer.toString(i));
        System.out.println("下级执行人列表 = " + nextExecutorList);
        //3.更新流程实例数据

        processInstDo.setExecutorId(SwapAreaUtils.getCommonInfo().getCurrentUserId());
        System.out.println("当前执行人 = " + SwapAreaUtils.getCommonInfo().getCurrentUserId());
        processInstDo.setDecision(decision);
        processInstDo.setDecisionDesc(description);
        //5.删除当前操流程实例当前步骤待办
        processService.deleteTodoAvyInf(processInstDo);
        //6.生成当前执行人的历史数据
        processService.makeHistAvy(processInstDo);
        //如果审核通过
        if (Constant.decision_1.equals(decision)){
            //4.如果有下级操作员，生成下级操作员的可退回待办和当前操作员的可回收代办
            if (nextExecutorList != null && nextExecutorList.size() != 0){
                processService.makeTodoAvy(nextExecutorList, processInstDo);
            }
            //7.更新流程实例
            processService.updateProcessInfo(processInstDo);
        } else if(Constant.decision_0.equals(decision)) {
            //7.更新流程实例
            processInstDo.setProcessTpcd(Constant.processTPCD_14);
            processInstDo.updateProcessInstByProcessInstId();
            //删除本实例影响课程的老师实例关系表
            LeaveInfoDo leaveInfoDo = new LeaveInfoDo();
            leaveInfoDo.setProcessInstId(processInstDo.getProcessInstId());
            leaveInfoDo.deleteTeacherLeave();
        } else {
            throw new RuntimeException("审核标志错误：decision = " + decision);
        }

    }

    /**
     * 获取当前流程实例
     * @param processInstId 流程实例id
     * @return
     */
    public ProcessInstDo getProcessInst(String processInstId){
        CommonInfo commonInfo = SwapAreaUtils.getCommonInfo();
        ProcessInstDo processInstDo = commonInfo.getProcessInstDo();
        if (processInstDo == null){
            processInstDo = new ProcessInstDo();
            processInstDo.setProcessInstId(processInstId);
            processInstDo = processInstDo.queryProcessInstDoByProcessInstId();
            SwapAreaUtils.getCommonInfo().setProcessInstDo(processInstDo);
            System.out.println(SwapAreaUtils.getCommonInfo().getProcessInstDo());
        }
        return processInstDo;
    }
}
