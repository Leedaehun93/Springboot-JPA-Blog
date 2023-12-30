package com.example.toyblog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * ======================================
 * FileName : GlobalEceptionHandler
 * Author : DH.Lee
 * Date : 12월 31일 0031
 * Note : 31강(블로그 프로젝트) - Exception처리하기
 * @ControllerAdvice 전역적인 예외 처리를 위한 페이지 만들기
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
