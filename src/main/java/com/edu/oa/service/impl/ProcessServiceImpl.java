package com.edu.oa.service.impl;

import com.edu.oa.mdo.*;
import com.edu.oa.service.IProcessService;
import com.edu.oa.service.IStartProcessService;
import com.edu.oa.util.CommonInfo;
import com.edu.oa.util.CommonUtils;
import com.edu.oa.util.Constant;
import com.edu.oa.util.SwapAreaUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProcessServiceImpl implements IProcessService {

    @Resource
    private IStartProcessService startProcessService;


    /**
     * 创建流程实例ID
     * 格式为22位
     * @return processInstId
     */
    public String getProcessInstId(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        //日期17位
        String s = format.format(new Date());
        //序列号5位
        String defaultSequence = "00000";
        String sequence = CommonUtils.nextSequence("process_inst_id");

        defaultSequence = defaultSequence.substring(0 ,5 - sequence.length());
        StringBuilder sb = new StringBuilder();
        sb.append(s).append(defaultSequence).append(sequence);
        return sb.toString();
    }
    public ProcessInstDo makeProcess(TemplateInfo templateInfo ){
        //ProcessInstDo processInstDo = new ProcessInstDo();
        ProcessInstDo processInstDo = SwapAreaUtils.getCommonInfo().getProcessInstDo();
        if (processInstDo == null){
            processInstDo = new ProcessInstDo();
            System.out.println(processInstDo);
            SwapAreaUtils.getCommonInfo().setProcessInstDo(processInstDo);
        }
        processInstDo.setAvyId("1");
        List<TplAvyInfoDo> avyInfoList = templateInfo.getAvyInfoList();
        for (TplAvyInfoDo avyInfo : avyInfoList) {
            if (avyInfo.getAvyId().equals("1"))
                processInstDo.setExecutorId(avyInfo.getExecutorId());
        }

        processInstDo.setFunctionId(templateInfo.getFunctionId());
        processInstDo.setProcessInstId(getProcessInstId());
        processInstDo.setTplNo(templateInfo.getTplNo());
        //10-运行中,12-已完成
        processInstDo.setProcessTpcd("10");
        System.out.println(new Date());
        processInstDo.setTms(new Date());
        processInstDo.insert();
        return processInstDo;
    }

    /**
     * 获取下一级流程执行人id
     * @param avyId 流程节点ID
     * @return
     */
    public List<String> getNextExecutorList(String avyId){
        CommonInfo commonInfo = SwapAreaUtils.getCommonInfo();
        TemplateInfo templateInfo = commonInfo.getTemplateInfo();
        List<TplAvyInfoDo> avyInfoList = templateInfo.getAvyInfoList();
        System.out.println("avyInfoList = " + avyInfoList);
        List<String> userIdList = new ArrayList<>();
        for (TplAvyInfoDo info : avyInfoList) {
            if (info.getAvyId().equals(avyId)){
                String executorId = info.getExecutorId();
                //获取制单员
                String userId = SwapAreaUtils.getCommonInfo().getFirstUser();
                System.out.println("制单员=" + userId);
                //班级ID
                String clazzNo = userId.substring(0, 10);
                ProcessInstDo processInstDo = commonInfo.getProcessInstDo();
                processInstDo.setClassNo(clazzNo);
                processInstDo.setOwnerId(userId);
                //获取班级信息
                ClazzDo clazzDo = new ClazzDo();
                clazzDo.setClassNo(clazzNo);
                clazzDo = clazzDo.findClazzById();
                switch (executorId){
                    case Constant._001:
                        userIdList.add(userId);
                        break;
                    case Constant._002 :
                        //班长
                        userIdList.add(clazzDo.getMonitorNo());
                        break;
                    case Constant._003 :
                        //辅导员
                        userIdList.add(clazzDo.getCounselorNo());
                        break;
                    case Constant._004 :
                        //院长
                        userIdList.add(getDean(clazzDo.getAcademyNo()));
                        break;
                    case Constant._005 :
                        //主任
                        userIdList.add(getChairman());
                        break;

                }
            }

        }
        return userIdList;
    }

    /**
     * 为下一步执行人生成待办，
     * 为当前执行人生成可收回待办
     * 删除当前执行人的待办信息
     * @param executorList 下一步执行人列表
     * @param processInst 流程执行信息
     */
    public void makeTodoAvy(List<String> executorList, ProcessInstDo processInst){
        //为下一步执行人生成可退回的待办
        for (String userId : executorList) {
            TodoAvyInfoDo todoDo = new TodoAvyInfoDo();
            BeanUtils.copyProperties(processInst, todoDo);
            String avyId = processInst.getAvyId();
            int i = Integer.parseInt(avyId) + 1;
            todoDo.setAvyId(Integer.toString(i));
            todoDo.setExecutorId(userId);
            todoDo.setCanRetreat("1");
            todoDo.setCanWithdraw("0");
            todoDo.makeTodoAvyInf();
        }
        //为当前执行人生成可收回的待办
        String executorId = processInst.getExecutorId();
        if (executorId.length() == 3) {
            executorId = SwapAreaUtils.getCommonInfo().getFirstUser();
        }
            TodoAvyInfoDo todoDo = new TodoAvyInfoDo();
            BeanUtils.copyProperties(processInst, todoDo);
            String avyId = processInst.getAvyId();
            int i = Integer.parseInt(avyId) + 1;
            todoDo.setAvyId(Integer.toString(i));
            todoDo.setExecutorId(executorId);
            todoDo.setCanRetreat("0");
            todoDo.setCanWithdraw("1");
            todoDo.makeTodoAvyInf();

    }

    /**
     * 生成历史数据
     * @param processInst 流程实例信息
     */
    public void makeHistAvy(ProcessInstDo processInst){
        HistAvyDo histAvyDo = new HistAvyDo();
        BeanUtils.copyProperties(processInst, histAvyDo);
        String executorId = processInst.getExecutorId();
        //如果执行人id是三位的，则是第一步的流程发起人
        if (executorId.length() == 3) {
            executorId = SwapAreaUtils.getCommonInfo().getFirstUser();
            histAvyDo.setExecutorId(executorId);

        }

        histAvyDo.setTms(new Date());
        if (StringUtils.isNotBlank(processInst.getDecision()))
            histAvyDo.setDecision(processInst.getDecision());
        if (StringUtils.isNotBlank(processInst.getDecisionDesc()))
            histAvyDo.setDecisionDesc(processInst.getDecisionDesc());
        histAvyDo.insert();
    }

    /**
     * 删除执行人的待办信息
     * @param processInst
     */
    public void deleteTodoAvyInf(ProcessInstDo processInst){
        String executorId = processInst.getExecutorId();
        if (executorId.length() == 3) {
            executorId = SwapAreaUtils.getCommonInfo().getFirstUser();
        }
        //删除当前执行人的可退回待办
        TodoAvyInfoDo todoAvyInfoDo = new TodoAvyInfoDo();
        todoAvyInfoDo.setProcessInstId(processInst.getProcessInstId());
        todoAvyInfoDo.setExecutorId(executorId);
        todoAvyInfoDo.setCanRetreat("1");
        todoAvyInfoDo.deleteTodoAvyInf();
        //删除上一级执行人的可收回待办
        String avyId = processInst.getAvyId();
        int i = Integer.parseInt(avyId);
        System.out.println("删除待办时avyId=" + i);
        if (i > 1){
            List<String> users = this.getNextExecutorList(Integer.toString(i - 1));
            for (String user : users) {
                TodoAvyInfoDo mDo = new TodoAvyInfoDo();
                mDo.setProcessInstId(processInst.getProcessInstId());
                mDo.setExecutorId(user);
                mDo.setCanWithdraw("1");
                mDo.deleteTodoAvyInf();
            }
        }
    }

    /**
     * 更新流程实例
     * @param processInst
     */
    public void updateProcessInfo(ProcessInstDo processInst){
        String avyId = processInst.getAvyId();
        int i = Integer.parseInt(avyId);
        TemplateInfo templateInfo = SwapAreaUtils.getCommonInfo().getTemplateInfo();
        List<TplAvyInfoDo> avyInfoList = templateInfo.getAvyInfoList();
        if (i == avyInfoList.size()){//已经是最后一步了
            processInst.setProcessTpcd("12");
//            processInst.setAvyId(Integer.toString(i + 1));
        } else {
//            processInst.setAvyId(Integer.toString(i + 1));
        }
        processInst.updateProcessInstByProcessInstId();
    }
    /**
     * 获取下一个用户节点
     */
    public void getNextNodeInfo(){

    }

    /**
     * 根据学院ID及院长角色ID获取院长信息ID
     * @param academyNo 学院ID
     * @return 院长ID
     */
    public String getDean(String academyNo){
        User user = new User();
        user.setAcademyNo(academyNo);
        user.setExecutorId(Constant._004);
        List<User> users = user.getUser();
        if (null != users && users.size() > 0){
            return users.get(0).getUserId();
        }
        return null;
    }

    /**
     * 获取主任ID
     * 假设学校里只有一名负责流程审批的主任
     * @return
     */
    public String getChairman(){
        User user = new User();
        user.setExecutorId(Constant._005);
        List<User> users = user.getUser();
        if (null != users && users.size() > 0){
            return users.get(0).getUserId();
        }
        return null;
    }
}
