package com.edu.oa.mdo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@EntityScan
public class LeaveInfoDo extends BaseDo implements Serializable {
    private String processInstId;
    private String tplNo;
    private String classNo;
    private String userId;
    private String username;
    /**
     * 申请的日期
     */
    private String applyDate;
    private String startDate;
    private String endDate;
    private String days;
    private String description;
    private String functionId;
    /**
     * 课程
     */
    private String courseName;
    private String courseNo;
    /**
     * 老师
     */
    private String teacherName;
    /**
     * 请假涉及的老师的id
     */
    private String ids;
    /**
     * 查询用
     */
    private List<String> processInstIdList;
    private String teacherNo;
    /**
     * 流程状态
     */
    private String processTpcd;

    /**
     * 请假状态
     * bfr 未开始
     * curr 正在休假
     * aft 结束休假
     */
    private List<String> leaveStatus;
    private String today;

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public List<String> getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(List<String> leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    public String getProcessTpcd() {
        return processTpcd;
    }

    public void setProcessTpcd(String processTpcd) {
        this.processTpcd = processTpcd;
    }

    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

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

    public int save() {
        this.setTms(new Date());
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        this.setApplyDate(format.format(new Date()));
        if (StringUtils.isNotBlank(this.getIds())) {
            String[] split = this.getIds().split("\\|");
            String courseName = "";
            String teacherName = "";
            for (String id : split) {
                this.setTeacherNo(id);
                //查询课程名称
                LeaveInfoDo course = (LeaveInfoDo) getObjectByParam("LeaveInfoDo.getCourseByTeacherNo", this);
                if (course != null) {
                    courseName = courseName.concat(course.getCourseName());
                    courseName = courseName.concat(",");
                    teacherName = teacherName.concat(course.getTeacherName());
                    teacherName = teacherName.concat(",");

                    this.setCourseNo(course.getCourseNo());
                    this.setCourseName(course.getCourseName());
                    this.setTeacherName(course.getTeacherName());
                }
                insert("LeaveInfoDo.saveTeacherLeave", this);
            }
            this.setTeacherName(teacherName);
            this.setCourseName(courseName);
        }
        return insert("LeaveInfoDo.save", this);
    }

    /**
     * 查询请假的待办
     *
     * @return
     */
    public List<LeaveInfoDo> queryTodoListByProcessInstId() {
        return (List<LeaveInfoDo>) getListByParam("LeaveInfoDo.queryTodoListByProcessInstId", this);
    }

    public List<LeaveInfoDo> queryTeacherLeaveInfoByTeacherNo() {
        return (List<LeaveInfoDo>) getListByParam("LeaveInfoDo.queryTeacherLeaveInfoByTeacherNo", this);
    }

    public int deleteTeacherLeave() {
        return delete("LeaveInfoDo.deleteTeacherLeave", this);
    }

    public List<LeaveInfoDo> queryRefuseList() {
        return (List<LeaveInfoDo>) getListByParam("LeaveInfoDo.queryRefuseList", this);
    }

    /**
     * 根据流程实例编号查询影响课程
     *
     * @return
     */
    public List<LeaveInfoDo> queryCourseByProcessInstId() {
        return (List<LeaveInfoDo>) getListByParam("LeaveInfoDo.queryCourseByProcessInstId", this);
    }

    /**
     * 根据流程实例编号更新数据
     *
     * @return
     */
    public int updateByProcessInstId() {
        this.setTms(new Date());
        return update("LeaveInfoDo.updateByProcessInstId", this);
    }

    public int deleteLeaveInfoByProcessInstId() {
        return delete("LeaveInfoDo.deleteLeaveInfoByProcessInstId", this);
    }

    /**
     * 查询尚未开始休假
     * @return
     */
    public List<LeaveInfoDo> queryTeacherLeaveInfoByTeacherNoAndConditionBfr() {
        return (List<LeaveInfoDo>) getListByParam("LeaveInfoDo.queryTeacherLeaveInfoByTeacherNoAndConditionBfr", this);
    }

    /**
     * 查询正在休假
     * @return
     */
    public List<LeaveInfoDo> queryTeacherLeaveInfoByTeacherNoAndConditionCurr() {
        return (List<LeaveInfoDo>) getListByParam("LeaveInfoDo.queryTeacherLeaveInfoByTeacherNoAndConditionCurr", this);
    }

    /**
     * 查询结束休假
     * @return
     */
    public List<LeaveInfoDo> queryTeacherLeaveInfoByTeacherNoAndConditionAft() {
        return (List<LeaveInfoDo>) getListByParam("LeaveInfoDo.queryTeacherLeaveInfoByTeacherNoAndConditionAft", this);
    }
    /**
     * 查询所有状态
     * @return
     */
    public List<LeaveInfoDo> queryTeacherLeaveInfoByTeacherNoAndConditionAll() {
        return (List<LeaveInfoDo>) getListByParam("LeaveInfoDo.queryTeacherLeaveInfoByTeacherNoAndConditionAll", this);
    }
    /**
     * 查询所有状态
     * @return
     */
    public List<LeaveInfoDo> queryTeacherLeaveInfoByTeacherNoAndCondition() {
        return (List<LeaveInfoDo>) getListByParam("LeaveInfoDo.queryTeacherLeaveInfoByTeacherNoAndCondition", this);
    }
}
