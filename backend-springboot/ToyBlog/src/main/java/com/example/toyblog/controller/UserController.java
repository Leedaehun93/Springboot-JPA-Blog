package com.example.toyblog.controller;

import com.example.toyblog.model.KakaoProfile;
import com.example.toyblog.model.OAuthToken;
import com.example.toyblog.model.User;
import com.example.toyblog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

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
 * 65강(블로그 프로젝트) - 카카오 로그인 서비스 구현 완료
 * - 사용자의 선택적 동의에 따라 이메일 정보 접근이 제한됨. 이메일 필드 null 허용 변경으로 테스트(구현 X)
 * - ObjectMapper 사용 시 JSON 키의 스네이크 케이스를 Java 객체의 카멜 케이스 필드로 매핑 설정
 * - @Autowired UserService
 * - @Autowired AuthenticationManager
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

    @Value("${cos.key}")
    private String cosKey;

    /**
     * 스프링의 의존성 주입(Dependency Injection, DI)을 위한 어노테이션
     * AuthenticationManager의 인스턴스가 자동으로 주입
     */
    @Autowired
    private AuthenticationManager authenticationManager; // 의존성 주입(DI)

    /**
     * 스프링의 의존성 주입(Dependency Injection, DI)을 위한 어노테이션
     * UserService의 인스턴스가 자동으로 주입
     */
    @Autowired
    private UserService userService;  // 의존성 주입(DI)

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
    public String kakaoCallback(String code) { // Data를 리턴해주는 컨트롤러 함수

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
         * 개별적 코드를 작성함.
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
//        return "카카오 인증 완료 : 코드 값 :" + code +
//                "<br>" +
//                "카카오 토큰 요청 완료 : 토큰요청에 대한 응답:" + response;

        /**
         * JSON 데이터를 Java 객체로 변환하기 위한 메서드
         * JSON 처리를 위해 다양한 라이브러리(Gson, Json Simple 등)가 사용될 수 있지만,
         * 여기서는 Spring에서 기본 제공하는 ObjectMapper를 사용한다.
         *
         * ObjectMapper의 readValue 메서드는 JSON 문자열을 Java 객체로 변환한다.
         * 이 과정에서 발생할 수 있는 JsonMappingException과 JsonProcessingException을
         * 처리하기 위해 try-catch 블록을 사용한다.
         */
        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oauthToken = null;
        try {
            // JSON 문자열을 OAuthToken 객체로 변환
            oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
        } catch (JsonMappingException e) {
            // JSON 매핑 중 발생한 예외 처리
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            // JSON 처리 중 발생한 예외 처리
            e.printStackTrace();
        }

        System.out.println("카카오 엑세스 토큰 : " + oauthToken.getAccess_token()); // 서버 콘솔에 로그 출력 // 테스트 완료

/**
 * 카카오 로그인 API를 통해 사용자의 프로필 정보를 받는 컨트롤러 메서드
 *
 * 앞서 얻은 카카오 액세스 토큰을 사용하여 카카오 사용자 프로필 API에 요청을 보냄.
 * 스프링 프레임워크에서 제공하는 RestTemplate을 사용하여 카카오 사용자 프로필 API에 요청을 보내고,
 * 응답으로 받은 JSON 형태의 사용자 프로필 데이터를 처리함.
 *
 * @return 카카오 사용자 프로필 API 요청에 대한 응답
 */
        RestTemplate rt2 = new RestTemplate();

        // HttpHeader 오브젝트 생성
        // HTTP 요청을 위한 Header 설정
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        // HTTP 요청을 위해 Header와 Body를 하나의 객체로 결합
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 =
                new HttpEntity<>(headers2);

        // Http 요청하기 - POST방식으로 - 그리고 response2 변수의 응답 받음
        // 실제로 HTTP 요청을 수행하고 결과를 ResponseEntity 객체로 받음
        ResponseEntity<String> response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest2,
                String.class
        );

        // 엑세스 토큰을 문자열로 반환, 참고 : JSON Viewer 확장 프로그램을 사용하여 가독성을 높일 수 있음
//        return response.getBody(); // 엑세스 토큰 값 화면에 출력 // 테스트 완료

        /**
         * 카카오 API로부터 받은 응답의 본문
         *  JSON 형식의 문자열로, 카카오 사용자 프로필 정보를 포함하고 있다.
         */
        System.out.println(response2.getBody());  // 서버 콘솔에 로그 출력 // 테스트 완료

/**
 * JSON 데이터를 Java 객체로 변환하기 위한 메서드
 * JSON 처리를 위해 다양한 라이브러리(Gson, Json Simple 등)가 사용될 수 있지만,
 * 여기서는 Spring에서 기본 제공하는 ObjectMapper를 사용한다.
 *
 * ObjectMapper의 readValue 메서드는 JSON 문자열을 Java 객체로 변환한다.
 * 이 과정에서 발생할 수 있는 JsonMappingException과 JsonProcessingException을
 * 처리하기 위해 try-catch 블록을 사용한다.
 *
 * setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE) 메서드를 사용하여
 * 지원 TODO: JSON 키의 네이밍 전략을 스네이크 케이스(snake_case)로 설정한다.
 *               이는 JSON의 키와 Java 객체의 필드 이름이 다른 케이싱을 사용할 경우 유용하다.
 *               예를 들어, JSON의 'access_token' 키는 Java 객체의 'accessToken' 필드로 매핑된다.
 */
        ObjectMapper objectMapper2 = new ObjectMapper()
                /**
                 * 지원 TODO:  JSON의 키 네이밍 전략을 스네이크 케이스(snake_case)로 설정한다.
                 *                       이 설정은 JSON에서 오는 키가 스네이크 케이스로 되어 있고,
                 *                       Java 객체의 필드 이름이 카멜 케이스(camelCase)로 되어 있을 때 유용하다.
                 *                       예를 들어, JSON의 'access_token' 키는 Java 객체의 'accessToken' 필드로 자동 매핑된다.
                 */
                .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        KakaoProfile kakaoProfile = null;
        try {
            // JSON 문자열을 OAuthToken 객체로 변환
            kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
            /**
             * 지원 TODO: 변환된 객체를 다시 JSON 문자열로 출력해 확인한다.
             *                      ObjectMapper를 사용하여 kakaoProfile 객체를 JSON 형식의 문자열로 변환한다.
             *                      이렇게 변환된 문자열은 콘솔에 출력되어 객체의 데이터를 JSON 형태로 확인할 수 있다.
             *                      여기서는 새로운 ObjectMapper 인스턴스를 생성하고, writeValueAsString 메서드를 사용하여
             *                      Java 객체를 JSON 문자열로 변환한다. 변환된 JSON 문자열은 기본적으로 카멜 케이스(camelCase) 형식을 따른다.
             *                      이를 통해 변환된 객체의 내용을 콘솔에서 확인할 수 있다.
             */
            System.out.println((new ObjectMapper()).writeValueAsString(kakaoProfile));  // 서버 콘솔에 로그 출력( 카멜 케이스로 들어오는지) // 테스트 완료
        } catch (JsonMappingException e) {
            // JSON 매핑 중 발생한 예외 처리
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            // JSON 처리 중 발생한 예외 처리
            e.printStackTrace();
        }
        System.out.println("=======================");
        // User 오브젝트 : username, password, email

        /**
         * 카카오 로그인 API로부터 받은 사용자 프로필 정보 중 사용자의 고유 아이디(ID)를 콘솔에 출력한다.
         *  이 아이디는 카카오 사용자의 고유 식별자로, 각 사용자마다 고유한 값을 가진다.
         *  이 값을 사용하여 사용자를 구분하거나, 특정 사용자의 정보를 관리하는 데 사용할 수 있다.
         */
        System.out.println("카카오 아이디(번호) :" + kakaoProfile.getId());
        /**
         * 카카오 API를 통해 가져온 사용자 정보 중에서 이메일 정보는 사용자의 선택적 동의가 필요한 보안 정책에 따라 데이터를 받을 수 없다.
         *  따라서 기본적으로는 이메일 정보를 가져올 수 없으며, 사용자가 동의를 한 경우에만 접근 가능하다. (구현 X)
         */
//         System.out.println("카카오 이메일 주소 :" + kakaoProfile.getKakaoAccount().getEmail);

        System.out.println("=======================");
        System.out.println("블로그 서버 유저네임 : " + kakaoProfile.getId());

        // 임시 패스워드 // UUID란 -> 중복되지 않는 어떤 특정 값을 만들어내는 알고리즘
        UUID garbagePassword = UUID.randomUUID();
        System.out.println("블로그 서버 패스워드 : " + cosKey);
        System.out.println("=======================");

        User kakaoUser = User.builder()
                .username(kakaoProfile.getId().toString())
                .password(cosKey)
                .oauth("kakao") // oauth 값에 "kakao" 있으면 비밀번호 수정 할 수 가 없음.
                .build();
        // 가입자 혹은 비가입자 체크해서  관리
        User originUser = userService.회원찾기(kakaoUser.getUsername());
        if (originUser.getUsername() == null) {
            System.out.println("기존 회원이 아니기에 자동 회원가입을 진행합니다");
            userService.회원가입(kakaoUser);
        }
        System.out.println("자동 로그인을 진행합니다.");
        // 로그인 처리
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));
        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return response2.getBody(); // 엑세스 토큰 값 화면에 출력 // 테스트 완료
        return "redirect:/";
    }

    /**
     * 회원정보 페이지
     */
    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }

} // end of class