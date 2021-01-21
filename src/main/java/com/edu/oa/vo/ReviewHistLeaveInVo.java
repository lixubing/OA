package com.edu.oa.vo;

public class ReviewHistLeaveInVo {
    private String startDateBegin;
    private String startDateEnd;
    private String endDateBegin;
    private String endDateEnd;
    private Integer page;
    private Integer rows;

    public String getStartDateBegin() {
        return startDateBegin;
    }

    public void setStartDateBegin(String startDateBegin) {
        this.startDateBegin = startDateBegin;
    }

    public String getStartDateEnd() {
        return startDateEnd;
    }

    public void setStartDateEnd(String startDateEnd) {
        this.startDateEnd = startDateEnd;
    }

    public String getEndDateBegin() {
        return endDateBegin;
    }

    public void setEndDateBegin(String endDateBegin) {
        this.endDateBegin = endDateBegin;
    }

    public String getEndDateEnd() {
        return endDateEnd;
    }

    public void setEndDateEnd(String endDateEnd) {
        this.endDateEnd = endDateEnd;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
