package com.example.demo.controller;

import com.example.demo.sse.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

  @Autowired
  NotificationService notificationService;

  @RequestMapping("/hello")
  public String hello(Model model) {
    return "hello";
  }

  @RequestMapping("/todoList")
  public String todoList(Model model) {
    return "todo/todoList";
  }

  @GetMapping("/main")
  public String main(ModelMap model) {
    return "main";
  }

/*  @GetMapping("/sse")
  @ResponseBody
  public SseEmitter streamSse() {
    SseEmitter emitter = new SseEmitter();

    // Create a new thread that sends events periodically
    new Thread(() -> {
      try {
        for (int i = 0; i < 10; i++) {
          // Send an event every 1 second
          emitter.send("Event #" + i, MediaType.TEXT_PLAIN);
          TimeUnit.SECONDS.sleep(1);
        }
        emitter.complete(); // End the event stream
      } catch (IOException | InterruptedException e) {
        emitter.completeWithError(e);
      }
    }).start();

    return emitter;
  }*/

}
