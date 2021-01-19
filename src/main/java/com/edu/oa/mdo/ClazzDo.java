package com.edu.oa.mdo;

/**
 * 班级
 */
public class ClazzDo extends BaseDo {
    /**
     * 班级id
     */
    private String classNo;
    /**
     * 班级名称
     */
    private String className;
    /**
     * 班级的专业ID
     */
    private String majorNo;
    /**
     * 专业名称
     */
    private String majorName;
    /**
     * 学院ID
     */
    private String academyNo;
    /**
     * 学院名称
     */
    private String academyName;
    /**
     * 辅导员ID
     */
    private String counselorNo;
    /**
     * 辅导员姓名
     */
    private String counselorName;
    /**
     * 班长ID
     */
    private String monitorNo;
    /**
     * 班长姓名
     */
    private String monitorName;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getMajorNo() {
        return majorNo;
    }

    public void setMajorNo(String majorNo) {
        this.majorNo = majorNo;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getAcademyNo() {
        return academyNo;
    }

    public void setAcademyNo(String academyNo) {
        this.academyNo = academyNo;
    }

    public String getAcademyName() {
        return academyName;
    }

    public void setAcademyName(String academyName) {
        this.academyName = academyName;
    }

    public String getCounselorNo() {
        return counselorNo;
    }

    public void setCounselorNo(String counselorNo) {
        this.counselorNo = counselorNo;
    }

    public String getCounselorName() {
        return counselorName;
    }

    public void setCounselorName(String counselorName) {
        this.counselorName = counselorName;
    }

    public String getMonitorNo() {
        return monitorNo;
    }

    public void setMonitorNo(String monitorNo) {
        this.monitorNo = monitorNo;
    }

    public String getMonitorName() {
        return monitorName;
    }

    public void setMonitorName(String monitorName) {
        this.monitorName = monitorName;
    }

    /**
     * 根据班级ID查找班级信息
     *
     * @return
     */
    public ClazzDo findClazzById() {
        ClazzDo clazz = (ClazzDo) getObjectByParam("ClazzDo.findClazzById", this);
        return clazz;
    }
}
