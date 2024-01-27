package com.example.toyblog.controller.api;

import com.example.toyblog.dto.ResponseDto;
import com.example.toyblog.model.User;
import com.example.toyblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ======================================
 * FileName : UserApiController
 * Note :
 * 38강(블로그 프로젝트) - 회원가입 하기 Ajax요청
 * - jQuery를 사용하여 웹 페이지에서 사용자 입력을 받아 AJAX를 통해 서버에 데이터를 전송하는 방법
 * RESTful 웹 서비스의 컨트롤러로 클라이언트(예: 웹 페이지에서의 AJAX 요청)로부터 사용자 정보를 받아서 처리하고,
 * 요청의 성공 여부를 ResponseDto를 통해 클라이언트에게 알려주는 역할
 * ResponseDto는 요청 처리 결과를 나타내는 'status' 필드와 결과 데이터를 담는 'data' 필드를 포함한다.
 * 60강(블로그 프로젝트) - 회원수정 1
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
    private UserService userService; // 의존성 주입(DI)

    /**
     * TODO: 회원가입시 throws Exception(try, catch) 테스트 해 보기
     */
//    @PostMapping("/api/user")
//    public ResponseDto<Integer> save(@RequestBody User user) throws Exception {
//        System.out.println("UserApiController : save 호출됨"); // 서버 콘솔에 로그 출력 // 테스트 완료
//        // 자바오브젝트를 JSON으로 변환해서 리턴(Jackson이 실행)
//        user.setRole(RoleType.USER);
//        userService.회원가입(user);
//        try{
//            ObjectMapper om = new ObjectMapper();
//            System.out.println(om.writeValueAsString(user));
//            throw new Exception("forced");
//        }catch (Exception e){
//            System.out.println(e);
//            throw e;
//        }
//    }
    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) throws Exception { // username, password, email
        System.out.println("UserApiController : save 호출됨"); // 서버 콘솔에 로그 출력 // 테스트 완료
        // 자바오브젝트를 JSON으로 변환해서 리턴(Jackson이 실행)
        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 요청 처리 결과와 데이터를 포함하는 ResponseDto 반환 // 200 정상 실행
    }

    /**
     * Note : 49강 - 스프링 시큐리티 기반 로그인 페이지 커스터마이징으로 기존 기본 로그인 방식은 주석 처리하여 참조용으로 보존
     */
// Spring Security 사용으로 전통적인 로그인 방식은 주석 처리
//    @PostMapping("/api/user/login")
//    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
//        System.out.println("UserApiController : login호출됨");
//        User principal = userService.로그인(user); // principal(접근주체)
//        if (principal != null) {
//            session.setAttribute("principal", principal);
//        }
//        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 요청 처리 결과와 데이터를 포함하는 ResponseDto 반환 // 200 정상 실행
//    }

    /**
     * 사용자의 정보를 수정하는 API 엔드포인트
     * HTTP PUT 요청을 통해 전송된 사용자 정보를 바탕으로 회원 정보를 업데이트한다.
     * 요청 본문에서 User 객체를 JSON 형태로 받아와 userService의 회원수정 기능을 호출한다.
     * 성공적으로 처리된 경우, HttpStatus.OK와 함께 성공 상태 코드를 나타내는 ResponseDto를 반환한다.
     *
     * @param user 요청 본문에서 전달받은 User 객체, 회원의 새로운 정보를 포함한다.
     * @return ResponseDto<Integer>는 처리 결과를 나타내는 상태 코드와 데이터를 포함한다.
     *         여기서 데이터는 '1'로, 성공적인 수정을 나타낸다.
     */
    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody User user) {
        userService.회원수정(user); // 서비스 계층에서 회원 정보 수정 로직을 수행
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 요청 처리 결과와 데이터를 포함하는 ResponseDto 반환 // 200 정상 실행
    }

} // end of class