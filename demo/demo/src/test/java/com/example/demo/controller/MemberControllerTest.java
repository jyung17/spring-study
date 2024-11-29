package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.member.controller.MemberController;
import com.example.demo.member.service.MemberService;
import com.example.demo.member.dto.MemberDto;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc // MockMvc를 주입하기 위한 애노테이 (Mock=가상)
class MemberControllerTest {
  @Autowired
  private MockMvc mvc;
  @Autowired
  MemberController memberController;
  @Autowired
  MemberService memberService;

  @BeforeEach
  public void beforeEach(){
    mvc = MockMvcBuilders.standaloneSetup(memberController)
        .addFilter(new CharacterEncodingFilter("utf-8", true))
        .alwaysExpect(MockMvcResultMatchers.status().isOk())
        .build();
  }

  @Test
  void test() throws Exception{

    mvc.perform(get("/hello"))
        .andExpect(status().isOk())
        .andDo(print());
  }

  @Test
  void test1() throws Exception {
    List<MemberDto> memberList = memberService.getAllMember();
    System.out.println("memberList = " + memberList);
  }

  @Test
  void builderTest() throws Exception {
    MemberDto memberDto = MemberDto.builder()
                                .userName("홍길동")
                                .userId("hong").build();
    Assert.assertEquals(memberDto.getUserName(), "홍길동");
    Assert.assertEquals(memberDto.getUserId(), "hong");
  }
}