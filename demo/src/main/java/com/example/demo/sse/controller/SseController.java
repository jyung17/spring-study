package com.example.demo.sse.controller;

import com.example.demo.sse.dto.NotificationDTO;
import com.example.demo.sse.service.NotificationService;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class SseController {

  @Autowired
  NotificationService notificationService;

  @GetMapping("/sse")
  @ResponseBody
  public SseEmitter streamSse() {
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
/*
  @GetMapping(value = "/subscribe/{email}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  @ResponseBody
  public SseEmitter subscribe(@PathVariable String email, @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId) {
    return notificationService.subscribe(email, lastEventId);
  }*/

  @GetMapping(value = "/connect/{userId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  @ResponseBody
  public SseEmitter connect(@PathVariable String userId, @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId)
      throws IOException, InterruptedException {
    return notificationService.connect(userId, lastEventId);
  }

  @PostMapping(value = "/sendAll")
  @ResponseBody
  public void send(@RequestBody NotificationDTO notificationDTO) {
    notificationService.send(notificationDTO);
  }

  @PostMapping(value = "/sendById")
  @ResponseBody
  public void sendById(@RequestBody NotificationDTO notificationDTO) throws IOException {
    notificationService.sendById(notificationDTO);
  }

  @PostMapping(value = "/sendToJson")
  @ResponseBody
  public void sendToJson(@RequestBody NotificationDTO notificationDTO) throws IOException {
    notificationService.sendToJson(notificationDTO);
  }
}
