package cn.sth.shop.util.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * ClassName:EncodingFilter
 * Package:cn.sth.shop.util.filter
 * Description:
 *
 * @Date:2020/1/7 16:47
 * Author:沙天慧
 */
@WebFilter(filterName = "EncodingFilter",urlPatterns = "/*")
public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest,servletResponse);
        servletResponse.setCharacterEncoding("UTF-8");

    }

    @Override
    public void destroy() {

    }
}
