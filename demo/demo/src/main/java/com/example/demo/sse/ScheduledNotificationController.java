package com.example.demo.sse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class ScheduledNotificationController {

  private final ScheduledNotificationService scheduledNotificationService;

  public ScheduledNotificationController(ScheduledNotificationService scheduledNotificationService) {
    this.scheduledNotificationService = scheduledNotificationService;
  }

  @GetMapping(value = "/scheduled-notifications", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public SseEmitter streamScheduledNotifications() {
    return scheduledNotificationService.addEmitter(); // 클라이언트에게 emitter 반환
  }
}
