package cn.sth.shop.factory;

import cn.sth.shop.service.back.*;
import cn.sth.shop.service.back.impl.*;

/**
 * ClassName:ServiceBackFactory
 * Package:cn.sth.shop.factory
 * Description:
 *
 * @Date:2020/1/10 15:34
 * Author:沙天慧
 */
public class ServiceBackFactory {
    public static IAdminServiceBack getAdminServiceBackInstance(){
        return new AdminServiceBackImpl();
    }
    public static IMemberServiceBack getMemberServiceBackInstance(){
        return new MemberServiceBackImpl();
    }
    public static IItemServiceBack getItemServiceBackInstance(){
        return new ItemServiceBackImpl();
    }
    public static IGoodsServiceBack getGoodsServiceBackInstance(){
        return new GoodsServiceBackImpl();
    }
    public static IOrdersServiceBack getOrdersServiceBackInstance(){
        return new OrdersServiceBackImpl();
    }
}
