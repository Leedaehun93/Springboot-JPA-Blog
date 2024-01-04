package com.example.toyblog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * ======================================
 * FileName : GlobalEceptionHandler
 * Author : DH.Lee
 * Date : 2023-12-31
 * Note : 31강(블로그 프로젝트) - Exception처리하기
 * @ControllerAdvice 전역적인 예외 처리를 위한 페이지 만들기
 *
 * Note : 32강(블로그 프로젝트) - 스프링 기본파싱전략과 json통신
 * 1) get 요청은 주소에 담아서 브라우저를 통해 보낼 수 있고, body 데이터 없음. form태그 형식으로 만든다.
 * 2) post 요청도 form 태그로 가능하지만 put, delete 요청과 모두 통일을 위해 javascript로 ajax요청+데이터는 json형태로 통일(수업에서는 이 방식 사용)하거나 form:form 태그로 4가지 요청을 다 커버한다.
 * 3) 스프링 컨트롤러는 key=value 형태의 데이터를 자동으로 파싱하여 변수에 담거나 object로 파싱해서 받아줄 수 있다.(해당 object의 setter 꼭 필요)
 * 4) key=value 형태가 아닌 Json데이터나 일반 text 데이터는 @requestbody 어노테이션이 필요하다.
 * ======================================
 */

/**
 * @ControllerAdvice 는 이 클래스가 전역 예외 처리기 역할을 하도록 지정한다.
 * 이를 통해 애플리케이션의 모든 컨트롤러에서 발생하는 예외를 여기서 처리할 수 있어진다.
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

/**
 * @ExceptionHandler(value = Exception.class) 는
 * Java 의 모든 예외의 최상위 클래스인 Exception을 처리한다.
 * 포괄적으로 처리하기 떄문에 다양한 유형의 예외 처리를 위해서는
 * 세부적인(지정된) 예외 타입 코드를 구성하는게 좋다.
 */
//    @ExceptionHandler(value = Exception.class)
//    public String handleArgumentException(Exception e) {
//        return "<h1>" + e.getMessage() + "</h1>";
//    }

    /**
     * @ExceptionHandler(value = IllegalArgumentException.class)
     * IllegalArgumentException 타입의 예외만 처리된다.
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    public String handleArgumentException(IllegalArgumentException e) {
        return "<h1>" + e.getMessage() + "</h1>";
    }

} // end of class
