package com.example.demo.sse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class NotificationController {

  private final NotificationService notificationService;

  public NotificationController(NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  // SSE 엔드포인트
  @GetMapping(value = "/notifications", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<String> streamNotifications() {
    return notificationService.getNotifications(); // 주기적으로 알림을 반환
  }
}