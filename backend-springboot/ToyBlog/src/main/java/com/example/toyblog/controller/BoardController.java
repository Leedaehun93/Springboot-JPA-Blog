package com.example.toyblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ======================================
 * FileName : BoardController
 * Author : DH.Lee
 * Date : 2024-01-04
 * Note : 33강(블로그 프로젝트) - 메인화면 만들기
 * 부트스트랩을 이용해 메인화면(Navbar, Footer 등) 만들기
 * https://www.w3schools.com/bootstrap/bootstrap_ver.asp
 * ======================================
 */
@Controller
public class BoardController {

    @GetMapping({"", "/"})
    public String index() {
        // MVC 설정 : /WEB-INF/views/index.jsp
        return "index";
    }

    /**
     * Note : 53강(블로그 프로젝트) - 글쓰기 완료
     */
    // USER 권한이 필요

    /**
     * saveForm 메서드
     * /board/saveForm URL로 GET 요청을 보내면
     * board/saveForm 뷰를 렌더링하여 보여주는 역할
     *
     * @return : "board/saveForm" 문자열 반환
     */
    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

} // end of class