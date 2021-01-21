package cn.sth.shop.test;

import cn.sth.shop.util.MD5Code;

/**
 * ClassName:TestMd5Code
 * Package:cn.sth.shop.test
 * Description:
 *
 * @Date:2020/1/7 15:05
 * Author:沙天慧
 */
public class TestMd5Code {
    public static void main(String[] args) {
        System.out.println(new MD5Code().getMD5ofStr("java"));
    }
}
