package com.example.demo.user.dao;

import com.example.demo.member.dto.MemberDto;
import com.example.demo.user.dto.UserDto;
import java.util.List;
import javax.swing.text.html.Option;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserRepository {
  List<UserDto> findAll();
  UserDto findByEmail(String email);

  UserDto save(UserDto userDto);
}