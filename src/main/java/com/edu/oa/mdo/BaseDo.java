package com.edu.oa.mdo;

import com.edu.oa.util.FrameworkContext;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.ibatis.session.RowBounds;

import java.util.Date;
import java.util.List;

public class BaseDo {
    final private Dao dao = (Dao) FrameworkContext.getApplicationContext().getBean("dao");

    private Date tms;

    /**每页大小*/
    private Integer rows;
    /**当前页*/
    private Integer page;
    private Boolean isPage = false;
    /**总条数*/
    private Integer total = 0;

    public Boolean getIsPage(){
        return isPage;
    }

    public void setPage(Boolean page) {
        isPage = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getPage() {
        return page;
    }



    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Date getTms() {
        return tms;
    }

    public void setTms(Date tms) {
        this.tms = tms;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Object getObjectByParam(String statement, Object parameter) {
        Object o = dao.getObjectByParam(statement, parameter);
        return o;
    }

    public List<?> getListByParam(String statement, Object parameter) {
        if (this.getIsPage() && this.getPage() != null && this.getRows() != null){
            //分页查询
            String count = statement.concat("_COUNT");
            System.out.println(dao);
            System.out.println(count);
            System.out.println(parameter);
            total = (Integer) dao.getObjectByParam(count, parameter);
            System.out.println(total);
            if (page < 1){
                page = 1;
            }
            if (rows < 1){
                rows = 10;
            }
            int pages = (int) Math.ceil((double) total / (double) rows);
            if (page > pages)
                page = pages;
            //从哪条开始查询
            int offset = (page - 1) * rows;
            RowBounds rowBounds = new RowBounds(offset, rows);
            List<?> list = dao.getListByParam(statement, parameter, rowBounds);
            return list;
        }
        List<?> list = dao.getListByParam(statement, parameter);
        return list;
    }

    public int update(String statement, Object parameter) {
        int update = dao.update(statement, parameter);
        return update;
    }

    public int insert(String statement, Object parameter) {
        int insert = dao.insert(statement, parameter);
        return insert;
    }

    public int delete(String statement, Object parameter) {
        int delete = dao.delete(statement, parameter);
        return delete;
    }
}
