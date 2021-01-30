package com.edu.oa.controller;

import com.alibaba.fastjson.JSONObject;
import com.edu.oa.mdo.*;
import com.edu.oa.service.*;
import com.edu.oa.util.Constant;
import com.edu.oa.util.JsonResult;
import com.edu.oa.util.SwapAreaUtils;
import com.edu.oa.vo.HistAvyVo;
import com.edu.oa.vo.RefuseLeaveVo;
import com.edu.oa.vo.ReviewHistLeaveInVo;
import com.edu.oa.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/wfe")
public class WfeController {
    private static final Logger LOG = LoggerFactory.getLogger(WfeController.class);
    @Resource
    private IStartProcessService startProcessService;
    @Resource
    private CourseService courseService;
    @Resource
    private ITodoService todoService;
    @Resource
    private IDealProcessService dealProcess;
    @Resource
    private IHistService histService;
    @Resource
    private IProcessService processService;
    @Resource
    private IUserService userService;

    /**
     * 为控制器添加通知，在控制器之前执行
     * 会在model中以user为键存值User
     * @return 当前用户
     */
    @ModelAttribute("user")
    public User initUser(){
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        LOG.info("security中的用户id=" + userId);
        User user = new User();
        user.setUserId(userId);
        user = user.queryUserByUserId();
        LOG.info("controller前置=" + user.getUserId());
        SwapAreaUtils.getCommonInfo().setUser(user);
        SwapAreaUtils.getCommonInfo().setCurrentUserId(user.getUserId());
        return user;
    }

    @RequestMapping("/askforleave")
    @ResponseBody
    public JsonResult askforleave(LeaveInfoDo leaveInfoDo){
        JsonResult js = new JsonResult();
        try{
            startProcessService.startLeaveProcess(leaveInfoDo);
        }catch (Exception e){
            LOG.info(e.getMessage(),e);
            js.setSuccess(false);
            js.setMsg(e.getMessage());
        }
        //

        return js;
    }
    @RequestMapping("/chooseCourse")
    @ResponseBody
    public String chooseCourse(){

        Map<String, List> courseAndTeacher = courseService.getCourseAndTeacher("202000100101");
        String result = JSONObject.toJSONString(courseAndTeacher);
        System.out.println(result);
        return result;
    }

    /**
     * 查找我的历史请假记录
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/fidMyHistLeave")
    @ResponseBody
    public HistAvyVo fidMyHistLeave(Integer page, Integer rows){
        LOG.info("page = " + page + " & rows = " + rows);
        ModelAndView mv = new ModelAndView();

        mv.addObject("name", "哈哈");
        return histService.getMyHistLeave(page, rows);
    }

    @RequestMapping("/findLeaveTodoList")
    @ResponseBody
    public List<LeaveInfoDo> findLeaveTodoList(){
        List<LeaveInfoDo> list = todoService.getApproveTodoList();
        return list;
    }

    /**
     * 审批
     * @param decision 审批决定：1-同意；0-拒绝
     * @param description 审批原因 拒绝时必输
     * @param processInstId 操作的流程还顺利id
     * @return
     */
    @RequestMapping("/approve")
    @ResponseBody
    public JsonResult approve(String description, String decision, String processInstId){
//        SwapAreaUtils.getCommonInfo().setCurrentUserId("2016001901");
        System.out.println("description = " + description + " & decision = " + decision + " & processInstId = " + processInstId);
        JsonResult js = new JsonResult();
        try {
            if (Constant.decision_0.equals(decision) && StringUtils.isBlank(description))
                throw new RuntimeException("决绝审批时，原因为必输项！");
            dealProcess.dealProcess(description, decision, processInstId);
        } catch (Exception e){
            js.setMsg(e.getMessage());
            js.setSuccess(false);
        }

        return js;
    }

    /**
     * 根据流程实例编号查询流程详细信息
     * @param
     */
    @RequestMapping("/getLeaveHistAvyInfo")
    public String getLeaveHistAvyInfo(String processInstId){
        LOG.info("processInstId=" + processInstId);
        return "index";
    }

    /**
     * 查询已拒绝的请假列表
     * @return
     */
    @RequestMapping("/findMyRefuseLeave")
    @ResponseBody
    public List<RefuseLeaveVo> findMyRefuseLeave(){
        List<RefuseLeaveVo> refuseList = histService.getRefuseList();
        return refuseList;
    }
    /**
     * 查询可收回请假列表
     * @return
     */
    @RequestMapping("/findMyCanWithdrawLeave")
    @ResponseBody
    public List<RefuseLeaveVo> findMyCanWithdrawLeave(){
        List<RefuseLeaveVo> refuseList = todoService.getCanWithdrawLeaveList();
        return refuseList;
    }
    /**
     * 查询已收回请假列表
     * @return
     */
    @RequestMapping("/findWithdrewLeave")
    @ResponseBody
    public List<RefuseLeaveVo> findWithdrewLeave(){
        List<RefuseLeaveVo> refuseList = todoService.getWithdrewLeaveList();
        return refuseList;
    }
    /**
     * 收回待办
     * @return
     */
    @RequestMapping("/withdraw")
    @ResponseBody
    public JsonResult withdraw(String processInstId){
        LOG.info("processInstId=" + processInstId);
        JsonResult jsonResult = new JsonResult();
        try {
            processService.withdrawTodo(processInstId);
        }catch (Exception e){
            jsonResult.setSuccess(false);
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 申请维护-修改
     * @return
     */
    @RequestMapping("/changeWithdrewLeaveInfo")
    @ResponseBody
    public JsonResult changeWithdrewLeaveInfo(LeaveInfoDo leaveInfoDo){
        JsonResult jsonResult = new JsonResult();
        try {
            processService.changeWithdrewLeaveInfo(leaveInfoDo);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult.setSuccess(false);
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }
    /**
     * 申请维护-修改
     * @return
     */
    @RequestMapping("/deleteWithdrewLeave")
    @ResponseBody
    public JsonResult deleteWithdrewLeave(String processInstId){
        JsonResult jsonResult = new JsonResult();
        try {
            processService.deleteWithdrewLeaveInfo(processInstId);
        }catch (Exception e){
            jsonResult.setSuccess(false);
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 根据老师id查询学生的请假情况
     * @param condition 查询条件
     * @param page 当前页
     * @param rows 每页条数
     * @return
     */
    @RequestMapping("/findStudentLeaveByTeacher")
    @ResponseBody
    public HistAvyVo findStudentLeaveByTeacher(String condition, Integer page, Integer rows){
        if(StringUtils.isBlank(condition))
            condition = "0";
        HistAvyVo histAvyVo = null;
        try {
                histAvyVo = processService.findStudentLeaveByTeacher(condition, page, rows);
            }catch (Exception e){
            LOG.info(e.getMessage(), e);
            }
        return histAvyVo;
    }

    /**
     * 流程审批人查询自己审批过的所有流程
     *
     * @return
     */
    @RequestMapping("/findLeaveByReviewExecutor")
    @ResponseBody
    public HistAvyVo findLeaveByReviewExecutor(ReviewHistLeaveInVo inVo){
        return histService.findLeaveByReviewExecutor(inVo);
    }

    @RequestMapping("/getPersonalInformation")
    @ResponseBody
    public UserVo getPersonalInformation(Model m){

        User user = (User) m.getAttribute("user");
        UserVo outVo = userService.completeUser(user);
        return outVo;
    }
    @RequestMapping("/updatePersonalInformation")
    @ResponseBody
    public JsonResult updatePersonalInformation(UserVo vo){

        JsonResult js = new JsonResult();
        try {
            userService.updatePersonalInformation(vo);
        }catch (Exception e){
            js.setMsg(e.getMessage());
            js.setSuccess(false);
        }
        return js;
    }

}
