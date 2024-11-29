package com.example.demo.sse.service;

import com.example.demo.sse.dto.NotificationDTO;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
@RequiredArgsConstructor
@Log4j2
public class NotificationService {

  static Map<String, SseEmitter> sseEmitterMap = new ConcurrentHashMap<>();

  public SseEmitter subscribe(String email, String lastEventId) {
    SseEmitter emitter = new SseEmitter();

    // Create a new thread that sends events periodically
    new Thread(() -> {
      try {
        emitter.send("Event #", MediaType.TEXT_PLAIN);
        TimeUnit.SECONDS.sleep(1);
        emitter.complete(); // End the event stream
      } catch (IOException | InterruptedException e) {
        emitter.completeWithError(e);
      }
    }).start();

    return emitter;
  }

  public SseEmitter connect(final String userId, final String lastEventId)
      throws IOException, InterruptedException {
    // 매 연결마다 고유의 이벤트 ID를 부여
    String eventId = userId + "_" + System.currentTimeMillis();

    // SseEmitter 인스턴스 생성 후 Map에 저장
    SseEmitter emitter = new SseEmitter(System.currentTimeMillis());
    sseEmitterMap.put(eventId, emitter);

    // 이벤트 전송 시
    emitter.onCompletion(() -> {
      log.info("onCompletion callback");
    });

    // 이벤트 스트림 연결 끊길 시
    emitter.onTimeout(() -> {
      log.info("onTimeout callback");
      emitter.complete();
    });

    // 첫 연결 시 503 Service Unavailable 방지용 더미 Event 발송
    //emitter.send("알림 서버 연결 성공. [userId: " + userId + "] - " + eventId, MediaType.TEXT_PLAIN);
    //String data = "알림 서버 연결 성공. [userId: " + userId + "] - " + eventId;
    NotificationDTO notificationDTO = new NotificationDTO();
    notificationDTO.setEventId(eventId);
    notificationDTO.setData("알림 서버 연결 성공. [userId: " + userId + "] - " + eventId);
    emitter.send(SseEmitter.event().name("message").id(eventId).data(notificationDTO));

    // 첫 연결 시 503 Service Unavailable 방지용 더미 Event 발송
    //sendToClient(eventId, emitter, "알림 서버 연결 성공. [memberId = " + userId + "]");

    System.out.println("sseEmitterMap = " + sseEmitterMap);

    //send(eventId);
    return emitter;
  }

  public void send(NotificationDTO notificationDTO) {
    String eventId = notificationDTO.getEventId();

    sseEmitterMap.forEach((key, emitter) -> {
      try {
        emitter.send(SseEmitter.event().name("message").id(eventId).data(notificationDTO));
        // emitter.send("Event Send by All: " + eventId + " : " +  data, MediaType.TEXT_PLAIN);
        //sendToClient(eventId, emitter, "data!!!");
      } catch (Exception e) {
        sseEmitterMap.remove(eventId);
        log.error("Failed to send notification", e);
      }
    });
  }

  public void sendById(NotificationDTO notificationDTO) throws IOException {
    String eventId = notificationDTO.getEventId();
    String data = notificationDTO.getData();

    try {
      SseEmitter emitter = sseEmitterMap.get(eventId);
      emitter.send("Event Send by ID: " + eventId + " : " + data, MediaType.TEXT_PLAIN);
    } catch (Exception e) {
      sseEmitterMap.remove(eventId);
      log.error("Failed to send notification", e);
    }
  }

  public void sendToJson(NotificationDTO notificationDTO) throws IOException {
    String eventId = notificationDTO.getEventId();
    String data = notificationDTO.getData();
    System.out.println("sseEmitterMap = " + sseEmitterMap);
    try {
      SseEmitter emitter = sseEmitterMap.get(eventId);
      emitter.send(SseEmitter.event().name("message").id(eventId).data(notificationDTO));
    } catch (IOException e) {
      throw new RuntimeException("알림 서버 연결 오류");
    }
  }
}
