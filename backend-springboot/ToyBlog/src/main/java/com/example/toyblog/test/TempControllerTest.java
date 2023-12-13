package com.example.toyblog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ======================================
 * FileName : TempControllerTest
 * Author : DH.Lee
 * Date : 2023-12-13
 * Note : Springboot - 나만의 블로그 만들기 17강 - yml설정하기
 * 스프링 부트 애플리케이션에서 정적 파일과 JSP 파일을 제공(처리) 하는 방법을 설명하는 컨트롤러
 * 1) 파일리턴 기본 경로와 풀 경로를 잘 확인해야한다.
 * 2) 정적 파일은 src/main/resources/static 경로에 위치하며,
 *    html, css, js, img 등을 자동으로 제공한다.
 * 3) 스프링 부트는 JSP를 지원하지만(브라우저가 직접적으로 경로를 인식못함), 내장 서버에서 사용하기 위해서는 추가 설정이 필요하다.
 *    JSP 파일은 webapp/WEB-INF/views 경로에 위치해야 하며,
 *    JAR 패키징 방식보다는 WAR 패키징을 사용할 때 더 잘 동작한다.
 * ======================================
 */
@Controller // 데이터가 아닌 파일을 리턴하기 위해서 @RestController 가 아닌 @controller 을 사용함
// @RestController RESTful 웹 서비스를 만들 때 사용되며, 주로 데이터(예: JSON) 를 반환한다.
public class TempControllerTest {

    // TODO: 정적인 HTML 파일을 반환하는 GetMapping 메소드
    // TODO: 기본 경로 http://localhost:8000/blog/temp/home
    @GetMapping("/temp/home")
    public String tempHome() {
        System.out.println("tempHome");
        // 리턴 경로는 정적 파일 경로를 기반으로 함: src/main/resources/static/home.html
        // 리턴명 : /home.html
        return "home.html";
    }


    // TODO: 정적인 이미지 파일을 반환하는 GetMapping 메소드
    @GetMapping("/temp/img")
    public String tempImg() {
        // 리턴 경로는 정적 파일 경로를 기반으로 함: src/main/resources/static/Ivan_R2.jpg
        return "Ivan_R2.jpg";
    }


    // TODO: 동적인 JSP 파일을 반환하는 GetMapping 메소드
    @GetMapping("/temp/jsp")
    public String tempJsp() {
        // 스프링 MVC가 처리하는 뷰 이름을 리턴
        // prefix: /WEB-INF/views/
        // suffix: .jsp
        // 리턴명: test
        return "test";
        // 실제 JSP 파일 위치: webapp/WEB-INF/views/test.jsp
    }
}
