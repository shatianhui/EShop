package cn.sth.shop.dao;

import cn.sth.shop.vo.Member;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * ClassName:IMemberDAO
 * Package:cn.sth.shop.dao
 * Description:
 *
 * @Date:2020/1/8 12:43
 * Author:沙天慧
 */
public interface IMemberDAO extends IDAO<String, Member> {
    /**
     * 用户的登录检查操作，登录后可以查询出用户的照片信息
     * @param vo
     * @return
     * @throws Exception
     */
    public boolean findLogin(Member vo) throws Exception;

    /**
     * 根据用户的状态来进行数据的列表操作
     * @param status
     * @param currentPage
     * @param lineSize
     * @param column
     * @param keyWord
     * @return
     * @throws Exception
     */
    public List<Member> findAllByStatus(Integer status,Integer currentPage,Integer lineSize,String column,String keyWord) throws Exception;

    /**
     * 根据所有的状态统计数据量
     * @param status
     * @param column
     * @param keyWord
     * @return
     * @throws Exception
     */
    public Integer getAllCountByStatus(Integer status,String column,String keyWord) throws Exception;

    /**
     * 实现用户状态的批量更新
     * @param ids
     * @param status
     * @return
     * @throws Exception
     */
    public boolean doUpdateStatus(Set<String> ids,Integer status) throws Exception;
    public boolean doUpdateMember(Member vo) throws Exception;
    public Member findById2(String id) throws SQLException;
}
