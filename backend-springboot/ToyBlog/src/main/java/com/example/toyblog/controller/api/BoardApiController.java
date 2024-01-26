package com.example.toyblog.controller.api;

import com.example.toyblog.auth.PrincipalDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.toyblog.dto.ResponseDto;
import com.example.toyblog.model.Board;
import com.example.toyblog.service.BoardService;

/**
 * ======================================
 * FileName : BoardApiController
 * Author : DH.Lee
 * Date : 2024-01-26
 * Note : 53강(블로그 프로젝트) - 글쓰기 완료
 * ======================================
 */

/**
 * BoardApiController : RESTful 웹 서비스의 컨트롤러로 클라이언트(예: 웹 페이지에서의 AJAX 요청)로부터 사용자 정보를 받아서 처리하고,
 * 요청의 성공 여부를 ResponseDto를 통해 클라이언트에게 알려주는 역할
 * ResponseDto는 요청 처리 결과를 나타내는 'status' 필드와 결과 데이터를 담는 'data' 필드를 포함한다.
 */
@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService; // DI

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        boardService.글쓰기(board, principalDetail.getUser());
        // 자바오브젝트를 JSON으로 변환해서 리턴(Jackson이 실행)
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 요청 처리 결과와 데이터를 포함하는 ResponseDto 반환
    }

} // end of class