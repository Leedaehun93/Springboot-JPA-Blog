package com.example.toyblog.model;

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
 * User, Board 모델 클래스와 동일한 부분은 주석 설명 생략
 * ======================================
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reply {

    /**
     * id 필드는 엔티티의 기본 키(Primary Key, PK)이며, GenerationType.IDENTITY 전략을 사용한다.
     * 이 전략은 데이터베이스가 id 값을 자동으로 관리하도록 지시한다.
     * 예를 들어, MySQL에서는 auto_increment를, Oracle에서는 시퀀스를 사용한다.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // 이 필드는 엔티티의 PK(외래 키)로 사용된다.

    //** content(본문) 필드 : null 을 허용하지 않으며, 최대 길이는 200자 */
    @Column(nullable = false, length = 200)
    private String content;

    /**
     * board 필드는 Reply(답변) 과 Board(게시글) 간의 다대일(Many-to-One) 관계를 정의한다.
     * @ManyToOne : 하나의 게시글(Board)에 여러 개의 답변(Reply)이 존재할 수 있음을 나타낸다.
     * @JoinColumn : 이 필드가 외래 키(FK)인 boardId를 가진 답변(Reply)의 열(column)과 연결된다.
     */
    @ManyToOne
    @JoinColumn(name="boardId")
    private Board board;

    /**
     * user 필드는 Reply(답변) 과 User(유저) 간의 다대일(Many-to-One) 관계를 정의한다.
     * @ManyToOne : 한 유저(User)가 여러 개의 답변(Reply)을 작성할 수 있음을 나타낸다.
     * @JoinColumn : 이 필드가 외래 키(FK)인 userId를 가진 답변(Reply)의 열(column)과 연결된다.
     */
    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    /**
     * createDate 필드는 엔티티가 데이터베이스에 저장될 때의 현재 시간을 자동으로 기록한다.
     * @CreationTimestamp 어노테이션은 Hibernate가 이 필드에 생성 시점의 타임스탬프를 자동으로 할당하도록 한다.
     * java.sql.Timestamp 타입은 SQL 타임스탬프 값을 표현하는 데 사용된다.
     */
    @CreationTimestamp
    private Timestamp createDate;

} // end of class
