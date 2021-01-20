package com.edu.oa.mdo;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class HistAvyDo extends BaseDo {
    private String processInstId;
    private String avyId;
    private String tplNo;
    private String executorId;
    /**操作员姓名*/
    private String executorName;
    private String classNo;
    private String functionId;
    private String decision;
    private String decisionDesc;

    private String startDate;
    private String endDate;
    private String days;
    private String processTpcd;
    /**请假理由*/
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExecutorName() {
        return executorName;
    }

    public void setExecutorName(String executorName) {
        this.executorName = executorName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getProcessTpcd() {
        return processTpcd;
    }

    public void setProcessTpcd(String processTpcd) {
        this.processTpcd = processTpcd;
    }

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

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getDecisionDesc() {
        return decisionDesc;
    }

    public void setDecisionDesc(String decisionDesc) {
        this.decisionDesc = decisionDesc;
    }

    public int insert(){
        return insert("HistAvyDo.insert", this);
    }

    /**
     * 根据用户id查询历史记录
     * @return
     */
    public List<HistAvyDo> queryHistAvyDoByExecutorId(){
        return (List<HistAvyDo>) getListByParam("HistAvyDo.queryHistAvyDoByExecutorId", this);
    }

    /**
     * 根据流程实例id查询流程详细信息
     * @return
     */
    public List<HistAvyDo> queryHistAvyInfoByProcessInstId(){
        if (StringUtils.isBlank(this.getProcessInstId()))
            throw new RuntimeException("processInstId不存在！");
        return (List<HistAvyDo>) getListByParam("HistAvyDo.queryHistAvyInfoByProcessInstId", this);
    }

    public int deleteHistAvyByProcessInstId() {
        return delete("HistAvyDo.deleteHistAvyByProcessInstId", this);
    }

}
