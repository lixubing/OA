package com.edu.oa.vo;

import com.edu.oa.mdo.User;

import java.util.List;

public class ExecutorListVo {
    private String processInstId;
    private String avyId;
    private List<User> executors;

    public String getProcessInstId() {
        return processInstId;
    }

    public void setProcessInstId(String processInstId) {
        this.processInstId = processInstId;
    }

    public String getAvyId() {
        return avyId;
    }

    public void setAvyId(String avyId) {
        this.avyId = avyId;
    }

    public List<User> getExecutors() {
        return executors;
    }

    public void setExecutors(List<User> executors) {
        this.executors = executors;
    }
}
