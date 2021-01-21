package cn.sth.shop.service.front;

import cn.sth.shop.vo.City;

import java.util.List;

/**
 * ClassName:ICityServiceFront
 * Package:cn.sth.shop.service.front
 * Description:
 *
 * @Date:2020/1/26 10:18
 * Author:沙天慧
 */
public interface ICityServiceFront {
    public List<City> listByProvincial(int pid) throws Exception;
}
