package com.dj.ssm.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.ssm.pojo.ResultModel;
import com.dj.ssm.pojo.User;
import com.dj.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("toLogin")
    public String toLogin() {
        return "/user/login";
    }

    //mybatis-plus登陆
    @RequestMapping("login")
    @ResponseBody
    public ResultModel<Object> login(User user, HttpSession session) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        /* queryWrapper.setEntity(mpUser);*/
        //用户名/手机号/邮箱+密码登陆
        queryWrapper.or(i -> i.eq("user_name", user.getUserName())
                .or().eq("email", user.getUserName())
                .or().eq("phone", user.getUserName()));
        queryWrapper.eq("pwd", user.getPwd());
        User user1 = userService.getOne(queryWrapper);
        if (user1 == null) {
            return new ResultModel<>().error("用户名密码错误");
        }
        session.setAttribute("user",user1);
        return new ResultModel<>().success();
    }


    //去展示
    @RequestMapping("toShow")
    public String toShow() {
        return "/user/show";
    }

    //展示方法
    @RequestMapping("show")
    @ResponseBody
    public ResultModel<Object> show(Integer pageNo, String userName, String startAge, String endAge) {
        Map<String, Object> resultMap = new HashMap<>();
        //Mp地分页对象
        IPage<User> page = new Page<>(pageNo, 2);
        //设置当前页
       /* page.setCurrent(pageNo);
        //每页显示条数
        page.setSize(2);*/
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_del", 1);
        //姓名模糊查
        if (StringUtils.hasText(userName)) {
            queryWrapper.like("user_name", userName);
        }
        //范围查询
        if (!StringUtils.isEmpty(startAge)) {
            queryWrapper.gt("age", startAge);
        }
        if (!StringUtils.isEmpty(endAge)) {
            queryWrapper.lt("age", endAge);
        }
        List<User> list = userService.list(queryWrapper);
        IPage<User> allRoles = userService.getAllRoles((Page<User>) page, queryWrapper);
     /*   resultMap.put("totalNum",page.getPages());
        resultMap.put("records",allRoles);*/
        return new ResultModel<>().success(allRoles);
    }


    //去修改
    @RequestMapping("toUpd")
    public String toUpd(Integer id, Model model) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        User user = userService.getOne(queryWrapper);
        model.addAttribute("user", user);
        return "/user/update";
    }

    //修改
    @RequestMapping("update")
    @ResponseBody
    public ResultModel<Object> update(User user) {
        userService.updateById(user);
        return new ResultModel<>().success();
    }

    //删除(修改isdel)
    @RequestMapping("del")
    @ResponseBody
    public ResultModel<Object> del(User user) {
        userService.updateById(user);
        return new ResultModel<>().success();
    }

    //去新增页面
    @RequestMapping("toAdd")
    public String toAdd() {
        return "/user/add";
    }

    //新增方法
    @RequestMapping("add")
    @ResponseBody
    public ResultModel<Object> add(User user) {
        userService.save(user);
        return new ResultModel<>().success();
    }


}
