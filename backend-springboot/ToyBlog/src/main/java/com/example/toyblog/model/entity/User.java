package com.example.toyblog.model.entity;

import com.example.toyblog.model.RoleType;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * ======================================
 * FileName : User
 * Author : DH.Lee
 * Date : 2023-12-13
 * Note : 18강(블로그 프로젝트) - User테이블 생성(JPA 어노테이션의 사용법과 엔티티의 역할)
 * User 모델 클래스 : User 엔티티 클래스는 데이터베이스의 User 테이블과 매핑된다.
 * TODO: DTO 와 Entity 용도의 차이 :
 *  DTO는 단순히 데이터 전송에 초점을 맞추는 반면, Entity는 데이터베이스의 데이터를 객체로 나타내는 데 사용된다.
 *  수명 주기: DTO는 요청이나 응답을 처리하는 데 사용되고 종료되지만, Entity는 애플리케이션 전반에 걸쳐 지속적으로 사용된다.
 *  데이터베이스 매핑: Entity는 데이터베이스 테이블과 직접 매핑되는 반면, DTO는 매핑이 되지 않는다.
 *  TODO: ORM(Object-Relational Mapping) -> JAVA(다른 언어) Object(객체) -> 테이블로 매핑해주는 기술이다.
 *   이를 통해 객체의 속성은 테이블의 컬럼과 연결되며, 객체의 인스턴스(또는 객체)는 테이블의 행에 해당하게 한다.
 * ======================================
 */

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * TODO: @Entity = ORM(Object-Relational Mapping) -> JAVA(다른 언어) Object(객체) -> 테이블로 매핑해주는 기술이다.
 *  User 클래스가 MySQL 에 테이블이 생성이 된다. = User 엔티티 클래스는 데이터베이스의 User 테이블과 매핑된다. 라고 표현 할 수 있다.
 * @Entity 어노테이션을 통해 JPA가 User 클래스를 데이터베이스 테이블 컬럼과 연결하고,
 * 이 클래스는 사용자 데이터를 저장하며, 객체의 인스턴스는 테이블의 한 행(row)에 해당하게 된다.
 */
@Entity

/**
 * @DynamicInsert 사용을 하게 되면 insert 시에 null 인 필드를 제외 시켜준다.
 */
// @DynamicInsert
public class User {

    /**
     * Primary key 정의 : private int id; 이 부분을 엔티티 클래스 내에서 기본 키를 정의합니다.
     */
    @Id
    /**
     * @GeneratedValue(strategy = GenerationType.IDENTITY) 어노테이션은 (Java Persistence API)에서 사용하는 어노테이션으로
     * JPA 가 해당 엔티티의 id 필드에 대해 데이터베이스의 자동 증가(auto_increment) 기능을 사용하여 값을 자동으로
     * 할당하도록 지시하여 데이터베이스가 새로운 레코드를 삽입할 때마다 고유한 키 값을 자동으로 생성해 주게 지정한다.
     * TODO: 즉, 현재 프로젝트에서 연결된 DB의 넘버링 전략(데이터 베이스의 자체적인 자동 증가 기능을 이용하여 기본 키값을 생성)을 따라간다.
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // GenerationType.IDENTITY: 데이터베이스가 id 값을 자동으로 관리한다.
    // Oracle에서는 시퀀스를, MySQL에서는 auto_increment를 사용한다.
    // id 필드 타입으로 int 대신 long을 사용하는 경우는, 회원 수가 매우 많은 경우에 적합하다.
    private int id; // 이 필드는 엔티티의 PK(기본 키)로 사용된다.

    /**
     * 아이디 필드: null 을 허용하지 않으며, 최대 길이는 30자
     * 이 필드의 값은 데이터베이스 내에서 고유해야 한다.(unique = true)
     *  즉, 동일한 username을 가진 두 개의 레코드를 테이블에 저장할 수 없다.
     */

    @Column(unique = true, nullable = false, length = 30)
    // TODO: 아이디
    private String username;

    /**
     * 패스워드 필드 : null 을 허용하지 않으며, 최대 길이는 100자
     */
    // TODO: 12345 => (비밀번호는 해시 암호화를 고려하여 길이를 100으로 설정)
    @Column(nullable = false, length = 100)
    private String password;

    /**
     * 이메일 필드 : null 을 허용하지 않으며, 최대 길이는 50자
     */
    @Column(nullable = false, length = 50)
    private String email;

    /**
     * TODO: 권한 부여 필드 : 컬럼에 기본 값(사용자 역할(role).
     *   기본 값은 ('user')을 지정해주고 문자열 값에는 반드시 홑 따옴표('') 사용하여 문자라고 알려줘야 한다.
    */
    //    @ColumnDefault("'user'")
    /**
     * TODO: private RoleType role;  => enum 열거형을 생성하여 role 필드에 허용된 값들을 USER 와 ADMIN 으로 제한합니다.
     *  private Enum(열거형) role; 전략을 사용하는 게 좋다. 이유 :
     *  role 필드에 허용된 값들을 제한하고(예 : ADMIN, USER, MANGER) 도메인(지정한 권한 범위) 안에 서만
     *  설정할 수 있기 떄문에 코드의 안정성과 명확성을 높일 수 있다.(오타나 잘못된 값 할당과 같은 프로그래밍 오류를 줄일 수 있음)
     *
     * TODO: @Enumerated(EnumType.STRING) : 데이터베이스에 enum 값을 저장할 때, 어노테이션을 사용하여 enum 값이 문자열로 저장되도록 지정한다.
     */
    @Enumerated(EnumType.STRING) // 데이터베이스에서는 RoleType 자체를 인식할 수 없기 때문에, enum 을 문자열 형태로 변환
    private RoleType role; // enum 열거형을 생성하여 role 필드에 허용된 값들을 USER 와 ADMIN 으로 제한한다.

    /** @CreationTimestamp :
     *   생성 시간 추적 :
     *    엔티티가 데이터베이스에 저장될 때 자동으로 타임스탬프를 설정하여 시간이 자동 입력됨,
     *    엔티티의 생성 시간을 추적하는데 유용함
     */
    /**
     * TODO: @UpdateTimestamp 어노테이션을 사용하여 업데이트 시간 필드를 활용 가능 예: private Timestamp updateDate;
     * TODO: Hibernate ORM(객체-관계 매핑) 프레임워크에서 제공하는 특정 기능이며,
     *       JPA 표준 사양에는 포함되어 있지 않아서 @UpdateTimestamp 어노테이션을 사용하기 위해서는 Hibernate를 ORM 프레임워크로 사용 할 것
     */
    @CreationTimestamp
    // TODO: 자바 SQL 이 들고 있는 Timestamp 사용하여 cretedate 생성하는 현재 시간을 자동으로 입력
    private Timestamp creteDate;

} // end of class
