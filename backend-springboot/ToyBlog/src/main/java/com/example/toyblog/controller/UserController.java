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
    @GetMapping("joinForm")
    public String joinForm(){
        return "user/joinForm";
    }

    /**
     * 로그인 페이지
     */
    @GetMapping("loginForm")
    public String loginForm(){
        return "user/loginForm";
    }

} // end of class
