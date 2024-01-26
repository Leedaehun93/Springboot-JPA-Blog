package com.example.toyblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.toyblog.model.User;
import com.example.toyblog.model.Board;
import com.example.toyblog.repository.BoardRepository;

/**
 * ======================================
 * FileName : BoardService
 * Author : DH.Lee
 * Date : 2024-01-26
 * Note : 53강(블로그 프로젝트) - 글쓰기 완료
 * 1)
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

} // end of class