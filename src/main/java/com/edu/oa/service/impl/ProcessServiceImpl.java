package com.edu.oa.service.impl;

import com.edu.oa.mdo.*;
import com.edu.oa.service.IDealProcess;
import com.edu.oa.service.IDealProcessService;
import com.edu.oa.service.IProcessService;
import com.edu.oa.service.IStartProcessService;
import com.edu.oa.util.CommonInfo;
import com.edu.oa.util.CommonUtils;
import com.edu.oa.util.Constant;
import com.edu.oa.util.SwapAreaUtils;
import com.edu.oa.vo.HistAvyInfoVo;
import com.edu.oa.vo.HistAvyVo;
import com.edu.oa.vo.RefuseLeaveVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ProcessServiceImpl implements IProcessService {

    private static final Logger LOG = LoggerFactory.getLogger(ProcessServiceImpl.class);
    @Resource
    private IStartProcessService startProcessService;
    @Resource
    private IDealProcessService dealProcessService;


    /**
     * 创建流程实例ID
     * 格式为22位
     *
     * @return processInstId
     */
    public String getProcessInstId() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        //日期17位
        String s = format.format(new Date());
        //序列号5位
        String defaultSequence = "00000";
        String sequence = CommonUtils.nextSequence("process_inst_id");

        defaultSequence = defaultSequence.substring(0, 5 - sequence.length());
        StringBuilder sb = new StringBuilder();
        sb.append(s).append(defaultSequence).append(sequence);
        return sb.toString();
    }

    public ProcessInstDo makeProcess(TemplateInfo templateInfo) {
        //ProcessInstDo processInstDo = new ProcessInstDo();
        ProcessInstDo processInstDo = SwapAreaUtils.getCommonInfo().getProcessInstDo();
        if (processInstDo == null) {
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
     *
     * @param avyId 流程节点ID(要生成待办的执行人所在节点)
     * @return
     */
    public List<String> getNextExecutorList(String avyId) {
        CommonInfo commonInfo = SwapAreaUtils.getCommonInfo();
        TemplateInfo templateInfo = commonInfo.getTemplateInfo();
        List<TplAvyInfoDo> avyInfoList = templateInfo.getAvyInfoList();
        System.out.println("avyInfoList = " + avyInfoList);
        List<String> userIdList = new ArrayList<>();
        for (TplAvyInfoDo info : avyInfoList) {
            if (info.getAvyId().equals(avyId)) {
                String executorId = info.getExecutorId();
                //获取制单员
                String userId = SwapAreaUtils.getCommonInfo().getFirstUser();
                if (StringUtils.isBlank(userId)) {
                    userId = SwapAreaUtils.getCommonInfo().getProcessInstDo().getOwnerId();
                    LOG.info("制单员 = " + userId);
                    LOG.info("+++ = " + SwapAreaUtils.getCommonInfo().getProcessInstDo());
                    SwapAreaUtils.getCommonInfo().setFirstUser(userId);
                }
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
                switch (executorId) {
                    case Constant._001:
                        userIdList.add(userId);
                        break;
                    case Constant._002:
                        //班长
                        userIdList.add(clazzDo.getMonitorNo());
                        break;
                    case Constant._003:
                        //辅导员
                        userIdList.add(clazzDo.getCounselorNo());
                        break;
                    case Constant._004:
                        //院长
                        userIdList.add(getDean(clazzDo.getAcademyNo()));
                        break;
                    case Constant._005:
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
     *
     * @param executorList 下一步执行人列表
     * @param processInst  流程执行信息
     */
    public void makeTodoAvy(List<String> executorList, ProcessInstDo processInst) {
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
        LOG.info("executorId=" + executorId);
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
     *
     * @param processInst 流程实例信息
     */
    public void makeHistAvy(ProcessInstDo processInst) {
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
     *
     * @param processInst
     */
    public void deleteTodoAvyInf(ProcessInstDo processInst) {
        String executorId = processInst.getExecutorId();
        LOG.info("当前执行人id=" + executorId);
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
        if (i > 1) {
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
     *
     * @param processInst
     */
    public void updateProcessInfo(ProcessInstDo processInst) {
        String avyId = processInst.getAvyId();
        int i = Integer.parseInt(avyId);
        TemplateInfo templateInfo = SwapAreaUtils.getCommonInfo().getTemplateInfo();
        List<TplAvyInfoDo> avyInfoList = templateInfo.getAvyInfoList();
        if (i == avyInfoList.size()) {//已经是最后一步了
            processInst.setProcessTpcd("12");
//            processInst.setAvyId(Integer.toString(i + 1));
        } else {
//            processInst.setAvyId(Integer.toString(i + 1));
        }
        processInst.updateProcessInstByProcessInstId();
    }

    /**
     * 收回待办
     *
     * @param processInstId
     * @return
     */
    @Override
    public void withdrawTodo(String processInstId) {
        //1.删除下一步操作员待办
        TodoAvyInfoDo todoAvyInfoDo = new TodoAvyInfoDo();
        todoAvyInfoDo.setProcessInstId(processInstId);
        todoAvyInfoDo.setCanRetreat(Constant.NUM_1);
        todoAvyInfoDo.deleteTodoAvyInf();
        //2.修改当前操作员的待办标志 可回收待办->已收回待办
        String userId = SwapAreaUtils.getCommonInfo().getUser().getUserId();
        todoAvyInfoDo.setExecutorId(userId);
        todoAvyInfoDo.setCanWithdraw(Constant.NUM_1);
        todoAvyInfoDo.updateToWithdrew();
        //3.删除当前操作员历史操作数据
        HistAvyDo histAvyDo = new HistAvyDo();
        histAvyDo.setExecutorId(userId);
        histAvyDo.setProcessInstId(processInstId);
        histAvyDo.deleteHistAvyByProcessInstId();
        //4.更改流程实例表数据为 11-已收回
        ProcessInstDo processInstDo = new ProcessInstDo();
        processInstDo.setProcessInstId(processInstId);
        processInstDo = processInstDo.queryProcessInstDoByProcessInstId();
        processInstDo.setProcessTpcd(Constant.processTPCD_11);
        processInstDo.updateProcessInstByProcessInstId();

    }

    /**
     * 申请维护-修改
     *
     * @param leaveInfoDo
     */
    @Override
    public void changeWithdrewLeaveInfo(LeaveInfoDo leaveInfoDo) {
        //1.重新匹配模板
        String tplNo = startProcessService.getTplNo(leaveInfoDo);
        TemplateInfo templateInfo = SwapAreaUtils.getCommonInfo().getTemplateInfo();
        //2.根据流程实例编号更改biz_info业务表的数据
        leaveInfoDo.setTplNo(tplNo);
        leaveInfoDo.updateByProcessInstId();
        //3.生成下一步执行人的待办,生成当前执行人的可收回待办
        ProcessInstDo processInst = dealProcessService.getProcessInst(leaveInfoDo.getProcessInstId());

        String avyId = processInst.getAvyId();
        int i = Integer.parseInt(avyId) + 1;
        List<String> executorList = getNextExecutorList(Integer.toString(i));
        LOG.info("下一步执行人 = " + executorList);
        makeTodoAvy(executorList, processInst);
        //删除当前执行人的已收回待办
        TodoAvyInfoDo todoAvyInfoDo = new TodoAvyInfoDo();
        todoAvyInfoDo.setProcessInstId(leaveInfoDo.getProcessInstId());
        todoAvyInfoDo.setWithdrew(Constant.NUM_1);
        todoAvyInfoDo.deleteTodoAvyInf();
        //4.生成当前执行人的历史数据
        makeHistAvy(processInst);
        //5.根据流程实例编号更改process_info的数据，更改流程状态为10-运行中
        ProcessInstDo processInstDo = new ProcessInstDo();
        processInstDo.setProcessInstId(leaveInfoDo.getProcessInstId());
        processInstDo = processInstDo.queryProcessInstDoByProcessInstId();
        processInstDo.setProcessTpcd(Constant.processTPCD_10);
        processInstDo.updateProcessInstByProcessInstId();
    }

    /**
     * 申请维护-删除
     *
     * @param processInstId
     */
    @Override
    public void deleteWithdrewLeaveInfo(String processInstId) {
        //1.删除当前操作员的已回收待办
        TodoAvyInfoDo todoAvyInfoDo = new TodoAvyInfoDo();
        todoAvyInfoDo.setProcessInstId(processInstId);
        todoAvyInfoDo.setWithdrew(Constant.NUM_1);
        todoAvyInfoDo.deleteTodoAvyInf();
        //2.删除流程实例
        ProcessInstDo processInstDo = new ProcessInstDo();
        processInstDo.setProcessInstId(processInstId);
        processInstDo.deleteProcessInstByProcessInstId();
        //3.删除业务信息
        LeaveInfoDo leaveInfoDo = new LeaveInfoDo();
        leaveInfoDo.setProcessInstId(processInstId);
        leaveInfoDo.deleteLeaveInfoByProcessInstId();
        //4.删除影响课程的数据
        leaveInfoDo.deleteTeacherLeave();
    }

    /**
     * 获取下一个用户节点
     */
    public void getNextNodeInfo() {

    }

    /**
     * 根据学院ID及院长角色ID获取院长信息ID
     *
     * @param academyNo 学院ID
     * @return 院长ID
     */
    public String getDean(String academyNo) {
        LOG.info("academyNo=" + academyNo);
        User user = new User();
        user.setAcademyNo(academyNo);
        user.setExecutorId(Constant._004);
        List<User> users = user.getUser();
        if (null != users && users.size() > 0) {
            LOG.info("查询出院长id=" + users.get(0).getUserId());
            return users.get(0).getUserId();
        }
        return null;
    }

    /**
     * 获取主任ID
     * 假设学校里只有一名负责流程审批的主任
     *
     * @return
     */
    public String getChairman() {
        User user = new User();
        user.setExecutorId(Constant._005);
        List<User> users = user.getUser();
        if (null != users && users.size() > 0) {
            return users.get(0).getUserId();
        }
        return null;
    }

    /**
     * 根据老师id查询学生的请假情况
     *
     * @param condition 查询条件 0-查询所有，1-查询未开始的请假，2-查询正在进行的请假，3-查询已经结束的请假
     * @param page
     * @param rows
     * @return
     */
    public HistAvyVo findStudentLeaveByTeacher(String condition, Integer page, Integer rows) throws ParseException {
        //1.根据用户id查询teacher_leave中间表，找到流程实例id
        //2.根据流程实例查询请假信息，只查询申请成功的
        String teacherNo = SwapAreaUtils.getCommonInfo().getCurrentUserId();
        LeaveInfoDo leaveInfoDo = new LeaveInfoDo();
        leaveInfoDo.setTeacherNo(teacherNo);
        //设置分页
        leaveInfoDo.setRows(rows);
        leaveInfoDo.setPage(true);
        leaveInfoDo.setPage(page);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String today = format.format(new Date());
        leaveInfoDo.setToday(today);
        List<LeaveInfoDo> query = new ArrayList<>();
        List<String> status = new ArrayList<>();
        if (condition.contains(Constant.NUM_0)) {
            //查询所有
            LOG.info("--查询所有--");
            query = leaveInfoDo.queryTeacherLeaveInfoByTeacherNoAndConditionAll();
        }else{
            //根据查询条件分批查询
            if (condition.contains(Constant.NUM_1)){
                status.add("bfr");
            }
            if (condition.contains(Constant.NUM_2)){
                status.add("curr");
            }
            if (condition.contains(Constant.NUM_3)){
                status.add("aft");
            }
            LOG.info("查询条件=" + status.toString());
            leaveInfoDo.setLeaveStatus(status);
            query = leaveInfoDo.queryTeacherLeaveInfoByTeacherNoAndCondition();
            LOG.info(query.size()+"");
        }

       //3.组装返回数据
        HistAvyVo vo = new HistAvyVo();
        List<HistAvyInfoVo> infoVoList = new ArrayList<>();
        vo.setTotal(leaveInfoDo.getTotal());
        for (LeaveInfoDo infoDo : query) {
            HistAvyInfoVo infoVo = new HistAvyInfoVo();
            BeanUtils.copyProperties(infoDo, infoVo);
            ClazzDo clazzDo = new ClazzDo();
            LOG.info("classNo=" + infoDo.getClassNo());
            clazzDo.setClassNo(infoDo.getClassNo());
            clazzDo = clazzDo.findClazzById();
            infoVo.setClassName(clazzDo.getClassName());
            infoVo.setMajorName(clazzDo.getMajorName());
            infoVo.setAcademyName(clazzDo.getAcademyName());
            infoVo.setOwner(infoDo.getUsername());
            //设置请假状态 未开始，正在休假，休假结束
            Date startDate = format.parse(infoVo.getStartDate());
            Date endDate = format.parse(infoVo.getEndDate());
            Date now = format.parse(today);
            if (now.compareTo(startDate) < 0){
                //未开始
                infoVo.setLeaveStatus("未开始");
            }else if(now.compareTo(endDate) > 0){
                //已经结束
                infoVo.setLeaveStatus("休假结束");
            }else{
                //正在进行中
                infoVo.setLeaveStatus("正在休假");
            }
            infoVoList.add(infoVo);
        }
        vo.setRows(infoVoList);
        return vo;
    }
}
