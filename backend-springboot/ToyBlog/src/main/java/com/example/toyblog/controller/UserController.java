package com.example.toyblog.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

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
 * 64강(블로그 프로젝트) - 카카오 로그인 엑세스토큰 받기
 * - 스프링 프레임워크에서 제공하는 RestTemplate을 사용하여 카카오 로그인 API에 액세스 토큰을 요청하는 코드를 사용
 * - 카카오 인증 요청, 카카오 토큰 인증 요청
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
     * 카카오 로그인 페이지
     */

    /**
     * 카카오 로그인 API를 통해 액세스 토큰을 요청하는 컨트롤러 메서드
     * 사용자가 카카오 로그인을 성공하면 카카오 서버에서 리디렉트 URI로 authorization code를 보냄.
     * 이 코드를 사용하여 카카오 서버에 액세스 토큰을 요청함.
     * 스프링 프레임워크에서 제공하는 RestTemplate을 사용하여 카카오 로그인 API에 액세스 토큰을 요청하는 코드를 사용
     *
     * @param code 카카오 인증 서버로부터 받은 authorization code
     * @return 카카오 액세스 토큰 요청에 대한 응답
     */
    @GetMapping("/auth/kakao/callback")
    public @ResponseBody String kakaoCallback(String code) { // Data를 리턴해주는 컨트롤러 함수

        // HTTP 요청을 보내기 위한 RestTemplate
        // POST방식으로 key=value 데이터를 요청(카카오쪽으로)
        // 추가적으로 Retrofit2, OkHttp 등 다른 HTTP 클라이언트 라이브러리 사용 가능
        RestTemplate rt = new RestTemplate();

        // HttpHeader 오브젝트 생성
        // HTTP 요청을 위한 Header 설정
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpBody 오브젝트 생성
        // HTTP 요청에서 전달할 파라미터 설정
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        /**
         * params 값은 변수에 담아서 진행해야하나 교육의 목적으로
         * 개별적으로 코드를 작성함.
         */
        params.add("grant_type", "authorization_code"); // ID 토큰을 발급한 인증 기관 정보 고정 키
        params.add("client_id", "a86f4b070d222923b2df1bdbd8c805c8"); // 카카오 앱 REST API 키
        params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback"); // 리디렉트 URI
        params.add("code", code); // 카카오 인증 서버로부터 받은 authorization code

        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        // HTTP 요청을 위해 Header와 Body를 하나의 객체로 결합
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(params, headers);

        // Http 요청하기 - POST방식으로 - 그리고 response 변수의 응답 받음
        // 실제로 HTTP 요청을 수행하고 결과를 ResponseEntity 객체로 받음
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );
        // 카카오 액세스 토큰 요청에 대한 응답을 리턴
//        return "카카오 인증 완료 : 코드 값 :" + code;
        return "카카오 토큰 요청 완료 : 토큰요청에 대한 응답:" + response;
    }

    /**
     * 회원정보 페이지
     */
    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }

} // end of class
