package com.tally.threadlocal;

import jakarta.servlet.http.HttpSession;

public class SessionContextHolder {

    private static final ThreadLocal<HttpSession> contextHolder;

    static {
        contextHolder = new ThreadLocal<>();
    }

    public static void set(final HttpSession session) {
        contextHolder.set(session);
    }

    public static HttpSession get() {
        return contextHolder.get();
    }

    public static void unset() {
        contextHolder.remove();
    }

}
