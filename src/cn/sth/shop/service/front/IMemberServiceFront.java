package cn.sth.shop.service.front;

import cn.sth.shop.vo.Member;

/**
 * ClassName:IMemberServiceFront
 * Package:cn.sth.shop.service.front
 * Description:
 *
 * @Date:2020/1/8 13:47
 * Author:沙天慧
 */
public interface IMemberServiceFront {
    /**
     * 实现用户注册
     * @param vo 包含有注册信息的vo对象
     * @return
     * @throws Exception
     */
    public boolean register(Member vo) throws Exception;

    /**
     * 用户登录操作
     * @param vo
     * @return
     * @throws Exception
     */
    public boolean login(Member vo) throws Exception;
    public Member updatePre(String mid) throws Exception;
    public boolean update(Member vo) throws Exception;

    /**
     * 用于前端Ajax,检查输入的id是否存在
     * @param mid
     * @return
     * @throws Exception
     */
    public boolean checkMid(String mid) throws Exception;
}
