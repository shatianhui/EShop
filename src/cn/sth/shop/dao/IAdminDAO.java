package cn.sth.shop.dao;

import cn.sth.shop.vo.Admin;

/**
 * ClassName:IAdminDAO
 * Package:cn.sth.shop.dao
 * Description:
 *
 * @Date:2020/1/10 14:57
 * Author:沙天慧
 */
public interface IAdminDAO extends IDAO<String, Admin> {
    /**
     * 实现管理员的登录操作，需要将上次登录时间返回
     * @param vo
     * @return
     * @throws Exception
     */
    public boolean findLogin(Admin vo) throws Exception;

    /**
     * 更新登录日期
     * @param aid
     * @return
     * @throws Exception
     */
    public boolean doUpdateLastdate(String aid) throws Exception;
}
