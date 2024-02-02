package com.example.toyblog.test;

import com.example.toyblog.model.Board;
import com.example.toyblog.model.Reply;
import com.example.toyblog.repository.BoardRepository;
import com.example.toyblog.repository.ReplyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ======================================
 * FileName : ReplyControllerTest
 * Note :
 * 67강(블로그 프로젝트) - 댓글 목록 뿌리기
 * - 댓글 Reply 무한참조방지 (엔티티 간 순환 참조를 방지) 테스트 코드
 * ======================================
 */
@RestController
public class ReplyControllerTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @GetMapping("/test/board/{id}")
    public Board getBoard(@PathVariable int id) {
        return boardRepository.findById(id).get(); // jackson 라이브러리 (오브젝트를 json으로 리턴) => 모델의 getter를 호출
    }

    @GetMapping("/test/reply")
    public List<Reply> getReply() {
        return replyRepository.findAll(); // jackson 라이브러리 (오브젝트를 json으로 리턴) => 모델의 getter를 호출
    }
} // end of class