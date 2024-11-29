package com.example.demo.sse2;

import com.example.demo.sse.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
public class NotificationController {
  private final NotificationService notificationService;

  @ApiOperation(value = "알림 구독", notes = "알림을 구독한다.")
  @GetMapping(value = "/subscribe", produces = "text/event-stream")
  @ResponseStatus(HttpStatus.OK)
  public SseEmitter subscribe(@AuthenticationPrincipal MemberDetails memberDetails,
      @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId) {
    return notificationService.subscribe(memberDetails.getId(), lastEventId);
  }
}