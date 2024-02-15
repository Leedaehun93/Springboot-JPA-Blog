package com.example.toyblog.service;

import com.example.toyblog.dto.ReplySaveRequestDto;
import com.example.toyblog.repository.ReplyRepository;
import com.example.toyblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.toyblog.model.User;
import com.example.toyblog.model.Board;
import com.example.toyblog.repository.BoardRepository;

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
 * 70강(블로그 프로젝트) - 댓글 작성시 네이티브 쿼리 사용해보기
 * 71강(블로그 프로젝트) - @Autowired의 원리
 * 73강(블로그 프로젝트) - 댓글 삭제 마지막 강
 * ======================================
 */

/**
 * BoardService 클래스는 게시판 관련 비즈니스 로직을 처리한다.
 * BoardRepository와 연결되어 게시글 데이터를 관리하며 데이터베이스와의 상호작용을 담당한다.
 */
/**
 * BoardService 클래스는 게시판 관련 비즈니스 로직을 처리한다.
 * BoardRepository와 연결되어 게시글 데이터를 관리하며 데이터베이스와의 상호작용을 담당한다.
 * 이 클래스는 생성자 주입을 통해 의존성을 주입받는다.
 *
 * 변경 이력:
 * - 이전 버전(70강 이전): @Autowired 어노테이션을 사용하여 필드 주입 방식으로 의존성을 관리했다.
 * - 현재 버전(71강): @RequiredArgsConstructor를 사용하여 생성자 주입 방식으로 의존성을 관리한다.
 *   이로 인해 코드의 명시성이 향상되고, 의존성 주입의 안전성이 강화되었다.
 */
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository; // 생성자 주입을 통한 의존성 주입
    private final ReplyRepository replyRepository; // 생성자 주입을 통한 의존성 주입

    // 생성자 주입 방식 예시 (Lombok의 @RequiredArgsConstructor가 이를 대체함)
    // public BoardService(BoardRepository bRepo, ReplyRepository rRepo) {
    //     this.boardRepository = bRepo;
    //     this.replyRepository = rRepo;
    // }

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
     * 이전 구현 방식(69강)에서 JPA의 save 메서드를 사용했으나, 현재 댓글 기능을 네이티브 쿼리로 재구현
     *
     * @Transactional 어노테이션을 사용하여 데이터베이스 트랜잭션을 관리한다.
     * 이 메서드에서는 네이티브 쿼리를 사용하여 댓글 데이터를 데이터베이스에 직접 저장한다.
     * 네이티브 쿼리를 사용함으로써, JPA의 추상화를 벗어나 데이터베이스와 직접적으로 상호작용한다.
     * 이는 복잡한 쿼리나 데이터베이스에 특화된 기능을 사용해야 할 때 유용하다.
     *
     * @param replySaveRequestDto 클라이언트로부터 받은 댓글 데이터를 담고 있는 DTO.
     *                            이 DTO는 댓글 작성자의 사용자 ID, 게시글 ID, 댓글 내용을 포함한다.
     */
    @Transactional
    public void 댓글쓰기(ReplySaveRequestDto replySaveRequestDto) {
        // 네이티브 쿼리를 사용하여 댓글을 데이터베이스에 저장
        replyRepository.mSave(replySaveRequestDto.getUserId(), replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent());
    }
    /**
     * 댓글을 삭제하는 비즈니스 로직을 수행하는 메서드
     *
     * @Transactional 어노테이션은 이 메서드의 실행을 트랜잭션으로 관리한다.
     * 이는 메서드 내에서 발생하는 모든 데이터베이스 연산이 하나의 트랜잭션으로 처리되며,
     * 오류 발생 시 자동으로 롤백된다.
     *
     * @param replyId 삭제할 댓글의 ID.
     * 메서드는 replyId를 사용하여 해당 댓글을 데이터베이스에서 삭제한다.
     */
    @Transactional
    public void 댓글삭제(int replyId) {
        // 주어진 ID를 가진 댓글을 데이터베이스에서 삭제
        replyRepository.deleteById(replyId);
    }

} // end of class