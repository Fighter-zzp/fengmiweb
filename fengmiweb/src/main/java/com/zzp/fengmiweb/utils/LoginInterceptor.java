package com.zzp.fengmiweb.utils;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    //放行的url
    private String[] urls = {"goodsTypeJson","footer.jsp","header.jsp","getGoodsIndex","login.jsp","index.jsp","register.jsp","registerSuccess.jsp","login.jsp","adminLogin","userLogin"};
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取用户当前的请求地址
        String requestURI = request.getServletPath();
        if (checkURL(requestURI)){
            //放行
            return true;
        }else {
            var user = request.getSession().getAttribute("user");
            var adminUser = request.getSession().getAttribute("adminUser");
//            System.out.println(user);
//            System.out.println(adminUser);
            if (user!=null || adminUser!=null)  return true;
        }
        var url = allUrl(request, requestURI);
        //转发
        request.setAttribute("url",url);
        request.getRequestDispatcher("login.jsp").forward(request,response);
        return false;
    }

    private boolean checkURL(String url){
        for (String u : urls) {
            if (url.contains(u)){
                return true;
            }
        }
        return false;
    }

    //获取带参的uri地址
    private String allUrl(HttpServletRequest req,String requestURI){
        var parameters = req.getParameterMap();
        var sb = new StringBuilder(requestURI);
        if (parameters!=null && parameters.size()!=0) {
            sb.append("?");
            for (String key : parameters.keySet()) {
                sb.append(key).append("=").append(parameters.get(key)[0]);
                sb.append("&");
            }
            sb.delete(sb.lastIndexOf("&"), sb.length());
        }
        return sb.toString();
    }
}
