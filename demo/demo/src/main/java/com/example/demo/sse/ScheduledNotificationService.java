package com.example.demo.sse;

import java.util.ArrayList;
import java.util.List;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
public class ScheduledNotificationService {

  // 클라이언트에게 메시지를 전달할 수 있는 emitter 저장
  private final List<SseEmitter> emitters = new ArrayList<>();

  // 클라이언트가 SSE를 연결하면 emitter 추가
  public SseEmitter addEmitter() {
    SseEmitter emitter = new SseEmitter();
    emitters.add(emitter);
    return emitter;
  }

  // 매 5초마다 알림을 전송
  @Scheduled(fixedRate = 5000) // 5초마다 실행
  public void sendScheduledNotification() {
    String message = "주기적인 알림 메시지: 서버에서 보내는 알림입니다!";
    for (SseEmitter emitter : emitters) {
      try {
        emitter.send(message);
      } catch (Exception e) {
        emitters.remove(emitter);  // 오류 발생 시 해당 emitter 제거
      }
    }
  }
}