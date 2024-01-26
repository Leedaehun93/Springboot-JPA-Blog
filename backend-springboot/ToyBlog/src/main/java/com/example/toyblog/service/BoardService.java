package com.example.toyblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.toyblog.model.User;
import com.example.toyblog.model.Board;
import com.example.toyblog.repository.BoardRepository;
import java.util.List;

/**
 * ======================================
 * FileName : BoardService
 * Note :
 * 53강(블로그 프로젝트) - 글쓰기 완료
 * - summernote 스크립트를 사용하여 글쓰기 기능을 구현
 * 54강(블로그 프로젝트) - 글목록보기
 * 55강(블로그 프로젝트) - 글목록 페이징하기
 * 56강(블로그 프로젝트) - 글 상세보기
 * ======================================
 */

/**
 * BoardService 클래스는 게시판 관련 비즈니스 로직을 처리한다.
 * BoardRepository와 연결되어 게시글 데이터를 관리하며 데이터베이스와의 상호작용을 담당한다.
 */
@Service
public class BoardService {

    /**
     * 스프링의 의존성 주입(Dependency Injection, DI)을 위한 어노테이션
     * 게시글 데이터 접근을 위한 JPA 리포지토리
     * BoardRepository의 인스턴스가 자동으로 주입된다.
     */
    @Autowired
    private BoardRepository boardRepository; // DI

    /**
     * 글쓰기 메서드 : 데이터를 받아 글쓰기 로직을 처리한다.
     * 트랜잭션 관리를 통해 데이터베이스 작업의 일관성과 무결성을 보장한다.
     *
     * boardRepository.save(board)는 Board 객체를 데이터베이스에 저장하는 JPA 리포지토리 메서드이다.
     * 이 메서드는 객체를 영구적으로 저장하거나 이미 존재하는 객체를 업데이트한다.
     * @Transactional 어노테이션은 메서드 내의 모든 데이터베이스 작업을 하나의 트랜잭션으로 묶는다.
     *
     * @param board 게시글 객체
     */
    @Transactional
    public void 글쓰기(Board board, User user) { // title, content
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board); // 게시글 데이터베이스에 저장
    }

    /**
     * 페이지네이션을 적용하여 게시글 목록을 조회하는 메서드
     * Pageable 객체를 매개변수로 받아, 지정된 페이지 크기와 정렬 순서에 따라 게시글 페이지를 반환한다.
     *
     * @param pageable 페이지네이션 정보를 담고 있는 Pageable 객체
     * @return 페이지네이션 적용된 게시글 목록이 담긴 Page<Board> 객체
     */
    public Page<Board> 글목록(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    /**
     * 주어진 id에 해당하는 게시글을 조회하는 메서드
     * id에 해당하는 게시글이 존재하지 않을 경우, IllegalArgumentException 예외를 발생시킨다.
     *
     * @param id 조회하려는 게시글의 고유 식별자.
     * @return id에 해당하는 Board 객체. 게시글이 존재하지 않을 경우 예외 발생
     * @throws IllegalArgumentException 게시글이 존재하지 않을 때 발생하는 예외
     */
    public Board 글상세보기(int id) {
        return boardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 상세보기 실패:아이디를 찾을 수 없습니다.");
                });
    }

} // end of class