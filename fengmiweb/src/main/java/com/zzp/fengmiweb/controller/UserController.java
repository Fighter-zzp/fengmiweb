package com.zzp.fengmiweb.controller;

import com.zzp.fengmiweb.entity.Cart;
import com.zzp.fengmiweb.entity.User;
import com.zzp.fengmiweb.entity.ResultBean;
import com.zzp.fengmiweb.service.ICartService;
import com.zzp.fengmiweb.service.IUserService;
import com.zzp.fengmiweb.utils.EmailUtils;
import com.zzp.fengmiweb.utils.MD5Utils;
import com.zzp.fengmiweb.utils.RandomUtils;
import com.zzp.fengmiweb.utils.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    //用户业务处理
    @Autowired
    private IUserService iUserService;
    //购物车处理
    @Autowired
    private ICartService ics;
    //普通用户登录
    @RequestMapping("userLogin")
    public String login(String username, String password, Model model, HttpServletRequest req){
        if (!StrUtils.isEmpty(username,password)){
            User user = iUserService.getUserByName(username);
            //校验密码
            if (user!=null){
                if (user.getPassword().equals(MD5Utils.md5(password))){
                    //密码正确
                    //把用户设置到session中
                    req.getSession().setAttribute("user",user);
                    //购物车功能
                    Cart cart = ics.queryByUid(user.getId());
                    if (cart==null){
                        cart = new Cart();
                        cart.setUid(user.getId());
                        cart.setMoney(0);
                    }
                    req.getSession().setAttribute("cart",cart);
                    var redirect = req.getParameter("redirect");
                    if(redirect != null&&redirect.length()>0) {
                        //跳转到用户登录前的操作页面
                        return "redirect:" + redirect;
                    }
                    //页面跳转
                    return "index";
                }
            }
        }
        model.addAttribute("loginError", "用户名或密码错误");
        return "login";
    }

    //管理员登录
    @RequestMapping("adminLogin")
    public String adminLogin(String username, String password , HttpServletRequest req){
        if (!StrUtils.isEmpty(username,password)){
            User adminUser = iUserService.getUserByName(username);
            if (adminUser!=null){
                //校验密码
                if (adminUser.getPassword().equals(MD5Utils.md5(password))){
                    //把user存到session中
                    req.getSession().setAttribute("adminUser",adminUser);
                    return "admin/admin";
                }
            }
        }
        req.setAttribute("loginError","用户名或密码不错误");
        return "admin/login";
    }

    //注册
    @RequestMapping("userRegister")
    public String register(User user,Model model,HttpSession session){
        //创建激活码
        String activeCode = RandomUtils.createActive();
        user.setActivatecode(activeCode);
        if (iUserService.save(user)){
            //添加成功
            session.setAttribute("acode",activeCode);
            //发送激活码
            EmailUtils.sendEmail(user);
            return "registerSuccess";
        }else {
            //添加失败
            model.addAttribute("registerError","注册失败!");
            return "register";
        }
    }

    //注销
    @RequestMapping("userLoginOut")
    public String loginOut(String admin,HttpSession session){
        if (admin!=null){
            session.removeAttribute("adminUser");
            return "admin/login";
        }else {
            session.removeAttribute("user");
            return "index";
        }
    }

    //查看用户名是否可用
    @RequestMapping("checkUserName")
    @ResponseBody
    public ResultBean checkName(String name){
        var resultBean = new ResultBean();
        //根据用户名是否存在设置返回结果
        if (iUserService.checkName(name)) {
            resultBean.setCode(1);
            resultBean.setMsg("OK");
            //存在
            return resultBean;
        }else {
            resultBean.setCode(2);
            resultBean.setMsg("ERROR");
            //不存在
            return resultBean;
        }
    }

    //校验邮箱是否可用
    @RequestMapping("checkEmail")
    @ResponseBody
    public ResultBean checkEmail(String email){
        var resultBean = new ResultBean();
        if (iUserService.checkEmail(email)){
            resultBean.setCode(1);
            resultBean.setMsg("OK");
            //存在
            return resultBean;
        }else {
            resultBean.setCode(2);
            resultBean.setMsg("ERROR");
            //不存在
            return resultBean;
        }
    }

    //删除用户
    @RequestMapping("userDel")
    @ResponseBody
    public int delUser(int id){
        return iUserService.deleteById(id);
    }

    //搜索用户
    @RequestMapping("userSearch")
    @ResponseBody
    public List<User> userSearch(String username,String gender){
        return iUserService.userSearch(username, gender);
    }

    //展示用户列表
    @RequestMapping("userList")
    @ResponseBody
    public List<User> userList(){
        return iUserService.selectAll();
    }

    //激活激活码
    @RequestMapping("/active")
    public String checkCode(String email,String code){
        if (iUserService.activeUser(email,code)){
            //激活成功
            return "login";
        }else {
            //失败
            return "index";
        }
    }
}
