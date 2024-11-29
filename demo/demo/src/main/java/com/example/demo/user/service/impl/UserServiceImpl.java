package com.example.demo.user.service.impl;

import com.example.demo.user.dao.UserRepository;
import com.example.demo.user.dto.AddUserRequest;
import com.example.demo.user.dto.UserDto;
import com.example.demo.user.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
// 스프링 시큐리티에서 사용자 정보를 가져오는 인터페이스
public class UserServiceImpl implements UserService{

  private final UserRepository userRepository;
/*
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
*/

  // 사용자 이름(email)으로 사용자 정보를 가져오는 메소드
/*
  @Override
  public UserDto loadUserByUsername(String email) {
    return userRepository.findByEmail(email);
  }
*/

//  public Long save(AddUserRequest dto){
//    return userRepository.save(UserDto.builder()
//        .email(dto.getEmail())
//        // 패스워드 암호화
//        .password(bCryptPasswordEncoder.encode(dto.getPassword()))
//        .name("홍길동")
//        .build()).getId();
//  }
}
