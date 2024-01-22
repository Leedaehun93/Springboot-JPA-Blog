package com.example.toyblog.repository;

import com.example.toyblog.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * ======================================
 * FileName : UserRepository
 * Author : DH.Lee
 * Date : 2023-12-15
 * Note : 24강(블로그 프로젝트) - 회원가입 위한 insert 테스트
 * 1) User DAO(Data Access Object) JPA CRUD 인터페이스
 * 자동으로  등록이 된다.
 * ======================================
 */

/**
 * TODO: @Repository 어노테이션 생략 가능하다.
 *   1) UserRepository는 Spring Data JPA의 JpaRepository를 확장한 인터페이스이다.
 *       이를 통해 User 엔티티에 대한 기본적인 CRUD 작업과 조회 기능을 제공한다.
 *   2) JpaRepository에는 다양한 표준 CRUD 메소드가 포함되어 있으며,
 *       필요에 따라 추가적인 쿼리 메소드를 선언할 수 있다.
 *   3) Spring Data JPA는 이 인터페이스를 구현한 클래스를 자동으로 생성하고,
 *       Spring 컨테이너에 Bean으로 등록한다.
 *   4) @Repository 어노테이션은 생략 가능하다. Spring Data JPA는
 *       인터페이스 정의를 기반으로 자동으로 이를 구현체로 인식하며,
 *       런타임에 동적으로 구현체를 생성한다.
 *   5) 이 인터페이스는 User 엔티티의 기본 키 타입이 Integer 임을 명시한다.
 *      JpaRepository<User, Integer> 의 두 번째 매개변수는 기본 키의 타입을 나타낸다.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
// TODO: Note : 49강 - 스프링 시큐리티 기반 로그인 페이지 커스터마이징으로 기존 기본 로그인 방식은 주석 처리하여 참조용으로 보존
    // TODO: 1. Spring Data JPA Naming 전략: 메소드 이름을 통해 단순한 조회 조건에 적합한 쿼리 자동 생성
//    User findByUsernameAndPassword(String username, String password); // 조회조건: findByUsernameAndPassword

    // TODO: 2. Spring Data JPA 네이티브 쿼리 전략: @Query 어노테이션을 사용하여 복잡한 쿼리를 직접 작성
//    @Query(value="SELECT * FROM user WHERE username = ?1 AND Password = ?2", nativeQuery = true) // nativeQuery = true는 쿼리가 순수 SQL임을 나타냄
//    User login(String username, String password);


} // end of interface