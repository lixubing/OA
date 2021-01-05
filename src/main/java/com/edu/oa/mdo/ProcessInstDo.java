package com.edu.oa.mdo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.Date;


public class ProcessInstDo extends BaseDo{
    private String processInstId;
    private String avyId;
    private String tplNo;
    private String executorId;
    private String processTpcd;
    private String classNo;
    private String functionId;
    private String decision;
    private String decisionDesc;
    /**流程发起人id*/
    private String ownerId;

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
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

    public String getProcessTpcd() {
        return processTpcd;
    }

    public void setProcessTpcd(String processTpcd) {
        this.processTpcd = processTpcd;
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
        return insert("ProcessInstDo.insert",this);
    }
    public void updateProcessInstByProcessInstId(){
        this.setTms(new Date());
        update("ProcessInstDo.updateProcessInstByProcessInstId", this);
    }
    public ProcessInstDo queryProcessInstDoByProcessInstId(){
        if (StringUtils.isBlank(this.getProcessInstId()))
            throw new RuntimeException("流程实例id为空！");
        return (ProcessInstDo)getObjectByParam("ProcessInstDo.queryProcessInstDoByProcessInstId", this);
    }
}
