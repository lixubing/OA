package com.edu.oa.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.edu.oa.mdo.*;
import com.edu.oa.service.IProcessService;
import com.edu.oa.service.IStartProcessService;
import com.edu.oa.util.CommonInfo;
import com.edu.oa.util.SwapAreaUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StartProcessServiceImpl implements IStartProcessService {
    @Resource
    private IProcessService processService;

    @Override
    public void startLeaveProcess(LeaveInfoDo leaveInfoDo) {
        //完善信息
        completeLeaveInfo(leaveInfoDo);
        //1.匹配流程模板
        String tplNo = getTplNo(leaveInfoDo);
        System.out.println("tplNo = " + tplNo);
        //2.完善流程模板信息
        TemplateInfo templateInfo = getTemplateInfo(tplNo);
        System.out.println(templateInfo.getTplNo() + "|" + templateInfo.getFunctionId() + "|" + templateInfo.getConditionDesc() + "|" + templateInfo.getAvyInfoList());
        //3.创建流程实例
        ProcessInstDo processInstDo = processService.makeProcess(templateInfo);
        //4.保存请求信息
        leaveInfoDo.setProcessInstId(processInstDo.getProcessInstId());
        leaveInfoDo.setTplNo(processInstDo.getTplNo());
//        BeanUtils.copyProperties();
        leaveInfoDo.save();
        //5.查找下一步执行人
        String avyId = processInstDo.getAvyId();
        int i = Integer.parseInt(avyId) + 1;
        List<String> nextExecutorList = processService.getNextExecutorList(Integer.toString(i));
        //6.生成下一步执行人的待办
        processService.makeTodoAvy(nextExecutorList, processInstDo);
        //7.生成当前执行人的历史数据
        processService.makeHistAvy(processInstDo);
        //8.删除当前执行人的待办信息
        processService.deleteTodoAvyInf(processInstDo);
        //9.更新流程实例数据
        processService.updateProcessInfo(processInstDo);
    }

    private void completeLeaveInfo(LeaveInfoDo leaveInfoDo) {
        User user = new User();
        leaveInfoDo.setUsername("张三");
    }

    /**根据条件查找流程模板*/
    public String getTplNo(LeaveInfoDo leaveInfoDo){
//        TemplateInfo templateInfo = new TemplateInfo();
        //请假流程模板的功能编号是COMM_001

        String tplNo = null;
        TplMpngRuleDo ruleDo = new TplMpngRuleDo();
        ruleDo.setFunctionId("COMM_001");

        List<TplMpngRuleDo> ruleList = ruleDo.getTplMpngRuleByFunctionId();
        System.out.println("查到的模板数 = " + ruleList.size());
        for (TplMpngRuleDo rule : ruleList) {
            rule.setDays(leaveInfoDo.getDays());
            boolean b = rule.executeMtchRuleExp();
            if (b) {
                tplNo = rule.getTplNo();
                //将templateInfo放入到内存中
                CommonInfo commonInfo = SwapAreaUtils.getCommonInfo();
                getTemplateInfo(tplNo);
//                templateInfo.setTplNo(tplNo);
//                templateInfo.setFunctionId(rule.getFunctionId());
//                commonInfo.setTemplateInfo(templateInfo);
                commonInfo.setFirstUser(leaveInfoDo.getUserId());
            }
        }
        return tplNo;
    }

    /**
     * 完善流程模板信息
     * @param tplNo
     * @return
     */
    public TemplateInfo getTemplateInfo(String tplNo){
        CommonInfo commonInfo = SwapAreaUtils.getCommonInfo();
        TemplateInfo templateInfo = commonInfo.getTemplateInfo();
        if (null == templateInfo){
            templateInfo = new TemplateInfo();
            commonInfo.setTemplateInfo(templateInfo);
        } else {
            return templateInfo;
        }
        //查询tpl_mpng_rule
        TplMpngRuleDo ruleDo = new TplMpngRuleDo();
        ruleDo.setTplNo(tplNo);
        ruleDo = ruleDo.getTplMpngRuleByTplNo();
        templateInfo.setTplNo(tplNo);
        templateInfo.setFunctionId(ruleDo.getFunctionId());
        templateInfo.setConditionDesc(ruleDo.getConditionDesc());
        //查询tpl_avy_info
        TplAvyInfoDo tplAvyInfoDo = new TplAvyInfoDo();
        tplAvyInfoDo.setTplNo(tplNo);
        List<TplAvyInfoDo> list = tplAvyInfoDo.getTplAvyInfoByTplNo();
        System.out.println("TplAvyInfoDo.list.size = " + list.size());
        templateInfo.setAvyInfoList(list);
        return templateInfo;
    }
}
