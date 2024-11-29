package com.example.demo.sse;

import java.time.Duration;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class NotificationService {

  // 알림 메시지를 주기적으로 생성하여 클라이언트에 전송
  public Flux<String> getNotifications() {
    return Flux.interval(Duration.ofSeconds(5))  // 5초마다 알림을 생성
        .map(tick -> generateDynamicMessage(tick));
  }

  // 동적으로 생성되는 알림 메시지
  private String generateDynamicMessage(long tick) {
    return "알림 메시지 " + tick + ": 서버에서 동적으로 생성된 알림입니다!";
  }
}