package com.edu.oa.controller;

import com.edu.oa.mdo.Role;
import com.edu.oa.mdo.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login(String name, String password) {
        System.out.println("登录" + name + "|" + password);
        return "index";
    }

    @RequestMapping("/main")
    public String main1(Model m) {
        getAuthority(m);
        System.out.println("登录到主页面main.HTML");
        return "main";
    }

    @RequestMapping("/teacherMain")
    public String teacherMain(Model m) {
        getAuthority(m);
        System.out.println("登录到老师主页面teacherMain.HTML");
        return "teacherMain";
    }

    @RequestMapping("/center")
    public String center1() {
        System.out.println("登录2");
        return "center";
    }

    @RequestMapping("/datagrid")
    @ResponseBody
    public String datagrid() {
        return "[{\n" +
                "\t\"id\": \"01\",\n" +
                "\t\"name\": \"张三\",\n" +
                "\t\"password\": \"123\"\n" +
                "}, {\n" +
                "\t\"id\": \"02\",\n" +
                "\t\"name\": \"李四\",\n" +
                "\t\"password\": \"123\"\n" +
                "}]";
    }

    @RequestMapping("/centerIndex")
    public String centerIndex() {
        System.out.println("登录2");
        return "centerIndex";
    }

    @RequestMapping("/askleave")
    public String askleave() {
        System.out.println("请假页面");
        return "askleave";
    }

    @RequestMapping("/gg")
    public String gg() {
        return "chooseCourse";
    }

    /**
     * 历史查询
     *
     * @return
     */
    @RequestMapping("/findMyLeave")
    public String findMyLeave() {
        return "findMyLeave";
    }

    /**
     * 待办列表
     *
     * @return
     */
    @RequestMapping("/leaveTodo")
    public String leaveTodo() {
        return "leaveTodo";
    }

    /**
     * 已拒绝列表
     *
     * @return
     */
    @RequestMapping("/findRefuseList")
    public String findRefuseList() {
        return "refuseList";
    }

    /**
     * 可收回列表
     *
     * @return
     */
    @RequestMapping("/myCanWithdrawLeave")
    public String myCanWithdrawLeave() {
        return "myCanWithdrawLeave";
    }

    /**
     * 维护列表（已收回）
     *
     * @return
     */
    @RequestMapping("/maintenanceLeave")
    public String maintenanceLeave() {
        System.out.println("申请维护页面");
        return "maintenanceLeave";
    }

    /**
     * 详情
     *
     * @return
     */
    @RequestMapping("/leaveInfo")
    public String leaveInfo() {
        return "leaveInfo";
    }

    /**
     * 申请维护-修改-详情
     *
     * @return
     */
    @RequestMapping("/withdrewLeaveInfo")
    public String withdrewLeaveInfo() {
        return "withdrewLeaveInfo";
    }

    /**
     * 老师查询本课程的请假情况
     *
     * @return
     */
    @RequestMapping("/teacherLeave")
    public String teacherLeave() {
        return "teacherLeave";
    }

    @RequestMapping("/reviewLeave")
    public String reviewLeave() {
        return "reviewLeave";
    }

    @RequestMapping("/accessDenied")
    public String accessDenied(Model m) {
        m = getAuthority(m);
        return "accessDenied";
    }

    /**
     * 查询个人信息
     * @return
     */
    @RequestMapping("personalInformation")
    public String personalInformation(){
        return "personalInformation";
    }

    /**
     * 安全退出
     */
    @RequestMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null)
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        //重定向到login页面
        return "redirect:/login?logout";
    }

    //获取用户的登录信息
    public Model getAuthority(Model mv) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sbs = new StringBuilder();
        User user = new User();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        user.setUserId(userId);
        user = user.queryUserByUserId();
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            sb.append(role.getRoleName()).append(",");
            sbs.append(role.getAuthority()).append(",");
        }
        if (sb.indexOf(",") > 0) {
            sb.deleteCharAt(sb.length() - 1);
            sbs.deleteCharAt(sbs.length() - 1);
        }
        mv.addAttribute("name", user.getUsername());
        mv.addAttribute("roleName", sb.toString());
        mv.addAttribute("roles", sbs.toString());
        System.out.println("name=" + user.getUsername());
        System.out.println("roleName=" + sb.toString());
        return mv;
    }
}
