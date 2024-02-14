package com.example.toyblog.controller.api;

import com.example.toyblog.config.auth.PrincipalDetail;
import com.example.toyblog.model.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.example.toyblog.dto.ResponseDto;
import com.example.toyblog.model.Board;
import com.example.toyblog.service.BoardService;
/**
 * ======================================
 * FileName : BoardApiController
 * Note :
 * 53강(블로그 프로젝트) - 글쓰기 완료
 * 57강(블로그 프로젝트) - 글 삭제하기
 * 58강(블로그 프로젝트) - 글 수정하기
 * 68강(블로그 프로젝트) - 댓글 작성하기
 * ======================================
 */

/**
 * BoardApiController : RESTful 웹 서비스의 컨트롤러로 클라이언트
 * (예: 웹 페이지에서의 AJAX 요청)로부터 사용자 정보를 받아서 처리하고,
 * 요청의 성공 여부를 ResponseDto를 통해 클라이언트에게 알려주는 역할
 * ResponseDto는 요청 처리 결과를 나타내는 'status' 필드와 결과 데이터를 담는 'data' 필드를 포함한다.
 */
@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService; // 의존성 주입(DI)

    /**
     * 게시글을 저장하는 API 엔드포인트
     * @RequestBody를 통해 요청 본문에 담긴 Board 객체를 받고,
     * @AuthenticationPrincipal을 통해 인증된 사용자의 세부 정보를 받아 글쓰기 로직을 수행한다.
     * 처리 결과를 ResponseDto 객체로 반환한다.
     *
     * @param board 클라이언트로부터 받은 게시글 데이터
     * @param principalDetail 인증된 사용자의 세부 정보
     * @return 처리 결과를 나타내는 ResponseDto 객체
     */
    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        boardService.글쓰기(board, principalDetail.getUser());
        // 자바오브젝트를 JSON으로 변환해서 리턴(Jackson이 실행)
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 요청 처리 결과와 데이터를 포함하는 ResponseDto 반환 // 200 정상 실행
    }

    /**
     * 주어진 id에 해당하는 게시글을 삭제하는 API 엔드포인트
     * @PathVariable을 통해 URL 경로에서 게시글의 id를 받아와 해당 게시글을 삭제한다.
     * 처리 결과를 ResponseDto 객체로 반환한다.
     *
     * @param id 삭제하려는 게시글의 고유 식별자
     * @return 처리 결과를 나타내는 ResponseDto 객체
     */
    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable int id) {
        boardService.글삭제하기(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 요청 처리 결과와 데이터를 포함하는 ResponseDto 반환 // 200 정상 실행
    }

    /**
     * 주어진 id에 해당하는 게시글을 수정하는 API 엔드포인트
     * @PathVariable을 통해 URL 경로에서 게시글의 id를 받아오고, @RequestBody를 통해 수정할 게시글 데이터를 받는다.
     * boardService의 글수정하기 메서드를 호출하여 게시글을 수정한 후,
     * 처리 결과를 ResponseDto 객체로 반환한다.
     *
     * @param id 수정하려는 게시글의 고유 식별자.
     * @param board 클라이언트로부터 받은 수정할 게시글 데이터
     * @return 처리 결과를 나타내는 ResponseDto 객체
     */
    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board){
//        System.out.println("BoardApiController : update : id : "+ id) ; // 서버 콘솔에 로그 출력 // 테스트 완료
//        System.out.println("BoardApiController : update : board : "+ board.getTitle()); // 서버 콘솔에 로그 출력 // 테스트 완료
//        System.out.println("BoardApiController : update : board : "+ board.getContent()); // 서버 콘솔에 로그 출력 // 테스트 완료
        boardService.글수정하기(id, board); // 수정하려는 게시글 ID와 데이터를 서비스 계층에 전달
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 요청 처리 결과와 데이터를 포함하는 ResponseDto 반환 // 200 정상 실행
    }

    /**
     * 댓글 저장을 위한 API 엔드포인트
     * 클라이언트로부터 받은 댓글 데이터를 처리하여 데이터베이스에 저장한다.
     * URL 경로에서 게시글의 ID를 추출하고, 요청 본문에서 댓글 데이터를 받아 처리한다.
     * 인증된 사용자의 정보는 @AuthenticationPrincipal을 통해 받아 사용한다.
     * 처리 완료 후, 요청의 성공 여부를 ResponseDto를 통해 반환한다.
     *
     * @param boardId URL 경로에서 추출된 게시글의 ID
     * @param reply 요청 본문에서 받아온 댓글 데이터
     * @param principal 인증된 사용자의 정보를 담고 있는 PrincipalDetail 객체
     * @return 처리 결과를 나타내는 ResponseDto 객체. 요청이 성공적으로 처리되었음을 나타낸다.
     */
    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDto<Integer> replySave(@PathVariable int boardId, @RequestBody Reply reply, @AuthenticationPrincipal PrincipalDetail principal) {
        boardService. 댓글쓰기(principal.getUser(), boardId, reply);
        // 자바오브젝트를 JSON으로 변환해서 리턴(Jackson이 실행)
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 요청 처리 결과와 데이터를 포함하는 ResponseDto 반환 // 200 정상 실행
    }

} // end of class