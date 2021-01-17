package com.edu.oa.vo;

import java.util.List;

public class HistAvyInfoVo {
    /**申请者姓名*/
    private String owner;
    /**请假理由*/
    private String description;
    /**申请的日期*/
    private String applyDate;
    private String startDate;
    private String endDate;
    private String days;
    /**流程状态*/
    private String processTpcd;
    private String processInstId;
    private String course;

    /**请假人信息*/
    private String majorName;
    private String academyName;
    private String className;

    /**流程历史执行信息*/
    private List<HistAvyInfoSubVo> subVoList;

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getAcademyName() {
        return academyName;
    }

    public void setAcademyName(String academyName) {
        this.academyName = academyName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public List<HistAvyInfoSubVo> getSubVoList() {
        return subVoList;
    }

    public void setSubVoList(List<HistAvyInfoSubVo> subVoList) {
        this.subVoList = subVoList;
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
}
