package com.edu.oa.service.impl;

import com.edu.oa.mdo.LeaveInfoDo;
import com.edu.oa.mdo.TodoAvyInfoDo;
import com.edu.oa.service.ITodoService;
import com.edu.oa.util.Constant;
import com.edu.oa.util.SwapAreaUtils;
import com.edu.oa.vo.RefuseLeaveVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询待办
 */
@Service
public class TodoService implements ITodoService {
    private  final static Logger LOG = LoggerFactory.getLogger(TodoService.class);
    /**
     * 获取待审核代办
     * @return
     */
    public List<LeaveInfoDo> getApproveTodoList(){
        TodoAvyInfoDo mdo = new TodoAvyInfoDo();
        mdo.setCanRetreat("1");
        return getTodoList(mdo);
    }

    @Override
    public List<RefuseLeaveVo> getCanWithdrawLeaveList() {
        TodoAvyInfoDo mdo = new TodoAvyInfoDo();
        mdo.setCanWithdraw("1");
        List<LeaveInfoDo> todoList = getTodoList(mdo);
        List<RefuseLeaveVo> vos = new ArrayList<>();
        for (LeaveInfoDo leaveInfoDo : todoList) {
            LOG.info("leaveInfoDo=" + leaveInfoDo);
            //查询影响的课程（课程-老师）
            List<LeaveInfoDo> courseList = leaveInfoDo.queryCourseByProcessInstId();
            StringBuilder sb = new StringBuilder();
            for (LeaveInfoDo infoDo : courseList) {
                sb.append(infoDo.getCourseName()).append(" - ").append(infoDo.getTeacherName()).append(",");
            }
            if (sb.indexOf(",") > -1){
                sb.deleteCharAt(sb.length() - 1);
            }
            LOG.info("courseTeacher = " + sb.toString());
            RefuseLeaveVo vo = new RefuseLeaveVo();
            BeanUtils.copyProperties(leaveInfoDo, vo);
            vo.setCourseTeacher(sb.toString());
            vos.add(vo);
        }
        return vos;
    }

    @Override
    public List<RefuseLeaveVo> getWithdrewLeaveList() {

        TodoAvyInfoDo mdo = new TodoAvyInfoDo();
        mdo.setWithdrew(Constant.NUM_1);
        List<LeaveInfoDo> todoList = getTodoList(mdo);
        List<RefuseLeaveVo> vos = new ArrayList<>();
        for (LeaveInfoDo leaveInfoDo : todoList) {
            LOG.info("leaveInfoDo=" + leaveInfoDo);
            //查询影响的课程（课程-老师）
            List<LeaveInfoDo> courseList = leaveInfoDo.queryCourseByProcessInstId();
            StringBuilder sb = new StringBuilder();
            for (LeaveInfoDo infoDo : courseList) {
                sb.append(infoDo.getCourseName()).append(" - ").append(infoDo.getTeacherName()).append(",");
            }
            if (sb.indexOf(",") > -1){
                sb.deleteCharAt(sb.length() - 1);
            }
            LOG.info("courseTeacher = " + sb.toString());
            RefuseLeaveVo vo = new RefuseLeaveVo();
            BeanUtils.copyProperties(leaveInfoDo, vo);
            vo.setCourseTeacher(sb.toString());
            vos.add(vo);
        }
        return vos;
    }


    /**
     * 获取待办列表
     *  canWithdraw 可收回标志 1-可收回，0-不可收回
     *  canRetreat 可退回标志 1-可退回，0-不可退回
     *  withdrew 已收回标志 1-已收回，0-未收回
     * @return
     */
    public List<LeaveInfoDo> getTodoList(TodoAvyInfoDo mdo){
        String userId = SwapAreaUtils.getCommonInfo().getCurrentUserId();
        LOG.info("待办查询用户id=" + userId);
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
