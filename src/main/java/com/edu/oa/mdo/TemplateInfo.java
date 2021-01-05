package com.edu.oa.mdo;

import java.util.List;

public class TemplateInfo {
    private String tplNo;
    private String functionId;
    private String conditionDesc;
    private List<TplAvyInfoDo>  avyInfoList;

    public String getTplNo() {
        return tplNo;
    }

    public void setTplNo(String tplNo) {
        this.tplNo = tplNo;
    }

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    public String getConditionDesc() {
        return conditionDesc;
    }

    public void setConditionDesc(String conditionDesc) {
        this.conditionDesc = conditionDesc;
    }

    public List<TplAvyInfoDo> getAvyInfoList() {
        return avyInfoList;
    }

    public void setAvyInfoList(List<TplAvyInfoDo> avyInfoList) {
        this.avyInfoList = avyInfoList;
    }
}
