package com.example.toyblog.service;

import com.example.toyblog.dto.ReplySaveRequestDto;
import com.example.toyblog.model.Reply;
import com.example.toyblog.repository.ReplyRepository;
import com.example.toyblog.repository.UserRepository;
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
 * 57강(블로그 프로젝트) - 글 삭제하기
 * 58강(블로그 프로젝트) - 글 수정하기
 * 67강(블로그 프로젝트) - 댓글 목록 뿌리기
 * 68강(블로그 프로젝트) - 댓글 작성하기
 * 69강(블로그 프로젝트 ) - 댓글 작성시 Dto 사용해보기
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
     * <p>
     * BoardRepository의 인스턴스가 자동으로 주입된다.
     * ReplyRepository의 인스턴스가 자동으로 주입된다.
     * UserRepository의 인스턴스가 자동으로 주입된다.
     */
    @Autowired
    private BoardRepository boardRepository; // 의존성 주입(DI)

    @Autowired
    private ReplyRepository replyRepository; // 의존성 주입(DI)

    @Autowired
    private UserRepository userRepository; // 의존성 주입(DI)

    /**
     * 사용자가 작성한 게시글 데이터를 받아 데이터베이스에 저장하는 로직을 처리하는 글쓰기 메서드
     *
     * @param board 사용자로부터 입력 받은 게시글 데이터. 게시글 제목과 내용을 포함한다.
     * @param user  게시글 작성자 정보. 게시글과 연관된 사용자 객체.
     * @Transactional 어노테이션을 사용하여 메서드 내의 모든 데이터베이스 작업을 하나의 트랜잭션으로 관리함으로써
     * 데이터베이스 작업의 일관성과 무결성을 보장한다.
     * <p>
     * boardRepository.save(board)는 Board 객체를 데이터베이스에 영구적으로 저장하거나,
     * 이미 존재하는 객체를 업데이트하는 JPA 리포지토리 메서드이다.
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
     * @param pageable 페이지네이션 정보를 담고 있는 Pageable 객체.
     * @return 페이지네이션 적용된 게시글 목록이 담긴 Page<Board> 객체.
     * @Transactional(readOnly = true)를 통해 데이터베이스 읽기 전용 트랜잭션으로 처리한다.
     */
    @Transactional(readOnly = true)
    public Page<Board> 글목록(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    /**
     * 주어진 id에 해당하는 게시글을 조회하는 메서드
     * id에 해당하는 게시글이 존재하지 않을 경우, IllegalArgumentException 예외를 발생시킨다.
     *
     * @param id 조회하려는 게시글의 고유 식별자.
     * @return id에 해당하는 Board 객체. 게시글이 존재하지 않을 경우 예외 발생.
     * @throws IllegalArgumentException 게시글을 찾을 수 없을 때 발생하는 예외.
     * @Transactional(readOnly = true)로 데이터 조회 시 데이터베이스 변경을 방지한다.
     */
    @Transactional(readOnly = true)
    public Board 글상세보기(int id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 상세보기 실패:아이디를 찾을 수 없습니다.");
                });
    }

    /**
     * 주어진 id에 해당하는 게시글을 삭제하는 메서드
     * 메서드는 id를 매개변수로 받아 해당하는 게시글을 데이터베이스에서 삭제한다.
     *
     * @param id 삭제하려는 게시글의 고유 식별자.
     * @Transactional을 사용하여 데이터 변경 작업을 트랜잭션으로 관리한다.
     * <p>
     * TODO: 예외 발생 시 이를 명시하는 @throws 태그를 추가하는 것이 좋다.
     */
    @Transactional
    public void 글삭제하기(int id) {
//        System.out.println("글 삭제하기 : "+id); // 서버 콘솔에 로그 출력 // 테스트 완료
        boardRepository.deleteById(id);
    }

    /**
     * 주어진 id에 해당하는 게시글을 수정하는 메서드
     * 메서드는 id와 수정할 게시글 정보(requestBoard)를 매개변수로 받아 해당 게시글을 데이터베이스에서 찾아 수정한다.
     *
     * @param id           수정하려는 게시글의 고유 식별자
     * @param requestBoard 클라이언트로부터 받은 수정할 게시글 정보
     * @Transactional을 사용하여 데이터 변경 작업을 트랜잭션으로 관리한다.
     * 글 찾기에 실패할 경우 IllegalArgumentException 예외를 발생시킨다.
     * <p>
     * JPA의 영속성 컨텍스트에 의해 관리되는 board 객체의 상태를 변경한다.
     * 이 메서드 실행 시 발생하는 변경은 '더티 체킹(Dirty Checking)'에 의해 데이터베이스에 자동으로 반영된다.
     */
    @Transactional
    public void 글수정하기(int id, Board requestBoard) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 찾기 실패:아이디를 찾을 수 없습니다.");
                });
        // Persistence 영속화(객체를 영구적으로 저장) 완료
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
        // 해당 함수로 종료시(Service가 종료될 때) 트랜잭션이 종료된다. 이때 더티 체킹(변경된 사항이 데이터베이스에 자동 반영됨) 자동 업데이트가 된다.dbflush
    }

    /**
     * 댓글 저장을 위한 API 메서드
     * 클라이언트로부터 받은 댓글 데이터(DTO)를 처리하여 데이터베이스에 저장한다.
     *
     * @Transactional 어노테이션을 사용하여 데이터베이스 트랜잭션을 관리한다.
     * 댓글 작성에 필요한 사용자와 게시글 정보를 데이터베이스에서 찾고, 찾을 수 없는 경우 예외를 발생시킨다.
     * 댓글 객체를 생성하고 데이터베이스에 저장한다.
     * JPA의 영속성 컨텍스트에 의해 관리되는 객체의 상태를 변경하며,
     * 트랜잭션이 종료될 때 변경된 사항이 '더티 체킹(Dirty Checking)'에 의해 데이터베이스에 자동으로 반영된다.
     *
     * @param replySaveRequestDto 클라이언트로부터 받은 댓글 데이터를 담고 있는 DTO
     */
    @Transactional
    public void 댓글쓰기(ReplySaveRequestDto replySaveRequestDto) {

        // 사용자 ID를 통해 유저 엔티티를 데이터베이스에서 찾고, 없을 경우 예외 발생
        User user = userRepository.findById(replySaveRequestDto.getUserId())
                .orElseThrow(() -> {
                    return new IllegalArgumentException("댓글 쓰기 실패: 유저 ID를 찾을 수 없습니다.");
                }); // 유저 엔티티 영속화 완료

        // 게시글 ID를 통해 게시글 엔티티를 데이터베이스에서 찾고, 없을 경우 예외 발생
        Board board = boardRepository.findById(replySaveRequestDto.getBoardId())
                .orElseThrow(() -> {
                    return new IllegalArgumentException("댓글 쓰기 실패: 게시글 ID를 찾을 수 없습니다.");
                }); // 게시글 엔티티 영속화 완료

        // 댓글 객체 생성 및 초기화
        Reply reply = Reply.builder()
                .user(user)
                .board(board)
                .content(replySaveRequestDto.getContent())
                .build();

        // 생성된 댓글 객체를 데이터베이스에 저장
        replyRepository.save(reply);
    }

} // end of class