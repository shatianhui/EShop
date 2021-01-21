package cn.sth.shop.servlet.front;

import cn.sth.shop.factory.ServiceFrontFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName:ProvincialServlet
 * Package:cn.sth.shop.servlet.front
 * Description:
 *
 * @Date:2020/1/26 11:16
 * Author:沙天慧
 */
@WebServlet(name = "ProvincialServlet",urlPatterns = "/pages/ProvincialServlet")
public class ProvincialServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        try{
            request.setAttribute("allProvincial", ServiceFrontFactory.getIProvincialServiceFrontInstance().list());
        }catch (Exception e){
            e.printStackTrace();
        }
        request.getRequestDispatcher("/pages/Provincial.jsp").forward(request,response);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
