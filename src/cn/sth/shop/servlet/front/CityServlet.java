package cn.sth.shop.servlet.front;

import cn.sth.shop.factory.ServiceFrontFactory;
import cn.sth.shop.vo.City;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * ClassName:ProvincialServlet
 * Package:cn.sth.shop.servlet.front
 * Description:
 *
 * @Date:2020/1/26 11:16
 * Author:沙天慧
 */
@WebServlet(name = "CityServlet",urlPatterns = "/pages/CityServlet")
public class CityServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/xml");
        try{
            int pid=Integer.parseInt(request.getParameter("pid"));
            List<City> all=ServiceFrontFactory.getICityServiceFrontInstance().listByProvincial(pid);
            Iterator<City> iter=all.iterator();
            Document document=DocumentHelper.createDocument();
            Element allProvincialElement=document.addElement("allProvincial");
            while (iter.hasNext()){
                City vo=iter.next();
                Element cityElement=allProvincialElement.addElement("city");
                Element cidElement=cityElement.addElement("cid");
                Element titleElement=cityElement.addElement("title");
                cidElement.setText(String.valueOf(vo.getCid()));
                titleElement.setText(vo.getTitle());
            }
            OutputFormat format=OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter out=new XMLWriter(response.getWriter(),format);
            out.write(document);
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
