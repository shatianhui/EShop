package cn.sth.shop.servlet.back;

import cn.sth.shop.factory.ServiceBackFactory;
import cn.sth.shop.util.CookieUtil;
import cn.sth.shop.util.MD5Code;
import cn.sth.shop.util.validate.ValidateUtil;
import cn.sth.shop.vo.Admin;
import cn.sth.shop.vo.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * ClassName:AdminLoginServletBack
 * Package:cn.sth.shop.servlet.back
 * Description:
 *
 * @Date:2020/1/10 15:25
 * Author:沙天慧
 */
@WebServlet(name = "MemberServletBack",urlPatterns = "/pages/back/admin/member/MemberServletBack/*")
public class MemberServletBack extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path="/pages/errors.jsp";
        String status=request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1);
        System.out.println(status);
        if(status!=null){
            if("list".equals(status)){
                path = this.list(request) ;
            }else if ("listStatus".equals(status)){
                path = this.listStatus(request) ;
            }else if("updateStatus".equals(status)){
                path=this.updateStatus(request);
            }else if("show".equals(status)){
                path=this.show(request);
            }
        }
        request.getRequestDispatcher(path).forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    public  String list(HttpServletRequest request){
        int currentPage=1;
        int lineSize=5;
        String column=null;
        String keyWord=null;
        String columnData="用户名:mid|真实姓名:name|联系电话:phone|地址:address";
        try{
            currentPage=Integer.parseInt(request.getParameter("cp"));
        }catch (Exception e){
        }
        try{
            lineSize=Integer.parseInt(request.getParameter("ls"));
        }catch (Exception e){
        }
        column=request.getParameter("col");
        keyWord=request.getParameter("kw");
        if(column==null){
            column="mid";
        }
        if (keyWord==null){
            keyWord="";
        }
        try{
            Map<String,Object> map=ServiceBackFactory.getMemberServiceBackInstance().list(column,keyWord,currentPage,lineSize);
            request.setAttribute("allMembers",map.get("allMembers"));
            request.setAttribute("allRecorders",map.get("memberCount"));
        }catch (Exception e){
            e.printStackTrace();
        }
        request.setAttribute("currentPage",currentPage);
        request.setAttribute("lineSize",lineSize);
        request.setAttribute("column",column);
        request.setAttribute("keyWord",keyWord);
        request.setAttribute("columnData",columnData);
        request.setAttribute("url","/pages/back/admin/member/MemberServletBack/list");
        return "/pages/back/admin/member/member_list.jsp";
    }
    public  String listStatus(HttpServletRequest request){
        int status=0;
        int currentPage=1;
        int lineSize=5;
        String column=null;
        String keyWord=null;
        String columnData="用户名:mid|真实姓名:name|联系电话:phone|地址:address";
        try{
            status=Integer.parseInt(request.getParameter("status"));
        }catch (Exception e){}
        try{
            currentPage=Integer.parseInt(request.getParameter("cp"));
        }catch (Exception e){
        }
        try{
            lineSize=Integer.parseInt(request.getParameter("ls"));
        }catch (Exception e){
        }
        column=request.getParameter("col");
        keyWord=request.getParameter("kw");
        if(column==null){
            column="mid";
        }
        if (keyWord==null){
            keyWord="";
        }
        try{
            Map<String,Object> map=ServiceBackFactory.getMemberServiceBackInstance().listByStatus(status,column,keyWord,currentPage,lineSize);
            request.setAttribute("allMembers",map.get("allMembers"));
            request.setAttribute("allRecorders",map.get("memberCount"));
        }catch (Exception e){
            e.printStackTrace();
        }
        request.setAttribute("currentPage",currentPage);
        request.setAttribute("status",status);
        request.setAttribute("lineSize",lineSize);
        request.setAttribute("column",column);
        request.setAttribute("keyWord",keyWord);
        request.setAttribute("columnData",columnData);
        request.setAttribute("url","/pages/back/admin/member/MemberServletBack/list");
        request.setAttribute("paramName","status");
        request.setAttribute("paramValue",String.valueOf(status));
        return "/pages/back/admin/member/member_list.jsp";
    }
    public String updateStatus(HttpServletRequest request){
        String referer=request.getHeader("referer");//取得之前的路径
        String type=request.getParameter("type");
        String msg=null;
        String url=null;
        String ids=request.getParameter("ids");
        if(ValidateUtil.validateEmpty(ids)){
            String result[]=ids.split("\\|");
            Set<String> mid=new HashSet<String>();
            for(int i=0;i<result.length;i++){
                mid.add(result[i]);
            }
            try{
                if("active".equalsIgnoreCase(type)){
                    if (ServiceBackFactory.getMemberServiceBackInstance().updateActive(mid)){
                        msg="用户状态已更新成功！";
                    }else{
                        msg="用户状态更新失败！";
                    }
                }
                if("lock".equalsIgnoreCase(type)){
                    if (ServiceBackFactory.getMemberServiceBackInstance().updateLock(mid)){
                        msg="用户状态已更新成功！";
                    }else{
                        msg="用户状态更新失败！";
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            msg="你还未选择更新数据，请重新操作";
        }
        url="/pages/back/admin/member/MemberServletBack"+referer.substring(referer.lastIndexOf("/"));
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }
    public String show(HttpServletRequest request){
        String mid=request.getParameter("mid");
        String msg=null;
        String url=null;
        String referer=request.getHeader("referer");//取得之前的路径
        if (ValidateUtil.validateEmpty(mid)) {
            try{
                request.setAttribute("member",ServiceBackFactory.getMemberServiceBackInstance().show(mid));
            }catch (Exception e){
                e.printStackTrace();
            }
            return "/pages/back/admin/member/member_show.jsp";
        }else{
            msg="你游览的数据不存在或者还未选择任何的数据，请重新选择!";
            url="/pages/back/admin/member/MemberServletBack"+referer.substring(referer.lastIndexOf("/"));
            request.setAttribute("msg",msg);
            request.setAttribute("url",url);
            return "/pages/forward.jsp";
        }

    }
}
