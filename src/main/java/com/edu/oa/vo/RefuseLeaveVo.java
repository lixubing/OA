package com.edu.oa.vo;

/**
 * 已决绝的假期申请
 */
public class RefuseLeaveVo {
    private String startDate;
    private String endDate;
    private String days;
    private String courseTeacher;
    private String script;
    private String lastExecutor;
    private String decisionDesc;
    private String ids;
    private String processInstId;
    private String description;
    private String courseName;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProcessInstId() {
        return processInstId;
    }

    public void setProcessInstId(String processInstId) {
        this.processInstId = processInstId;
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

    public String getCourseTeacher() {
        return courseTeacher;
    }

    public void setCourseTeacher(String courseTeacher) {
        this.courseTeacher = courseTeacher;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getLastExecutor() {
        return lastExecutor;
    }

    public void setLastExecutor(String lastExecutor) {
        this.lastExecutor = lastExecutor;
    }

    public String getDecisionDesc() {
        return decisionDesc;
    }

    public void setDecisionDesc(String decisionDesc) {
        this.decisionDesc = decisionDesc;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}
