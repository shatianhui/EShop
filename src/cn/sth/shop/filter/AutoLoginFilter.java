package cn.sth.shop.filter;

import cn.sth.shop.factory.ServiceFrontFactory;
import cn.sth.shop.util.CookieUtil;
import cn.sth.shop.vo.Member;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * ClassName:AutoLoginFilter
 * Package:cn.sth.shop.filter
 * Description:
 *
 * @Date:2020/1/10 13:23
 * Author:沙天慧
 */
@WebFilter(filterName = "AutoLoginFilter",urlPatterns = {"/index.jsp","/pages/front/*"})
public class AutoLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpSession session=request.getSession();
        if(session.getAttribute("mid")==null){
            Map<String,String> map= CookieUtil.load(request);
            if (map.containsKey("mid")&&map.containsKey("password")) {
                Member vo = new Member();
                vo.setMid(map.get("mid"));
                vo.setPassword(map.get("password"));
                try {
                    if (ServiceFrontFactory.getIMemberServiceFrontInstance().login(vo)) {
                        session.setAttribute("mid", vo.getMid());
                        session.setAttribute("photo", vo.getPhoto());
                    } else {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
