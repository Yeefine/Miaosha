package com.myproject.miaosha.access;

import com.myproject.miaosha.domain.MiaoshaUser;

public class UserContext {

    // 每个线程之间独立的缓存
    private static ThreadLocal<MiaoshaUser> userHolder = new ThreadLocal<MiaoshaUser>();

    public static void setUser(MiaoshaUser user) {
        userHolder.set(user);
    }

    public static MiaoshaUser getUser() {
        return userHolder.get();
    }
}
