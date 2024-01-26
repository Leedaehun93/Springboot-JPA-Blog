package com.example.toyblog.model;

/**
 * ======================================
 * FileName : RoleType
 * Note :
 * 25강(블로그 프로젝트) - 회원가입을 위한 enum 사용법
 * - Enum 은 코드 내에서 역할의 강제성을 부여한다.
 *   즉, 오직 RoleType 에 정의된 값들만 사용될 수 있다.
 *   JPA 와 같은 ORM 을 사용할 때, 엔티티의 필드로 사용되어
 *   데이터베이스에 안전하게 역할을 저장할 수 있다.
 * ======================================
 */

/**
 * RoleType enum 은 사용자의 역할을 나타내기 위해 사용된다.
 * 이 enum 은 USER 와 ADMIN 두 가지 역할을 정의한다.
 * USER 는 일반 사용자를, ADMIN 은 관리자를 의미한다.
 */
public enum RoleType {

    USER, // 일반 사용자 역할
    ADMIN // 관리자 역할

} // end of enum
