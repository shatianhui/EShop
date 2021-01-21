package cn.sth.shop.exception;

/**
 * ClassName:UnEnoughAmountException
 * Package:cn.sth.shop.exception
 * Description:
 *
 * @Date:2020/1/21 13:49
 * Author:沙天慧
 */
public class EmptyShopcarException extends Exception {
    public EmptyShopcarException(String msg){
        super(msg);
    }
}
