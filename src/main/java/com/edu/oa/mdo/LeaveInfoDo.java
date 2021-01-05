package com.edu.oa.mdo;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;
import java.util.List;

@EntityScan
public class LeaveInfoDo extends BaseDo implements Serializable {
    private String processInstId;
    private String tplNo;
    private String classNo;
    private String userId;
    private String username;
    /**申请的日期*/
    private String applyDate;
    private String startDate;
    private String endDate;
    private String days;
    private String description;
    private String functionId;
    /**课程*/
    private String courseName;
    /**老师*/
    private String teacherName;
    /**请假涉及的老师的id*/
    private String ids;
    /**查询用*/
    private List<String> processInstIdList;

    public List<String> getProcessInstIdList() {
        return processInstIdList;
    }

    public void setProcessInstIdList(List<String> processInstIdList) {
        this.processInstIdList = processInstIdList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

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

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getProcessInstId() {
        return processInstId;
    }

    public void setProcessInstId(String processInstId) {
        this.processInstId = processInstId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int save(){
        return insert("LeaveInfoDo.save", this);
    }

    /**
     * 查询请假的待办
     * @return
     */
    public List<LeaveInfoDo> queryTodoListByProcessInstId(){
       return (List<LeaveInfoDo>)getListByParam("LeaveInfoDo.queryTodoListByProcessInstId", this);
    }
}
