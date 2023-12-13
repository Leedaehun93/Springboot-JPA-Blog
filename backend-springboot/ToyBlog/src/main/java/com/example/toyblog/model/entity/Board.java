package com.example.toyblog.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * ======================================
 * FileName : Board
 * Author : DH.Lee
 * Date : 2023-12-14
 * Note : 20강(블로그 프로젝트) - Board테이블 생성
 * TODO: User 모델 클래스와 동일한 부분은 주석 설명 생략
 * ======================================
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Board {

    @Id
    /** 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다. */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /** Primary key 정의 : private int id; 이 부분을 엔티티 클래스 내에서 기본 키를 정의합니다. */
    private int id; // FK

    //** 타이틀 필드 : null을 허용하지 않으며, 최대 길이는 100자 */
    @Column(nullable = false, length = 100)
    private String title;

    /**
     * TODO: content(본문) 필드는 대용량 텍스트 데이터를 저장합니다. 이 필드는 null을 허용하지 않습니다.
     *  @Column 대신 큰 크기의 데이터를 담기 위해 @Lob 어노테이션을 사용한다.
     *  @Lob 어노테이션은 큰 크기의 데이터를 저장할 수 있도록 한다.
     * 주로 HTML 형식의 텍스트를 저장하는 데 사용되며, 섬머노트(Summernote)와 같은 WYSIWYG 에디터에서
     * 생성된 컨텐츠를 저장하는 데 적합합니다. 섬머노트는 HTML 태그가 포함된 텍스트 데이터를 생성할 수 있다.
     * 길이 제한이 없어 다양한 크기의 컨텐츠를 유연하게 저장할 수 있다.
     */
    @Lob
    private String content;

    //** TODO: 카운트(조회수) 필드 : null을 허용하지 않으며, 최대 길이는 100자 */
    @ColumnDefault("0")
    private int count;

    /**
     * TODO: (1) @JoinColumn(name="userid") :
     *  DB는 오브젝트를 저장할 수 없다. 그래서 FK,자바는 오브젝트를 저장 할 수 있다.
     * TODO: (2) @ManyToOne :
     *     * Many = Many, User = One
     */
    @ManyToOne // Board = Many, User = One
    @JoinColumn(name="userId")
    private User user;

    @CreationTimestamp
    private Timestamp creteDate;

} // end of class
