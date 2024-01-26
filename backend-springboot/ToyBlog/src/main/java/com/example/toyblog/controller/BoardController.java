package com.example.toyblog.controller;

import com.example.toyblog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ======================================
 * FileName : BoardController
 * Note :
 * 33강(블로그 프로젝트) - 메인화면 만들기
 * - 부트스트랩을 이용해 메인화면(Navbar, Footer 등) 만들기
 * - https://www.w3schools.com/bootstrap/bootstrap_ver.asp
 * 54강(블로그 프로젝트) - 글목록보기
 * - index.jsp에 JSTL forEach와 EL을 사용하여 게시글 목록 렌더링
 * - viewResolver를 통한 게시글 목록의 뷰 처리
 * 55강(블로그 프로젝트) - 글목록 페이징하기
 * ======================================
 */
@Controller
public class BoardController {

    @Autowired
    private BoardService boardService; // DI

    /**
     * 루트 URL에 대한 GET 요청을 처리하고, 게시글 목록을 모델에 추가한 후 index 뷰를 반환한다.
     * '/WEB-INF/views/index.jsp' 페이지로 게시글 목록을 렌더링하기 위해 viewResolver가 작동한다.
     *
     * @param model 스프링 MVC에서 제공하는 모델 객체로, 뷰에 데이터를 전달하는데 사용된다.
     * @return "index" 문자열을 반환하여 viewResolver가 처리할 뷰의 이름을 지정한다.
     */
    @GetMapping({"", "/"})
    public String index(Model model, @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("boards", boardService.글목록(pageable));
        return "index"; // MVC 설정 : /WEB-INF/views/index.jsp // viewResolver 작동
    }

    /**
     * saveForm 메서드
     * /board/saveForm URL로 GET 요청을 보내면
     * board/saveForm 뷰를 렌더링하여 보여주는 역할
     *
     * @return : "board/saveForm" 문자열 반환
     */
    @GetMapping("/board/saveForm") // USER 권한이 필요
    public String saveForm() {
        return "board/saveForm";
    }

} // end of class