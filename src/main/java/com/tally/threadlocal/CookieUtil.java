package com.tally.threadlocal;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class CookieUtil {

    public static HttpSession getSession(HttpServletRequest request) {
        final Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies) {
            if ("JSESSIONID".equals(cookie.getName())) {
                return request.getSession(false);  // 기존 세션을 가져옴
            }
        }
        return request.getSession();
    }
}
