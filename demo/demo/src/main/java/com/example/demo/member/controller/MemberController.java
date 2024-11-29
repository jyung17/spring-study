package com.example.demo.member.controller;

import com.example.demo.member.service.MemberService;
import com.example.demo.member.dto.MemberDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MemberController {

  @Autowired
  private MemberService memberService;

  @GetMapping(value="/index")
  public String index(Model model) {

    List<MemberDto> memberList = memberService.getAllMember();
    System.out.println("size: " + memberList.size());
    System.out.println("memberList = " + memberList);

    model.addAttribute("memberList",memberList);
    return "index.html";
  }

  @GetMapping("/hello1")
  public String hello1() {
    return "hello.html";
  }

  @GetMapping("/hello")
  public String hello() {
    return memberService.hello();
  }
}
