package cn.ibaochenyu.jzh_shop.webGlobal;

import com.alibaba.ttl.TransmittableThreadLocal;

public final class UserContext {
    private static final ThreadLocal<UserInfoDTOshow> USER_THREAD_LOCAL = new TransmittableThreadLocal<>();


    public static void setUser(UserInfoDTOshow user) {//ok
        USER_THREAD_LOCAL.set(user);
    }

    public static void removeUser() {
        USER_THREAD_LOCAL.remove();
    }


}
