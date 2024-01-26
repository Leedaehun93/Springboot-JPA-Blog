package com.example.toyblog.repository;

import com.example.toyblog.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ======================================
 * FileName : BoardRepository
 * Note :
 * 53강(블로그 프로젝트) - 글쓰기 완료
 * - summernote 스크립트를 사용하여 글쓰기 기능을 구현
 * ======================================
 */

/**
 * JpaRepository를 상속받는 BoardRepository 인터페이스 정의
 * - CRUD 기능 및 페이지네이션과 정렬을 위한 기본적인 JPA 작업을 제공한다.
 * - Spring Data JPA는 인터페이스 정의에 따라 구현 클래스를 런타임에 자동으로 생성한다.
 * - 인터페이스에 @Repository 어노테이션을 생략해도, Spring은 이를 Repository로 인식하고 관리한다.
 * - Board 엔티티와 이 엔티티의 기본 키 타입인 Integer를 지정하여 사용한다.
 *
 * 페이지네이션(pagination): 일반적으로 웹 페이지나 어플리케이션에서 사용자가 데이터의 일정한 부분만을 순차적으로 볼 수 있게 하는 기능
 */
public interface BoardRepository extends JpaRepository<Board, Integer> {

} // end of interface