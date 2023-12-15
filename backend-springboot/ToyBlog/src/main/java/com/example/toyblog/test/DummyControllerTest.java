package com.example.toyblog.test;

import com.example.toyblog.model.RoleType;
import com.example.toyblog.model.entity.User;
import com.example.toyblog.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ======================================
 * FileName : DummyControllerTest
 * Author : DH.Lee
 * Date : 2023-12-15
 * Note : 24강(블로그 프로젝트) - 회원가입 위한 insert 테스트
 * 1) Postman 에서 POST 방식의 http://localhost:8000/blog/dummy/join 도메인 요청
 * 2) Params 타입을 Body로 선택하고 x-www-form-urlencoded  MIME 타입으로 진행한다.
 * x-www-form-urlencoded -> key=value&key=value&key=value
 * ======================================
 */
@RestController
public class DummyControllerTest {

    /**
     * TODO: @Autowired : 스프링 프레임워크의 의존성 주입(DI) 기능을 사용한다.
     *  이 어노테이션은 스프링이 관리하는 빈(Bean) 중에서 해당 타입(UserRepository)의 빈을
     *  자동으로 찾아 이 클래스의 필드에 주입한다.
     *  이를 통해 개발자는 UserRepository의 구체적인 생성 방법을 몰라도 되며,
     *  스프링 컨테이너가 빈의 생명주기를 관리하게 된다.
     *  결과적으로, 코드의 결합도가 낮아지고 유지보수성이 향상된다.
     */
    @Autowired
    private UserRepository userRepository; // DI 를 통해 UserRepository 인스턴스를 자동 주입 받는다.

    // http://localhost:8000/blog/dummy/join(요청)
    // http 의 body 에 username, password, email 데이터를 가지고(요청)
    @PostMapping("/dummy/join")
    /**
     * TODO: (1) 개별 파라미터를 사용하는 방식 : 이 방식에서는 HTTP 요청의 개별 파라미터를 매핑한다.
     *  간단한 요청일 경우에만 사용한다.
     */
//    public String join(String username, String password, String email) { // key=value (약속된 규칙)
//        System.out.println("username: " + username);
//        System.out.println("password: " + password);
//        System.out.println("email: " + email);
//
//        return "회원가입이 완료되었습니다.";
//    }
    /**
     * TODO: (2) 객체를 사용하는 방식 : HTTP 요청의 파라미터를 User 객체의 속성에 자동으로 매핑하여
     *  여러 데이터 필드를 포함하는 복잡한 요청을 쉽게 처리 가능하다.
    */
    public String join(User user) { // key=value (약속된 규칙)

        System.out.println("username : " + user.getUsername());
        System.out.println("password : " + user.getPassword());
        System.out.println("email : " + user.getEmail());

        System.out.println("Id : " + user.getId());
        System.out.println("Role : " + user.getRole());
        System.out.println("CreteDate : " + user.getCreteDate());

        /**
         * 기본 값 : USER 지정
         */
        user.setRole(RoleType.USER);
        /**
         * user 데이터베이스로 저장
         */
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }

} // end of class
