package cn.sth.shop.factory;

import cn.sth.shop.dao.IProvincialDAO;
import cn.sth.shop.service.front.*;
import cn.sth.shop.service.front.impl.*;

/**
 * ClassName:ServiceFrontFactory
 * Package:cn.sth.shop.factory
 * Description:
 *
 * @Date:2020/1/8 13:57
 * Author:沙天慧
 */
public class ServiceFrontFactory {
    public static IMemberServiceFront getIMemberServiceFrontInstance(){
        return new MemberServiceFrontImpl();
    }
    public static IGoodsServiceFront getIGoodsServiceFrontInstance(){
        return new GoodsServiceFrontImpl();
    }
    public static IShopcarServiceFront getIShopCarServiceFrontInstance(){
        return new ShopcarServiceFrontImpl();
    }
    public static IOrdersServiceFront getIOrdersServiceFrontInstance(){
        return new OrdersServiceFrontImpl();
    }
    public static IProvincialServiceFront getIProvincialServiceFrontInstance(){
        return new ProvincialServiceFrontImpl();
    }
    public static ICityServiceFront getICityServiceFrontInstance(){
        return new CityServiceFrontImpl();
    }
}
