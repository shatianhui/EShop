package cn.sth.shop.servlet.front;

import cn.sth.shop.factory.ServiceFrontFactory;
import cn.sth.shop.util.CookieUtil;
import cn.sth.shop.util.MD5Code;
import cn.sth.shop.util.validate.ValidateUtil;
import cn.sth.shop.vo.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * ClassName:MemberServletFront
 * Package:cn.sth.shop.servlet.front
 * Description:
 *
 * @Date:2020/1/8 14:26
 * Author:沙天慧
 */
@WebServlet(name = "MemberServletFront",urlPatterns = "/pages/memberServletFront/*")
public class MemberServletFront extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path="/pages/errors.jsp";
        String status=request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1);
        System.out.println(status);
        if(status!=null){
            if("register".equals(status)){
                path = this.register(request) ;
            }else if("login".equals(status)){
                path=this.login(request,response);
            }else if("logout".equals(status)){
                path=this.logout(request,response);
            }else if("checkMid".equals(status)){
                this.checkMid(request,response);
                return;
            }else if("checkCode".equals(status)){
                this.checkCode(request,response);
                return;//不需要进行路径跳转
            }
        }
        request.getRequestDispatcher(path).forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
    public String register(HttpServletRequest request){
        String msg=null;
        String url=null;
        String mid=request.getParameter("mid");
        String passWord=request.getParameter("passWord");
        if (ValidateUtil.validateEmpty(mid)&&ValidateUtil.validateEmpty(passWord)){
            Member vo=new Member();
            vo.setMid(mid);
            vo.setPassword(new MD5Code().getMD5ofStr(passWord));//需要通过MD5加密
            vo.setRegdate(new Date());
            vo.setPhoto("nophoto.jpg");
            vo.setCode(UUID.randomUUID().toString());//生成一个随机的Code码
            vo.setStatus(2);//2表示属于待激活状态
            try{
                if(ServiceFrontFactory.getIMemberServiceFrontInstance().register(vo)){
                    msg="用户注册成功，请进行账户激活(暂时不需要，可直接登录)";//发送信息给邮箱，邮件系统后期完善
                    url="/index.jsp";
                }else{
                    msg="用户注册失败，请重新注册！";
                    url="/pages/member_register.jsp";
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            msg="输入的用户注册信息不正确，请重新注册！";
            url="/pages/member_register.jsp";
        }
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }
    public  String login(HttpServletRequest request,HttpServletResponse response){
        String msg=null;
        String url=null;
        String mid=request.getParameter("mid");
        String password=request.getParameter("password");
        String code=request.getParameter("code");
        String rand=(String)request.getSession().getAttribute("rand");
        if (ValidateUtil.validateEmpty(mid)&&ValidateUtil.validateEmpty(password)
                &&ValidateUtil.validateEmpty(code)&&ValidateUtil.validateEmpty(rand)){
            if(ValidateUtil.validateSame(code,rand)){
                Member vo=new Member();
                vo.setMid(mid);
                vo.setPassword(new MD5Code().getMD5ofStr(password));
                try{
                    if (ServiceFrontFactory.getIMemberServiceFrontInstance().login(vo)){
                        request.getSession().setAttribute("mid",mid);
                        request.getSession().setAttribute("photo",vo.getPhoto());
                        msg="登录成功，欢迎光临！";
                        url="/index.jsp";
                        if(request.getParameter("reme")!=null){
                            int expiry=Integer.parseInt(request.getParameter("reme"));
                            CookieUtil.save(request,response,"mid",mid,expiry);
                            CookieUtil.save(request,response,"password",vo.getPassword(),expiry);
                        }
                    }else{
                        msg="用户登录失败，错误的用户名或密码！";
                        url="/pages/member_login.jsp";
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                msg="验证码输入错误，请重新登录！";
                url="/pages/member_login.jsp";
            }
        }else{
            msg="用户登录失败，请重新登录！";
            url="/pages/member_login.jsp";
        }
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }
    public String logout(HttpServletRequest request,HttpServletResponse response){
        CookieUtil.clear(request,response);
        request.getSession().invalidate();
        request.setAttribute("msg","你已安全退出!");
        request.setAttribute("url","/index.jsp");
        return "/pages/forward.jsp";
    }

    /**
     * 用于前端Ajax验证
     * @param request
     * @param response
     */
    public void checkMid(HttpServletRequest request,HttpServletResponse response){
        String mid=request.getParameter("mid");
        try{
            if(mid==null||"".equals(mid)){  //没有内容
                response.getWriter().print(false);  //无法使用
            }else{
                response.getWriter().print(ServiceFrontFactory.getIMemberServiceFrontInstance().checkMid(mid));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 用于前端Ajax验证码的验证
     * @param request
     * @param response
     * @return
     */
    public void checkCode(HttpServletRequest request,HttpServletResponse response){
        String code=request.getParameter("code");
        String rand= (String) request.getSession().getAttribute("rand");
        try {
            if (ValidateUtil.validateEmpty(code)) {
                if (ValidateUtil.validateSame(code, rand)) {
                    response.getWriter().print(true);
                    return;
                }
            }
            response.getWriter().print(false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
