package com.example.toyblog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ======================================
 * FileName : BlogControllerTest
 * Author : DH.Lee
 * Date : 2023-12-12
 * Note :
 * 1)
 * ======================================
 */

// 스프링이 com.example.blog 패키지 이하를 스캔해서 모든 파일을 메모리에 new 하는 것은 아니다.
// 특정 어노테이션이 붙어있는 클래스 파일들을 new 해서 (IoC) 스프링 컨테이너에 관리해 준다.
@RestController
public class BlogControllerTest {

    // http://localhost:8080/test/hello
    @GetMapping("/test/hello")
    public String hello() {
        return "<h1>hello sping boot</h1>";
    }
}
