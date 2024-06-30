package com.tally.threadlocal;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.logging.Logger;

@RestController
public class OrderController {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @PostMapping("/api/v1/orders")
    public void createOrder() {

        // ThreadLocal에서 사용자 정보 조회
        final HttpSession httpSession = SessionContextHolder.get();

        logger.info("[주문 요청] userId = " + httpSession.getId());
        // biz logic..
        logger.info("[주문 완료] userId = " + httpSession.getId() +
                ", orderId = " + UUID.randomUUID());
    }

}
