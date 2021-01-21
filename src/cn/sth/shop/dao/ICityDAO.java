package cn.sth.shop.dao;

import cn.sth.shop.vo.City;

import java.sql.SQLException;
import java.util.List;

/**
 * ClassName:ICityDAO
 * Package:cn.sth.shop.dao
 * Description:
 *
 * @Date:2020/1/25 21:59
 * Author:沙天慧
 */
public interface ICityDAO {
    /**
     * 根据省份列出所有的城市信息
     * @param pid
     * @return
     * @throws SQLException
     */
    public List<City> findAllByProvincial(Integer pid) throws SQLException;
}
