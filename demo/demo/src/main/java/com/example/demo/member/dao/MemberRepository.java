package com.example.demo.member.dao;

import com.example.demo.member.dto.MemberDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MemberRepository {
  List<MemberDto> getAllMember();
}