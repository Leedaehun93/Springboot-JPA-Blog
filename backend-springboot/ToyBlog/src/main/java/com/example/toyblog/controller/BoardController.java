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
 *  https://www.w3schools.com/bootstrap/bootstrap_ver.asp
 * ======================================
 */
@Controller
public class BoardController {

    @GetMapping({"","/"})
    public String index() {
        // MVC 설정 : /WEB-INF/views/index.jsp
        return "index";
    }

} // end of class