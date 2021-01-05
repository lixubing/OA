package com.edu.oa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login(String name,String password){
        System.out.println("登录" + name + "|" + password);
        return "index";
    }
    @RequestMapping("/login1")
    @ResponseBody
    public String login1(String name,String password){
        System.out.println("登录" + name + "|" + password);
        return "login2";
    }
    @RequestMapping("/login2")
    public String login2(){
        System.out.println("登录2");
        return "index1";
    }
    @RequestMapping("/main")
    public String main1(){
        System.out.println("登录2");
        return "main";
    }
    @RequestMapping("/center")
    public String center1(){
        System.out.println("登录2");
        return "center";
    }
    @RequestMapping("/datagrid")
    @ResponseBody
    public String datagrid(){
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
    public String centerIndex(){
        System.out.println("登录2");
        return "centerIndex";
    }
    @RequestMapping("/askleave")
    public String askleave(){
        System.out.println("请假页面");
        return "askleave";
    }

    @RequestMapping("/gg")
    public String gg(){
        return "chooseCourse";
    }

    /**
     * 历史查询
     * @return
     */
    @RequestMapping("/findMyLeave")
    public String findMyLeave(){
        return "findMyLeave";
    }

    /**
     * 待办列表
     * @return
     */
    @RequestMapping("/leaveTodo")
    public String leaveTodo(){
        return "leaveTodo";
    }

}
