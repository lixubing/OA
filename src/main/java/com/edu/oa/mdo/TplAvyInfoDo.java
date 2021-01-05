package com.edu.oa.mdo;

import java.util.List;

public class TplAvyInfoDo extends BaseDo{
    private String tplNo;
    private String avyId;
    private String executorId;
    private String functionId;

    public String getTplNo() {
        return tplNo;
    }

    public void setTplNo(String tplNo) {
        this.tplNo = tplNo;
    }

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

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }
    public List<TplAvyInfoDo> getTplAvyInfoByTplNo(){
        return (List<TplAvyInfoDo>)getListByParam("TplAvyInfoDo.getTplAvyInfoByTplNo",this);
    }
}
