package cn.sth.shop.service.back;

import cn.sth.shop.vo.Member;

import java.util.Map;
import java.util.Set;

/**
 * ClassName:IMemberServiceBack
 * Package:cn.sth.shop.service.back
 * Description:
 *
 * @Date:2020/1/11 14:09
 * Author:沙天慧
 */
public interface IMemberServiceBack {
    public Map<String,Object> list(String column,String keyword,int currentPage,int lineSize) throws Exception;
    public Map<String,Object> listByStatus(int status,String column,String keyword,int currentPage,int lineSize) throws Exception;

    /**
     * 待激活变为激活
     * @param ids
     * @return
     * @throws Exception
     */
    public boolean updateActive(Set<String> ids) throws Exception;

    /**
     * 激活状态变锁定
     * @param ids
     * @return
     * @throws Exception
     */
    public boolean updateLock(Set<String> ids) throws Exception;

    /**
     * 查看人员的完整信息
     * @param id
     * @return
     * @throws Exception
     */
    public Member show(String id) throws Exception;
}
