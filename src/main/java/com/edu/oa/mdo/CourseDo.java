package com.edu.oa.mdo;

import org.springframework.stereotype.Repository;

import java.util.List;

/**课程*/
//@Repository
public class CourseDo extends BaseDo{
    /**课程ID*/
    private String courseNo;
    /**课程名称*/
    private String courseName;
    /**老师的ID*/
    private String teacherNo;
    /**老师姓名*/
    private String teacherName;
    /**学号*/
    private String studentNo;
    /**学生姓名*/
    private String studentName;
    /**分数*/
    private String score;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
    /**
     * 获取学生的课程及成绩
     */
    public List<CourseDo> getCourseDoByStudentNo(){
        return (List<CourseDo>)getListByParam("course.getCourseAndTeacher",this);
    }

    public List<CourseDo> queryCourseByTescherNo() {
        return (List<CourseDo>)getListByParam("course.queryCourseByTescherNo",this);
    }
}
