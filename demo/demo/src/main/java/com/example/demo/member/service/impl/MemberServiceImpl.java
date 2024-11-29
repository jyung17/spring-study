package com.example.demo.member.service.impl;

import com.example.demo.member.dao.MemberRepository;
import com.example.demo.member.service.MemberService;
import com.example.demo.member.dto.MemberDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

  @Autowired
  private MemberRepository memberRepository;

  @Override
  public List<MemberDto> getAllMember() {
    List<MemberDto> allMember = memberRepository.getAllMember();
    return allMember;
  }

  @Override
  public String hello() {
    return "hello Service";
  }
}
