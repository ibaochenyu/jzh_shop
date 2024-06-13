package cn.ibaochenyu.jzh_shop;

import com.google.common.base.Strings;

public class CacheUtil {
    /**
     * 判断结果是否为空或空的字符串
     *
     * @param cacheVal
     * @return
     */
    public static boolean isNullOrBlank(Object cacheVal) {//ok
        return cacheVal == null || (cacheVal instanceof String && Strings.isNullOrEmpty((String) cacheVal));
    }
}
