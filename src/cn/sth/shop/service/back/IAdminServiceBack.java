package cn.sth.shop.service.back;

import cn.sth.shop.vo.Admin;

/**
 * ClassName:IAdminServiceBack
 * Package:cn.sth.shop.service.back
 * Description:
 *
 * @Date:2020/1/10 15:17
 * Author:沙天慧
 */
public interface IAdminServiceBack {
    /**
     * 实现管理员登录操作
     * @param vo
     * @return
     * @throws Exception
     */
    public boolean login(Admin vo) throws Exception;
}
