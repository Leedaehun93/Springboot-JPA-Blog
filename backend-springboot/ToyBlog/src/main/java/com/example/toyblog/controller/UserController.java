package com.example.toyblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ======================================
 * FileName : UserController
 * Author : DH.Lee
 * Date : 2024-01-04
 * Note : 34강(블로그 프로젝트) - 로그인, 회원가입 화면 만들기
 * 1) JSP 페이지를 헤더, 메인 콘텐츠, 푸터로 나누어 구성하기
 * 2) 회원가입, 로그인 폼 구성하기
 * ======================================
 */
@Controller
public class UserController {

    /**
     * 회원가입 페이지
     */
//    @GetMapping("joinForm")
//    public String joinForm(){
//        return "user/joinForm";
//    }

    /**
     * 로그인 페이지
     */
//    @GetMapping("loginForm")
//    public String loginForm(){
//        return "user/loginForm";
//    }

    /**
     * Note : 49강(블로그 프로젝트) - 스프링 시큐리티 로그인 페이지 커스터마이징
     * 스프링 시큐리티의 폼 로그인(form login) 기능을 사용자 정의 로그인 페이지로 변경
     * 기존 기본 로그인 방식은 주석 처리하여 참조용으로 보존
     */
    // 인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
    // 그냥 주소가 / 이면 index.jsp 허용
    // static 이하에 있는 /js/**, /css/**, /image/**
    /**
     * 회원가입 페이지
     */
    @GetMapping("/auth/joinForm")
    public String joinForm(){
        return "user/joinForm";
    }

    /**
     * 로그인 페이지
     */
    @GetMapping("/auth/loginForm")
    public String loginForm(){
        return "user/loginForm";
    }

} // end of class