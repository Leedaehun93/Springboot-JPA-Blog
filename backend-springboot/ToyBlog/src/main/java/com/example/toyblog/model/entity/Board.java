package com.example.toyblog.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * ======================================
 * FileName : Board
 * Author : DH.Lee
 * Date : 2023-12-14
 * Note : 20강(블로그 프로젝트) - Board테이블 생성
 * User 모델 클래스와 동일한 부분은 주석 설명 생략
 * ======================================
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Board {

    /**
     * id 필드는 엔티티의 기본 키(Primary Key, PK)이며, GenerationType.IDENTITY 전략을 사용한다.
     * 이 전략은 데이터베이스가 id 값을 자동으로 관리하도록 지시한다.
     * 예를 들어, MySQL에서는 auto_increment를, Oracle에서는 시퀀스를 사용한다.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // 이 필드는 엔티티의 PK(기본 키)로 사용된다.

    //** 타이틀 필드 : null 을 허용하지 않으며, 최대 길이는 100자 */
    @Column(nullable = false, length = 100)
    private String title;

    /**
     * content(본문) 필드는 대용량 텍스트 데이터를 저장합니다. 이 필드는 null을 허용하지 않습니다.
     *  @Column 대신 큰 크기의 데이터를 담기 위해 @Lob 어노테이션을 사용한다.
     *  @Lob 어노테이션은 큰 크기의 데이터를 저장할 수 있도록 한다.
     * 주로 HTML 형식의 텍스트를 저장하는 데 사용되며, 섬머노트(Summernote)와 같은 WYSIWYG 에디터에서
     * 생성된 컨텐츠를 저장하는 데 적합합니다. 섬머노트는 HTML 태그가 포함된 텍스트 데이터를 생성할 수 있다.
     * 길이 제한이 없어 다양한 크기의 컨텐츠를 유연하게 저장할 수 있다.
     */
    @Lob
    private String content;

    //** 카운트(조회수) 필드 : null을 허용하지 않으며, 최대 길이는 100자 */
    @ColumnDefault("0")
    private int count;

    /**
     * @JoinColumn(name="userId") :
     *  이 필드는 데이터베이스의 외래 키(FK)를 'userId'라는 이름의 컬럼으로 매핑한다.
     *  JPA를 사용할 때, 자바 객체에서는 User 타입의 오브젝트를 참조할 수 있지만,
     *  데이터베이스에서는 해당 오브젝트의 식별자(id)를 저장한다.
     *  @ManyToOne(fetch = FetchType.EAGER): FetchType.EAGER를 사용하여 관련된 User 엔티티를
     *  즉시 로딩한다. 즉, Board 엔티티를 로딩할 때 연관된 User 엔티티도 함께 로딩된다.
     */
    @ManyToOne(fetch = FetchType.EAGER) // 한 User는 여러 Board를 가질 수 있다. (User 입장에서는 OneToMany)
    @JoinColumn(name="userId")
    private User user;

    /**
     * @OneToMany(mappedBy="board", fetch = FetchType.EAGER) :
     *  Board 엔티티 와 Reply 엔티티 사이의 일대다 관계를 정의한다.
     *  'mappedBy' 속성은 이 필드(Board)가 연관관계의 주인이 아니며, 실제 외래 키 관리는
     *  Reply 엔티티의 'board' 필드에 의해 이루어진다는 것을 나타낸다.
     *  이는 Reply 테이블에 'board'라는 필드가 외래 키(FK)로 존재함을 의미하며,
     *  Board 엔티티에는 이 관계에 대응하는 별도의 컬럼이 생성되지 않는다.
     *  FetchType.EAGER를 사용하여 Board 엔티티를 로딩할 때 연관된 Reply 엔티티들도 함께 로딩된다.
     */
    @OneToMany(mappedBy="board", fetch = FetchType.EAGER)
    private List<Reply> replies; // 한 개의 Board 에는 여러 개의 Reply 가 있을 수 있다.

    /**
     * createDate 필드는 엔티티가 데이터베이스에 저장될 때의 현재 시간을 자동으로 기록합니다.
     * @CreationTimestamp 어노테이션은 Hibernate가 이 필드에 생성 시점의 타임스탬프를 자동으로 할당하도록 합니다.
     * java.sql.Timestamp 타입은 SQL 타임스탬프 값을 표현하는 데 사용됩니다.
     */
    @CreationTimestamp
    private Timestamp creteDate;

} // end of class
