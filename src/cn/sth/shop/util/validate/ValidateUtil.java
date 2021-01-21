package cn.sth.shop.util.validate;

/**
 * ClassName:ValidateUtil
 * Package:cn.sth.shop.util.validate
 * Description:
 *
 * @Date:2020/1/8 14:42
 * Author:沙天慧
 */
public class ValidateUtil {
    /**
     * 验证数据是否为空
     * @param data
     * @return 空返回false，否则返回true
     */
    public static boolean validateEmpty(String data){
        if (data==null||"".equals(data)){
            return false;
        }
        return true;
    }

    /**
     * 进行数据的正则验证操作
     * @param data
     * @param regex
     * @return
     */
    public static boolean validateRegex(String data,String regex){
        if(validateEmpty(data)){
            return data.matches(regex);
        }
        return false;
    }

    /**
     * 验证数据是否相同，不区分大小写
     * @param dataA
     * @param dataB
     * @return
     */
    public static boolean validateSame(String dataA,String dataB){
        if(validateEmpty(dataA)&&validateEmpty(dataB)){
            return dataA.equalsIgnoreCase(dataB);
        }
        return false;
    }
}
