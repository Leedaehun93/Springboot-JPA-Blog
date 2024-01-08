package com.example.toyblog.controller.api;

import com.example.toyblog.model.RoleType;
import com.example.toyblog.model.dto.ResponseDto;
import com.example.toyblog.model.entity.User;
import com.example.toyblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ======================================
 * FileName : UserApiController
 * Author : DH.Lee
 * Date : 2024-01-08
 * Note : 38강(블로그 프로젝트) - 회원가입 하기 Ajax요청
 * 1) jQuery를 사용하여 웹 페이지에서 사용자 입력을 받아 AJAX를 통해 서버에 데이터를 전송하는 방법
 *     RESTful 웹 서비스의 컨트롤러로 클라이언트(예: 웹 페이지에서의 AJAX 요청)로부터 사용자 정보를 받아서 처리하고,
 *     요청의 성공 여부를 ResponseDto를 통해 클라이언트에게 알려주는 역할
 *     ResponseDto는 요청 처리 결과를 나타내는 'status' 필드와 결과 데이터를 담는 'data' 필드를 포함한다.
 * ======================================
 */

/**
 * UserApiController : RESTful 웹 서비스의 컨트롤러로 클라이언트(예: 웹 페이지에서의 AJAX 요청)로부터 사용자 정보를 받아서 처리하고,
 * 요청의 성공 여부를 ResponseDto를 통해 클라이언트에게 알려주는 역할
 * ResponseDto는 요청 처리 결과를 나타내는 'status' 필드와 결과 데이터를 담는 'data' 필드를 포함한다.
 */
@RestController
public class UserApiController {

    /**
     * 스프링의 의존성 주입(Dependency Injection, DI)을 위한 어노테이션
     * UserService의 인스턴스가 자동으로 주입
     */
    @Autowired
    private UserService userService; // DI

    /**
     * 회원가입을 위한 API 엔드포인트
     *
     * @param user 사용자로부터 받은 데이터가 담긴 User 객체
     * @return ResponseDto<Integer> 처리 결과를 포함하는 ResponseDto 객체
     * @PostMapping 어노테이션을 사용하여 '/api/user' 경로로 들어오는 POST 요청을 처리한다.
     * @RequestBody 어노테이션을 사용하여 요청 본문에 있는 User 객체의 데이터를 받는다.
     */
    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user) {
        System.out.println("UserApiController : save 호출됨"); // 서버 콘솔에 로그 출력 // 테스트 완료
        // 자바오브젝트를 JSON으로 변환해서 리턴(Jackson이 실행)
        user.setRole(RoleType.USER);
        int result = userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK, 1); // 요청 처리 결과와 데이터를 포함하는 ResponseDto 반환
    }

} // end of class