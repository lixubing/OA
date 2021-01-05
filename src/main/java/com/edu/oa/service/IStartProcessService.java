package com.edu.oa.service;

import com.edu.oa.mdo.LeaveInfoDo;
import com.edu.oa.mdo.TemplateInfo;

public interface IStartProcessService {
    public void startLeaveProcess(LeaveInfoDo leaveInfoDo);

    /**
     * 根据流程模板id查找流程模板对象
     * @param tplNo
     * @return
     */
    public TemplateInfo getTemplateInfo(String tplNo);
}
