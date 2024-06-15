package cn.ibaochenyu.jzh_shop.webGlobal;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.Optional;

public final class UserContext {
    private static final ThreadLocal<UserInfoDTO> USER_THREAD_LOCAL = new TransmittableThreadLocal<>();


    public static void setUser(UserInfoDTO user) {//ok
        USER_THREAD_LOCAL.set(user);
    }

    public static void removeUser() {
        USER_THREAD_LOCAL.remove();
    }


}
