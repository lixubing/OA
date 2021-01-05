package com.edu.oa.service.impl;

import com.edu.oa.mdo.LeaveInfoDo;
import com.edu.oa.mdo.TodoAvyInfoDo;
import com.edu.oa.service.ITodoService;
import com.edu.oa.util.SwapAreaUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询待办
 */
@Service
public class TodoService implements ITodoService {

    /**
     * 获取待审核代办
     * @return
     */
    public List<LeaveInfoDo> getApproveTodoList(){
        String userId = SwapAreaUtils.getCommonInfo().getCurrentUserId();
        userId = "2016001901";
        TodoAvyInfoDo mdo = new TodoAvyInfoDo();
        mdo.setCanRetreat("1");
        mdo.setExecutorId(userId);
        List<TodoAvyInfoDo> todoList = mdo.getTodoList();

        List<String> processInstIdList = new ArrayList<>();
        for (TodoAvyInfoDo todo : todoList) {
            processInstIdList.add(todo.getProcessInstId());
        }
        System.out.println("待办查询出的流程实例id = " + processInstIdList);

        List<LeaveInfoDo> list = new ArrayList<>();
        if (processInstIdList != null && processInstIdList.size() > 0) {
            LeaveInfoDo leaveInfoDo = new LeaveInfoDo();
            leaveInfoDo.setProcessInstIdList(processInstIdList);
            list = leaveInfoDo.queryTodoListByProcessInstId();
        }
        return list;
    }
}
