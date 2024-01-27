package com.example.toyblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ======================================
 * FileName : UserController
 * Note :
 * 33강(블로그 프로젝트) - 메인화면 만들기
 * - 부트스트랩을 이용해 메인화면(Navbar, Footer 등) 만들기
 * 34강(블로그 프로젝트) - 로그인, 회원가입 화면 만들기
 * - JSP 페이지를 헤더, 메인 콘텐츠, 푸터로 나누어 구성하기
 * - 회원가입, 로그인 폼 구성하기
 * 46강(블로그 프로젝트) - 프로젝트 전통적인 방식의 로그인 방법
 * - 로그인 기능 구현을 위한 세션 관리 추가
 * - 사용자 이름과 비밀번호를 사용하여 로그인
 * - 로그인 성공 시 사용자 정보를 세션에 저장
 * - JSP 페이지에서 사용자의 로그인 상태에 따라
 * 다른 네비게이션 바 항목을 표시하는 로직을 구현
 * (JSTL 태그를 사용하여 로그인 여부에 따라 조건부로
 * HTML 내용 렌더링(로그인, 회원가입), (글쓰기, 회원정보, 로그아웃)
 * 49강(블로그 프로젝트) - 스프링 시큐리티 기반 로그인 페이지 커스터마이징
 * - 스프링 시큐리티의 폼 로그인(form login) 기능을 사용자 정의 로그인 페이지로 변경
 * - 기존 기본 로그인 방식은 주석 처리하여 참조용으로 보존
 * 50강(블로그 프로젝트) - 비밀번호 해시 후 회원가입하기
 * 52강(블로그 프로젝트) - 스프링 시큐리티 로그인
 * - 스프링 시큐리티의 인증 매니저를 구성하여 로그인 프로세스를 이용한다.
 * 60강(블로그 프로젝트) - 회원수정 1
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
    public String joinForm() {
        return "user/joinForm";
    }

    /**
     * 로그인 페이지
     */
    @GetMapping("/auth/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    /**
     * 회원정보 페이지
     */
    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }

} // end of class
