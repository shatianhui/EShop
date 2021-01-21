package cn.sth.shop.dao;

import cn.sth.shop.vo.Provincial;

import java.sql.SQLException;
import java.util.List;

/**
 * ClassName:IProvincialDAO
 * Package:cn.sth.shop.dao
 * Description:
 *
 * @Date:2020/1/25 21:57
 * Author:沙天慧
 */
public interface IProvincialDAO {
    /**
     * 列出所有的省份
     * @return
     * @throws SQLException
     */
    public List<Provincial> findAll() throws SQLException;
}
