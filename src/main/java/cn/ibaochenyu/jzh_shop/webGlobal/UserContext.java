package cn.ibaochenyu.jzh_shop.webGlobal;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.Optional;

public final class UserContext {
    private static final ThreadLocal<UserInfoDTOshow> USER_THREAD_LOCAL = new TransmittableThreadLocal<>();


    public static void setUser(UserInfoDTOshow user) {//ok
        USER_THREAD_LOCAL.set(user);
    }

    public static void removeUser() {
        USER_THREAD_LOCAL.remove();
    }

    /**
     * 获取上下文中用户 ID
     *
     * @return 用户 ID
     */
    public static String getUserId() {//ok //为什么要用那么复杂的形式----可能是怕空了，扔出异常吧
        UserInfoDTOshow userInfoDTO = USER_THREAD_LOCAL.get();
        return Optional.ofNullable(userInfoDTO).map(UserInfoDTOshow::getUserId).orElse(null);
    }


}
