package cn.sth.shop.factory;

import cn.sth.shop.dao.*;
import cn.sth.shop.dao.impl.*;
import cn.sth.shop.service.back.IItemServiceBack;
import cn.sth.shop.service.back.impl.ItemServiceBackImpl;

import java.sql.Connection;

/**
 * ClassName:DAOFactory
 * Package:cn.sth.shop.factory
 * Description:
 *
 * @Date:2020/1/8 13:45
 * Author:沙天慧
 */
public class DAOFactory {
    public static IMemberDAO getMemberDAOInstance(Connection conn){
        return new MemberDAOImpl(conn);
    }
    public static IAdminDAO getAdminDAOInstance(Connection conn){
        return new AdminDAOImpl(conn);
    }
    public  static IItemDAO getItemDAOInstance(Connection conn){
        return new ItemDAOImpl(conn);
    }
    public static IGoodsDAO getGoodsInstance(Connection conn){
        return new GoodsDAOImpl(conn);
    }
    public static IShopcarDAO getShopcarInstance(Connection conn){
        return new ShopcarDAOImpl(conn);
    }
    public static IOrdersDAO getOrdersInstance(Connection conn){
        return new OrdersDAOImpl(conn);
    }
    public static IDetailsDAO getDetailsInstance(Connection conn){
        return new DetailsDAOImpl(conn);
    }
    public static IProvincialDAO getProvincialInstance(Connection conn){
        return new ProvincialDAOImpl(conn);
    }
    public static ICityDAO getCityInstance(Connection conn){
        return new CityDAOImpl(conn);
    }
}
