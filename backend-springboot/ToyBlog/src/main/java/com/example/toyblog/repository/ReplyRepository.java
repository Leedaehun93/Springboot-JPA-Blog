package com.example.toyblog.repository;

import com.example.toyblog.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ======================================
 * FileName : ReplyRepository
 * Note :
 * 67강(블로그 프로젝트) - 댓글 목록 뿌리기
 * ======================================
 */
public interface ReplyRepository extends JpaRepository<Reply, Integer> {

} // end of interface
