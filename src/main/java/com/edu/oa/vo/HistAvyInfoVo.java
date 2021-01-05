package com.edu.oa.vo;

public class HistAvyInfoVo {
    private String startDate;
    private String endDate;
    private String days;
    private String processTpcd;
    private String processInstId;

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
