package com.edu.oa.mdo;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;

public class TodoAvyInfoDo extends BaseDo{
    private String processInstId;
    private String avyId;
    private String tplNo;
    private String executorId;
    private String  classNo;
    private String functionId;
    private String  canWithdraw;
    private String  canRetreat;
    private String username;

    public String getProcessInstId() {
        return processInstId;
    }

    public void setProcessInstId(String processInstId) {
        this.processInstId = processInstId;
    }

    public String getAvyId() {
        return avyId;
    }

    public void setAvyId(String avyId) {
        this.avyId = avyId;
    }

    public String getTplNo() {
        return tplNo;
    }

    public void setTplNo(String tplNo) {
        this.tplNo = tplNo;
    }

    public String getExecutorId() {
        return executorId;
    }

    public void setExecutorId(String executorId) {
        this.executorId = executorId;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    /**
     * 是否可收回
     * @return
     */
    public String getCanWithdraw() {
        return canWithdraw;
    }

    /**
     * 是否可收回
     * @param canWithdraw
     */
    public void setCanWithdraw(String canWithdraw) {
        this.canWithdraw = canWithdraw;
    }

    /**
     * 是否可退回
     * @return
     */
    public String getCanRetreat() {
        return canRetreat;
    }

    /**
     * 是否可退回
     * @param canRetreat
     */
    public void setCanRetreat(String canRetreat) {
        this.canRetreat = canRetreat;
    }

    /**
     * 生成待办
     */
    public int makeTodoAvyInf(){
        this.setTms(new Date());
        int insert = insert("TodoAvyInfoDo.makeTodoAvyInf", this);
        return insert;
    }

    /**
     * 根据流程实例id和执行人ID，可退回标志
     * 删除待办信息
     * @return
     */
    public int deleteTodoAvyInf(){
        if (StringUtils.isBlank(this.getProcessInstId())){
            throw new RuntimeException("流程功能编号为空！");
        }

       return delete("TodoAvyInfoDo.deleteTodoAvyInf", this);
    }

    /**
     * 查询待办
     * @return 流程实例id
     */
    public List<TodoAvyInfoDo> getTodoList(){
        if (StringUtils.isBlank(this.getCanWithdraw()) && StringUtils.isBlank(this.getCanRetreat()))
            throw new RuntimeException("可收回或可退回标志不能为空！");
        return (List<TodoAvyInfoDo>)getListByParam("TodoAvyInfoDo.getTodoList", this);
    }
}
