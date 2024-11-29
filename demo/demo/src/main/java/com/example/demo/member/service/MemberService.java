package com.example.demo.member.service;

import com.example.demo.member.dto.MemberDto;
import java.util.List;

public interface MemberService {
  public List<MemberDto> getAllMember();
  public String hello();
}
