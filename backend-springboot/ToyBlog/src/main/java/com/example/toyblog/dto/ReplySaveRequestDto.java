package com.example.toyblog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ======================================
 * FileName : BoardService
 * Note :
 * 69강(블로그 프로젝트 ) - 댓글 작성시 Dto 사용해보기
 * ======================================
 */
/**
 * 댓글 저장 요청을 위한 DTO 클래스.
 * 이 클래스는 클라이언트로부터 댓글 저장에 필요한 데이터를 전달받기 위해 사용된다.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplySaveRequestDto {

    private int userId;   // 사용자 ID
    private int boardId;  // 게시글 ID
    private String content; // 댓글 내용

} // end of class
