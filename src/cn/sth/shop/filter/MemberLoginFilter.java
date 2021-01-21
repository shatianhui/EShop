package cn.sth.shop.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * ClassName:MemberLoginFilter
 * Package:cn.sth.shop.filter
 * Description:
 *
 * @Date:2020/1/11 13:13
 * Author:沙天慧
 */
@WebFilter(filterName = "MemberLoginFilter",urlPatterns = {"/pages/front/ShopcarServletFront/list","/pages/front/member/*","/pages/front/orders/OrdersServletFront/*"})
public class MemberLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpSession session=request.getSession();
        if (session.getAttribute("mid")!=null) {//登陆过
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            request.setAttribute("msg","你还未登录，请你先登录！");
            request.setAttribute("url","/pages/member_login.jsp");
            request.getRequestDispatcher("/pages/forward.jsp").forward(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
