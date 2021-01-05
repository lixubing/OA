package com.edu.oa.util;

import com.edu.oa.mdo.ProcessInstDo;
import com.edu.oa.mdo.TemplateInfo;
import com.edu.oa.mdo.User;

public class CommonInfo {
    private TemplateInfo templateInfo;
    private ProcessInstDo processInstDo;
    private User user;


    /**流程发起人*/
    private String firstUser;

    /**
     * 当前登录用户的ID
     */
    private String currentUserId;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ProcessInstDo getProcessInstDo() {
        return processInstDo;
    }

    public void setProcessInstDo(ProcessInstDo processInstDo) {
        this.processInstDo = processInstDo;
    }

    public String getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(String currentUserId) {
        this.currentUserId = currentUserId;
    }

    public String getFirstUser() {
        return firstUser;
    }

    public void setFirstUser(String firstUser) {
        this.firstUser = firstUser;
    }

    public TemplateInfo getTemplateInfo() {
        return templateInfo;
    }

    public void setTemplateInfo(TemplateInfo templateInfo) {
        this.templateInfo = templateInfo;
    }
}
