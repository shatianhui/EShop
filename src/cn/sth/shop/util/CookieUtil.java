package cn.sth.shop.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * ClassName:CookieUtil
 * Package:cn.sth.shop.util
 * Description:
 *
 * @Date:2020/1/10 12:50
 * Author:沙天慧
 */
public class CookieUtil {
    /**
     * 保存cookie的方法
     */
    public static void save(HttpServletRequest request,HttpServletResponse response,String name,String value,int expiry){
        Cookie cookie=new Cookie(name, value);
        cookie.setMaxAge(expiry);//失效时间
        cookie.setPath("/");//设置Cookie保存路径根目录
        response.addCookie(cookie);
    }
    public static Map<String,String> load(HttpServletRequest request){
        Map<String,String> map=new HashMap<String,String>();
        Cookie c[]=request.getCookies();
        if(c!=null) {
            for (int i = 0; i < c.length; i++) {
                if(!"JSESSIONID".equals(c[i].getName())){
                    map.put(c[i].getName(), c[i].getValue());
                }
            }
        }
        return map;
    }
    public static void clear(HttpServletRequest request,HttpServletResponse response){
        Map<String,String> map=load(request);
        Iterator<Map.Entry<String,String>> iter=map.entrySet().iterator();
        while (iter.hasNext()){
            Map.Entry<String,String> me=iter.next();
            Cookie cookie=new Cookie(me.getKey(),"");
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
    }
}
