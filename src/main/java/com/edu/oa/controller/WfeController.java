package com.edu.oa.controller;

import com.alibaba.fastjson.JSONObject;
import com.edu.oa.mdo.HistAvyDo;
import com.edu.oa.mdo.LeaveInfoDo;
import com.edu.oa.mdo.User;
import com.edu.oa.service.*;
import com.edu.oa.util.Constant;
import com.edu.oa.util.JsonResult;
import com.edu.oa.util.SwapAreaUtils;
import com.edu.oa.vo.HistAvyVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
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

    /**
     * 为控制器添加通知，在控制器之前执行
     * 会在model中以user为键存值User
     * @return 当前用户
     */
    @ModelAttribute("user")
    public User initUser(){
        User user = new User();
        user.setUserId("202000100101");
        SwapAreaUtils.getCommonInfo().setUser(user);
        return user;
    }

    @RequestMapping("/askforleave")
    @ResponseBody
    public JsonResult askforleave(LeaveInfoDo leaveInfoDo){
        System.out.println("请假天数：" + leaveInfoDo.getDays());
        System.out.println(JSONObject.toJSONString(leaveInfoDo));
        String userId = "202000100101";
        leaveInfoDo.setUserId(userId);
        //
        startProcessService.startLeaveProcess(leaveInfoDo);
        return JsonResult.SUCCESS();
    }
    @RequestMapping("/ask")
    @ResponseBody
    public JsonResult askforleave1(){
        System.out.println("请假天数：" );
        return JsonResult.ERROR("申请失败");
    }
    @RequestMapping("/chooseCourse")
    @ResponseBody
    public String chooseCourse(){

//        return "{\"total\":28,\"rows\":[\n" +
//                "{\"course\":\"高数\",\"name\":\"高斯\",\"id\":\"001001\"},\n" +
//                "{\"course\":\"语文\",\"name\":\"朱自清\",\"id\":\"001002\"}]}";
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
//        return "{\"total\":20,\"rows\":[\n" +
//                "\t{\"startDate\":\"2020-12-12\",\"endDate\":\"2020-12-13\",\"days\":\"3\",\"tpcd\":\"申请中\",\"desc\":\"<a href='chooseCourse.html'>详情</a>\"},\n" +
//                "\t{\"startDate\":\"2020-12-12\",\"endDate\":\"2020-12-13\",\"days\":\"3\",\"tpcd\":\"申请成功\",\"desc\":\"<a href='chooseCourse.html'>详情</a>\"},\n" +
//                "\t{\"startDate\":\"2020-12-12\",\"endDate\":\"2020-12-13\",\"days\":\"3\",\"tpcd\":\"已销假\",\"desc\":\"<a href='chooseCourse.html'>详情</a>\"},\n" +
//                "\t{\"startDate\":\"2020-12-12\",\"endDate\":\"2020-12-13\",\"days\":\"3\",\"tpcd\":\"申请失败\",\"desc\":\"<a href='chooseCourse.html'>详情</a>\"},\n" +
//                "\t{\"startDate\":\"2020-12-12\",\"endDate\":\"2020-12-13\",\"days\":\"3\",\"tpcd\":\"申请中\",\"desc\":\"<a href='chooseCourse.html'>详情</a>\"},\n" +
//                "\t{\"startDate\":\"2020-12-12\",\"endDate\":\"2020-12-13\",\"days\":\"3\",\"tpcd\":\"申请中\",\"desc\":\"<a href='chooseCourse.html'>详情</a>\"},\n" +
//                "\t{\"startDate\":\"2020-12-12\",\"endDate\":\"2020-12-13\",\"days\":\"3\",\"tpcd\":\"申请中\",\"desc\":\"<a href='chooseCourse.html'>详情</a>\"},\n" +
//                "\t{\"startDate\":\"2020-12-12\",\"endDate\":\"2020-12-13\",\"days\":\"3\",\"tpcd\":\"申请中\",\"desc\":\"<a href='chooseCourse.html'>详情</a>\"},\n" +
//                "\t{\"startDate\":\"2020-12-12\",\"endDate\":\"2020-12-13\",\"days\":\"3\",\"tpcd\":\"申请中\",\"desc\":\"<a href='chooseCourse.html'>详情</a>\"},\n" +
//                "\t{\"startDate\":\"2020-12-12\",\"endDate\":\"2020-12-13\",\"days\":\"3\",\"tpcd\":\"申请中\",\"desc\":\"<a href='chooseCourse.html'>详情</a>\"},\n" +
//                "\t{\"startDate\":\"2020-12-12\",\"endDate\":\"2020-12-13\",\"days\":\"3\",\"tpcd\":\"申请中\",\"desc\":\"<a href='chooseCourse.html'>详情</a>\"},\n" +
//                "\t{\"startDate\":\"2020-12-12\",\"endDate\":\"2020-12-13\",\"days\":\"3\",\"tpcd\":\"申请中\",\"desc\":\"<a href='chooseCourse.html'>详情</a>\"},\n" +
//                "\t{\"startDate\":\"2020-12-12\",\"endDate\":\"2020-12-13\",\"days\":\"3\",\"tpcd\":\"申请中\",\"desc\":\"<a href='chooseCourse.html'>详情</a>\"},\n" +
//                "\t{\"startDate\":\"2020-12-12\",\"endDate\":\"2020-12-13\",\"days\":\"3\",\"tpcd\":\"申请中\",\"desc\":\"<a href='chooseCourse.html'>详情</a>\"},\n" +
//                "\t{\"startDate\":\"2020-12-12\",\"endDate\":\"2020-12-13\",\"days\":\"3\",\"tpcd\":\"申请中\",\"desc\":\"<a href='chooseCourse.html'>详情</a>\"},\n" +
//                "\t{\"startDate\":\"2020-12-12\",\"endDate\":\"2020-12-13\",\"days\":\"3\",\"tpcd\":\"申请中\",\"desc\":\"<a href='chooseCourse.html'>详情</a>\"},\n" +
//                "\t{\"startDate\":\"2020-12-12\",\"endDate\":\"2020-12-13\",\"days\":\"3\",\"tpcd\":\"申请中\",\"desc\":\"<a href='chooseCourse.html'>详情</a>\"},\n" +
//                "\t{\"startDate\":\"2020-12-12\",\"endDate\":\"2020-12-13\",\"days\":\"3\",\"tpcd\":\"申请中\",\"desc\":\"<a href='chooseCourse.html'>详情</a>\"},\n" +
//                "\t{\"startDate\":\"2020-12-12\",\"endDate\":\"2020-12-13\",\"days\":\"3\",\"tpcd\":\"申请中\",\"desc\":\"<a href='chooseCourse.html'>详情</a>\"},\n" +
//                "\t{\"startDate\":\"2020-12-12\",\"endDate\":\"2020-12-13\",\"days\":\"3\",\"tpcd\":\"申请中\",\"desc\":\"<a href='chooseCourse.html'>详情</a>\"}\n" +
//                "]}";
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
    public String approve(String description, String decision, String processInstId){
        SwapAreaUtils.getCommonInfo().setCurrentUserId("2016001901");
        System.out.println("description = " + description + " & decision = " + decision + " & processInstId = " + processInstId);
        if (Constant.decision_0.equals(decision) && StringUtils.isBlank(description))
            throw new RuntimeException("决绝审批时，原因为必输项！");
        dealProcess.dealProcess(description, decision, processInstId);
        return "";
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
}
