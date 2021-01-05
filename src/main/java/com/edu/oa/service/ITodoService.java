package com.edu.oa.service;

import com.edu.oa.mdo.LeaveInfoDo;
import com.edu.oa.mdo.TodoAvyInfoDo;

import java.util.List;

public interface ITodoService {
    /**
     * 获取待审核代办
     * @return
     */
    public List<LeaveInfoDo> getApproveTodoList();
}
