package com.edu.oa.vo;

import java.util.List;

public class HistAvyVo {
    private int total;
    private List<HistAvyInfoVo> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<HistAvyInfoVo> getRows() {
        return rows;
    }

    public void setRows(List<HistAvyInfoVo> rows) {
        this.rows = rows;
    }
}
