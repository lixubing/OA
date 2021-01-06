package com.edu.oa.vo;

/**
 * 流程历史执行信息
 */
public class HistAvyInfoSubVo {
    /**流程序号*/
    private String avyId;
    /**操作员id*/
    private String executorId;
    /**操作员姓名*/
    private String executorName;
    /**审批结果*/
    private String decision;
    /**审批原因*/
    private String decisionDesc;

    public String getAvyId() {
        return avyId;
    }

    public void setAvyId(String avyId) {
        this.avyId = avyId;
    }

    public String getExecutorId() {
        return executorId;
    }

    public void setExecutorId(String executorId) {
        this.executorId = executorId;
    }

    public String getExecutorName() {
        return executorName;
    }

    public void setExecutorName(String executorName) {
        this.executorName = executorName;
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
}
