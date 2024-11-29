package com.example.demo.sse;

import java.io.IOException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/sse")
public class SseController {

  // 서버에서 동적으로 알림을 전송하는 메서드
  @GetMapping("/notifications1")
  public SseEmitter sendNotifications() {
    SseEmitter emitter = new SseEmitter();

    // 별도의 스레드에서 알림 메시지 전송
    new Thread(() -> {
      try {
        // 특정 조건에 맞춰 알림 전송
        String[] notifications = {"새로운 사용자 등록!", "시스템 알림: 서버 상태 OK", "경고: 비밀번호 변경 필요"};
        for (String notification : notifications) {
          emitter.send(notification, MediaType.TEXT_PLAIN);
          Thread.sleep(1000); // 2초 간격으로 메시지 전송
        }
        //emitter.complete(); // 메시지 전송 후 완료
      } catch (IOException | InterruptedException e) {
        emitter.completeWithError(e); // 오류 처리
      }
    }).start();

    return emitter;
  }

  @RequestMapping("/api")
  @ResponseBody
  public SseEmitter test() throws IOException {
    SseEmitter sseEmitter = new SseEmitter();
    sseEmitter.send("asdfasdfasdfsadf", MediaType.TEXT_PLAIN);
    return sseEmitter;
  }
}
