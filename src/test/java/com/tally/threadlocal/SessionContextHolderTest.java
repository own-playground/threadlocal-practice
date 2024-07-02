package com.tally.threadlocal;

import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SessionContextHolderTest {

    @Test
    @DisplayName("스레드 풀 환경에서 ThreadLocal을 지우지 않아 문제가 발생하는 예시")
    void removeWithThreadPool() {

        final int threadCount = 5;     // 실행할 작업의 총 개수
        final int threadPoolSize = 3;  // 스레드 풀의 스레드 개수

        ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);  // 3개의 스레드를 가진 스레드 풀 생성

        for (int i = 0; i < threadCount; i++) {

            // given: 세션 생성
            HttpSession session = mock(HttpSession.class);
            when(session.getId()).thenReturn(UUID.randomUUID().toString());

            executorService.submit(() -> {

                final String threadName = Thread.currentThread().getName().substring(7);

                // when & then: ThreadLocal 정보 출력
                System.out.printf("[%s]  started: SESSION ID = %s%n", threadName, SessionContextHolder.get());
                SessionContextHolder.set(session);
                System.out.printf("[%s] finished: SESSION ID = %s%n", threadName, SessionContextHolder.get());
            });
        }
        // Executor 서비스 종료
        executorService.shutdown();
    }

    @Test
    @DisplayName("스레드 풀 환경에서 ThreadLocal을 지웠을 때 문제가 발생하지 않는 예시")
    void removeNotWithThreadPool() {

        final int threadCount = 5;     // 실행할 작업의 총 개수
        final int threadPoolSize = 3;  // 스레드 풀의 스레드 개수

        ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);  // 3개의 스레드를 가진 스레드 풀 생성

        for (int i = 0; i < threadCount; i++) {

            // given: 세션 생성
            HttpSession session = mock(HttpSession.class);
            when(session.getId()).thenReturn(UUID.randomUUID().toString());

            executorService.submit(() -> {

                final String threadName = Thread.currentThread().getName().substring(7);

                // when & then: ThreadLocal 정보 출력
                System.out.printf("[%s]  started: SESSION ID = %s%n", threadName, SessionContextHolder.get());
                SessionContextHolder.set(session);
                System.out.printf("[%s] finished: SESSION ID = %s%n", threadName, SessionContextHolder.get());
                SessionContextHolder.unset();
            });
        }
        // Executor 서비스 종료
        executorService.shutdown();
    }

}