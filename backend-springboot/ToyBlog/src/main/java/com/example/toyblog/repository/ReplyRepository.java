package com.example.toyblog.repository;

import com.example.toyblog.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * ======================================
 * FileName : ReplyRepository
 * Note :
 * 67강(블로그 프로젝트) - 댓글 목록 뿌리기
 * 70강(블로그 프로젝트) - 댓글 작성시 네이티브 쿼리 사용해보기
 * ======================================
 */
/**
 * 댓글(Reply) 엔티티에 대한 JPA Repository 인터페이스.
 * JPARepository를 상속받아 기본적인 CRUD 기능을 제공하며,
 * 추가로 사용자 정의 쿼리를 위한 메서드를 포함한다.
 */
public interface ReplyRepository extends JpaRepository<Reply, Integer> {

    /**
     * 사용자 정의 네이티브 SQL 쿼리를 사용하여 댓글 데이터를 데이터베이스에 저장하는 메서드.
     * @Modifying 어노테이션은 이 쿼리가 데이터를 변경하는 작업임을 나타낸다.
     *
     * @param userId 댓글을 작성하는 사용자의 ID.
     * @param boardId 댓글이 작성될 게시글의 ID.
     * @param content 댓글의 내용.
     * @return 데이터베이스에 성공적으로 삽입된 레코드의 수를 반환한다.
     */
    @Modifying
    @Query(value = "INSERT INTO reply(userId, boardId, content, createDate) VALUE(?1, ?2, ?3, now())", nativeQuery = true)
    int mSave(int userId, int boardId, String content); // 업데이트된 행의 개수를 리턴해줌.

} // end of interface