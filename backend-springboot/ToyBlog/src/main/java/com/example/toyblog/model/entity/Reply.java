package com.example.toyblog.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * ======================================
 * FileName : Reply
 * Author : DH.Lee
 * Date : 2023-12-14
 * Note : 21강(블로그 프로젝트) - Reply 테이블 생성
 * TODO: User, Board 모델 클래스와 동일한 부분은 주석 설명 생략
 * ======================================
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reply {

    @Id
    /** 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다. */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // GenerationType.IDENTITY: 데이터베이스가 id 값을 자동으로 관리한다.
    // 시퀀스, auto_increment
    private int id; // FK

    //** content(본문) 필드 : null을 허용하지 않으며, 최대 길이는 200자 */
    @Column(nullable = false, length = 200)
    private String content;

    // TODO: 1. FK
    @ManyToOne
    @JoinColumn(name="boardId")
    private Board board;

    // TODO: 2. FK
    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    /**
     * TODO: @UpdateTimestamp 어노테이션을 사용하여 업데이트 시간 필드를 활용
     */
    @CreationTimestamp
    // TODO: 자바 SQL 이 들고 있는 Timestamp 사용하여 cretedate 생성하는 현재 시간을 자동으로 입력
    private Timestamp creteDate;

} // end of class
