package com.example.toyblog.repository;

import com.example.toyblog.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ======================================
 * FileName : UserRepository
 * Author : DH.Lee
 * Date : 2023-12-15
 * Note : 24강(블로그 프로젝트) - 회원가입 위한 insert 테스트
 * 1) User DAO(Data Access Object) JPA CRUD 인터페이스
 * 자동으로 bean 등록이 된다.
 * ======================================
 */

/**
 * TODO: @Repository 어노테이션 생략 가능하다.
 *   1) UserRepository 는 Spring Data JPA 의 JpaRepository 를 확장한 인터페이스이다.
 *       이를 통해 User 엔티티에 대한 기본적인 CRUD 작업과 조회 기능을 제공한다.
 *   2) JpaRepository 에는 다양한 표준 CRUD 메소드가 포함되어 있으며,
 *       필요에 따라 추가적인 쿼리 메소드를 선언할 수 있다.
 *   3) Spring Data JPA 는 이 인터페이스를 구현한 클래스를 자동으로 생성하고,
 *       Spring 컨테이너에 Bean 으로 등록한다.
 *   4) @Repository 어노테이션은 생략 가능하다. Spring Data JPA 는
 *       인터페이스 정의를 기반으로 자동으로 이를 구현체로 인식하며,
 *       런타임에 동적으로 구현체를 생성한다.
 *   5) 이 인터페이스는 User 엔티티의 기본 키 타입이 Integer 임을 명시한다.
 *      JpaRepository<User, Integer> 의 두 번째 매개변수는 기본 키의 타입을 나타낸다.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}