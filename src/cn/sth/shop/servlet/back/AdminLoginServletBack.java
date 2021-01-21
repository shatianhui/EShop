package cn.sth.shop.servlet.back;

import cn.sth.shop.factory.ServiceBackFactory;
import cn.sth.shop.util.CookieUtil;
import cn.sth.shop.util.MD5Code;
import cn.sth.shop.util.validate.ValidateUtil;
import cn.sth.shop.vo.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * ClassName:AdminLoginServletBack
 * Package:cn.sth.shop.servlet.back
 * Description:
 *
 * @Date:2020/1/10 15:25
 * Author:沙天慧
 */
@WebServlet(name = "AdminLoginServletBack",urlPatterns = "/pages/back/AdminLoginServletBack/*")
public class AdminLoginServletBack extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path="/pages/errors.jsp";
        String status=request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1);
        System.out.println(status);
        if(status!=null){
            if("login".equals(status)){
                path = this.login(request) ;
            } else if("logout".equals(status)){
                path=this.logout(request,response);
            }
        }
        request.getRequestDispatcher(path).forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    public  String login(HttpServletRequest request){
        String msg=null;
        String url=null;
        String aid=request.getParameter("aid");
        String password=request.getParameter("password");
        String code=request.getParameter("code");
        String rand=(String)request.getSession().getAttribute("rand");
        if(ValidateUtil.validateEmpty(aid)&&ValidateUtil.validateEmpty(password)
                &&ValidateUtil.validateEmpty(code)&&ValidateUtil.validateEmpty(rand)){
            if(ValidateUtil.validateSame(code,rand)) {
                Admin vo = new Admin();
                vo.setAid(aid);
                vo.setPassword(new MD5Code().getMD5ofStr(password));
                try {
                    if (ServiceBackFactory.getAdminServiceBackInstance().login(vo)) {
                        request.getSession().setAttribute("aid", vo.getAid());
                        request.getSession().setAttribute("lastdate", vo.getLastdate());
                        msg = "登录成功，欢迎光临！";
                        url = "pages/back/admin/index.jsp";
                    } else {
                        msg = "管理员登录失败，错误的用户名或密码！";
                        url = "/pages/back/login.jsp";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                msg="验证码输入错误，请重新登录！";
                url="/pages/back/login.jsp";
            }
        }else{
            msg="管理员登录失败，请重新登录！";
            url="/pages/back/login.jsp";
        }
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }
    public String logout(HttpServletRequest request, HttpServletResponse response){
        CookieUtil.clear(request,response);
        request.getSession().invalidate();
        request.setAttribute("msg","管理员注销成功！");
        request.setAttribute("url","/pages/back/login.jsp");
        return "/pages/forward.jsp";
    }
}
