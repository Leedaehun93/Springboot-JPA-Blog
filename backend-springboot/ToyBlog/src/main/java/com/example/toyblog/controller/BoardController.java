package com.example.toyblog.controller;

import com.example.toyblog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
 * 56강(블로그 프로젝트) - 글 상세보기
 * 58강(블로그 프로젝트) - 글 수정하기
 * ======================================
 */
@Controller
public class BoardController {

    @Autowired
    private BoardService boardService; // 의존성 주입(DI)

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
     * 게시글 상세보기 '/board/{id}' URL에 대한 GET 요청을 처리하는 메서드
     * URL에서 '{id}' 부분을 추출하여 해당 게시글의 상세 정보를 조회하고, 이를 뷰에 전달한다.
     *
     * @param id    URL 경로에서 추출된 게시글의 고유 식별자. 게시글을 조회하기 위해 사용된다.
     * @param model 스프링 MVC에서 제공하는 모델 객체. 뷰에 데이터를 전달하는데 사용된다.
     * @return "board/detail" 문자열을 반환하여 viewResolver에 의해 '/WEB-INF/views/board/detail.jsp' 페이지로 게시글 상세 정보가 렌더링된다.
     */
    @GetMapping("/board/{id}")
    public String findBy(@PathVariable int id, Model model) {
        model.addAttribute("board", boardService.글상세보기(id));
        return "board/detail";
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

    /**
     * updateForm 메서드
     * '/board/{id}/updateForm' URL로 GET 요청을 받으면
     * 특정 게시글(id)의 수정 폼을 렌더링하여 보여주는 역할을 한다.
     * @PathVariable 어노테이션을 사용하여 URL 경로에서 게시글의 고유 식별자(id)를 추출하고,
     * 이를 사용하여 boardService를 통해 해당 게시글의 상세 정보를 조회한다.
     * 조회된 게시글 정보는 모델에 'board'라는 이름으로 추가되어, 뷰에서 사용할 수 있다.
     *
     * @param id URL 경로에서 추출된 게시글의 고유 식별자
     * @param model 뷰로 데이터를 전달하는 데 사용되는 Model 객체
     * @return "board/updateForm" 문자열 반환, 뷰 리졸버에 의해 'board/updateForm' 뷰로 이동
     */
    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model) {
        model.addAttribute("board", boardService.글상세보기(id));
        return "board/updateForm";
    }

} // end of class