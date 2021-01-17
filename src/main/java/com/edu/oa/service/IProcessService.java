package com.edu.oa.service;

import com.edu.oa.mdo.LeaveInfoDo;
import com.edu.oa.mdo.ProcessInstDo;
import com.edu.oa.mdo.TemplateInfo;
import com.edu.oa.vo.HistAvyVo;
import com.edu.oa.vo.RefuseLeaveVo;

import java.util.List;

public interface IProcessService {
    /**
     * 创建流程实例
     * @param templateInfo
     */
    public ProcessInstDo makeProcess(TemplateInfo templateInfo );

    /**
     * 获取下一步执行人ID
     * @param avyId 流程节点ID
     * @return 下一步执行人ID
     */
    public List<String> getNextExecutorList(String avyId);

    /**
     * 为下一步执行人生成待办
     * @param executorList
     * @param processInst
     */
    public void makeTodoAvy(List<String> executorList, ProcessInstDo processInst);

    /**
     * 生成历史数据
     * @param processInst 流程实例信息
     */
    public void makeHistAvy(ProcessInstDo processInst);

    /**
     * 删除执行人的待办信息
     * @param processInst
     */
    public void deleteTodoAvyInf(ProcessInstDo processInst);

    /**
     * 更新流程实例
     * @param processInst
     */
    public void updateProcessInfo(ProcessInstDo processInst);

    void withdrawTodo(String processInstId);

    void changeWithdrewLeaveInfo(LeaveInfoDo leaveInfoDo);
    public void deleteWithdrewLeaveInfo(String processInstId);
    public HistAvyVo findStudentLeaveByTeacher(String condition, Integer page, Integer rows);
}
