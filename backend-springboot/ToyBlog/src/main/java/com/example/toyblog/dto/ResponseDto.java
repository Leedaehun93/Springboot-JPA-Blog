package com.example.toyblog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * ======================================
 * FileName : ResponseDto
 * Note :
 * 38강(블로그 프로젝트) - 회원가입 하기 Ajax요청
 * 1) jQuery를 사용하여 웹 페이지에서 사용자 입력을 받아 AJAX를 통해 서버에 데이터를 전송하는 방법
 *    ResponseDto<T> 클래스는 API 응답을 위한 범용 데이터 전송 객체(Data Transfer Object, DTO)
 *    제네릭 타입 <T>를 사용하여 다양한 유형의 응답 데이터를 유연하게 처리할 수 있다.
 *    ResponseDto<T> 클래스는 API 응답의 표준 구조를 제공하며, 클라이언트와 서버 간의 데이터 교환을 용이하게 한다.
 * ======================================
 */

/**
 * Lombok 라이브러리의 어노테이션을 사용하여 표준적인 메서드(getter, setter 등)를 자동으로 생성
 * @Data: 자동으로 getter, setter, equals, hashCode, toString 메서드 생성
 * @NoArgsConstructor: 매개변수 없는 기본 생성자 생성
 * @AllArgsConstructor: 모든 필드를 매개변수로 받는 생성자 생성
 * @Builder: 객체의 빌더 패턴 구현
 *
 * @param <T> 이 DTO가 처리할 데이터의 타입
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto<T> {

    int status; // HTTP 응답 상태를 나타내는 필드(예 : 성공, 실패, 에러 등)
    T data; // 실제 응답 데이터를 포함하는 필드, 제네릭 타입으로 다양한 데이터 유형 처리

} // end of class