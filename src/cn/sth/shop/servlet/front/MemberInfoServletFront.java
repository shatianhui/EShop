package cn.sth.shop.servlet.front;

import cn.sth.shop.factory.ServiceFrontFactory;
import cn.sth.shop.util.CookieUtil;
import cn.sth.shop.util.MD5Code;
import cn.sth.shop.util.validate.ValidateUtil;
import cn.sth.shop.vo.Member;
import com.jspsmart.upload.SmartUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@WebServlet(name = "MemberInfoServletFront",urlPatterns = "/pages/front/member/MemberInfoServletFront/*")
public class MemberInfoServletFront extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path="/pages/errors.jsp";
        String status=request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1);
        System.out.println(status);
        if(status!=null){
            if("updatePre".equals(status)){
                path = this.updatePre(request) ;
            }else if ("update".equals(status)){
                path=this.update(request,response);
            }
        }
        request.getRequestDispatcher(path).forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
    public String updatePre(HttpServletRequest request){
        String mid= (String) request.getSession().getAttribute("mid");
        try {
            request.setAttribute("member",ServiceFrontFactory.getIMemberServiceFrontInstance().updatePre(mid));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/pages/front/member/member_update.jsp";
    }
    public String update(HttpServletRequest request,HttpServletResponse response){
        String msg=null;
        String url=null;
        String mid= (String) request.getSession().getAttribute("mid");
        SmartUpload smart=new SmartUpload();
        try {
            smart.initialize(super.getServletConfig(),request,response);
            smart.upload();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String name=smart.getRequest().getParameter("name");
        String phone=smart.getRequest().getParameter("phone");
        String address=smart.getRequest().getParameter("address");
        if(ValidateUtil.validateEmpty(name)&&ValidateUtil.validateEmpty(phone)&&ValidateUtil.validateEmpty(address)){
            Member vo=new Member();
            vo.setMid(mid);
            vo.setName(name);
            vo.setPhone(phone);
            vo.setAddress(address);
            vo.setPhoto(smart.getRequest().getParameter("oldPic"));
            try {
                if(smart.getFiles().getSize()>0){//有图片上传
                    if(smart.getFiles().getFile(0).getContentType().contains("image")){//图片
                        if("nophoto.jpg".equals(vo.getPhoto())){
                            vo.setPhoto(UUID.randomUUID()+"."+smart.getFiles().getFile(0).getFileExt());
                            System.out.println(vo.getPhoto());
                        }else {
                            vo.setPhoto("nophoto.jpg");
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(ServiceFrontFactory.getIMemberServiceFrontInstance().update(vo)){
                    if(smart.getFiles().getSize()>0){//有图片上传
                        if(smart.getFiles().getFile(0).getContentType().contains("image")){//图片
                            String filePath=super.getServletContext().getRealPath("/upload/member/")+vo.getPhoto();
                            smart.getFiles().getFile(0).saveAs(filePath);
                        }
                    }
                    msg="用户信息更新成功！";
                    request.getSession().setAttribute("photo",vo.getPhoto());
                }else{
                    msg="用户信息更新失败！";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            msg="更新数据不完整，请重新输入！";
        }
        url="/pages/front/member/MemberInfoServletFront/updatePre";
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }
}
