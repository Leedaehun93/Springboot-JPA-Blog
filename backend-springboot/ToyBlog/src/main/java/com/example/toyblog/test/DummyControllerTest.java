package com.example.toyblog.test;

import com.example.toyblog.model.RoleType;
import com.example.toyblog.model.entity.User;
import com.example.toyblog.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Supplier;

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
     * @Autowired : 스프링 프레임워크의 의존성 주입(DI) 기능을 사용한다.
     * 이 어노테이션은 스프링이 관리하는 빈(Bean) 중에서 해당 타입(UserRepository)의 빈을
     * 자동으로 찾아 이 클래스의 필드에 주입한다.
     * 이를 통해 개발자는 UserRepository의 구체적인 생성 방법을 몰라도 되며,
     * 스프링 컨테이너가 빈의 생명주기를 관리하게 된다.
     * 결과적으로, 코드의 결합도가 낮아지고 유지보수성이 향상된다.
     */
    @Autowired
    private UserRepository userRepository; // DI 를 통해 UserRepository 인스턴스를 자동 주입 받는다.


    // http://localhost:8000/blog/dummy/join(요청)
    // http 의 body 에 username, password, email 데이터를 가지고(요청)
    @PostMapping("/dummy/join")
    /**
     * (1) 개별 파라미터를 사용하는 방식 : 이 방식에서는 HTTP 요청의 개별 파라미터를 매핑한다.
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
     * (2) 객체를 사용하는 방식 : HTTP 요청의 파라미터를 User 객체의 속성에 자동으로 매핑하여
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

    } // end of join

    /**
     * ======================================
     * FileName : DummyControllerTest
     * Author : DH.Lee
     * Date : 2023-12-20
     * Note : 26강(블로그 프로젝트) - id로 select 테스트
     * 1) .orElseThrow() 메소드를 활용한 익명 클래스(객체)와 람다 표현식 사용법
     * Java 의 Optional 클래스에서 제공되며, 이를 사용하면 NullPointerException 을 방지하고 코드의 안정성을 향상시킬 수 있다.
     * .orElseThrow() 메소드는 함수형 인터페이스를 파라미터로 받아, 해당 인터페이스의 구현체를 통해 예외를 생성하고 던진다.
     * 2) 스프링 부트의 MessageConverter
     * 요청: 웹브라우저로부터의 HTTP 요청 (예) user 객체: Java 오브젝트, 데이터베이스에서 조회된 사용자 정보를 담고 있다.
     * 변환: 웹브라우저가 이해할 수 있는 데이터 형식(JSON)으로 변환
     * 스프링 부트에서는 MessageConverter 가 응답 시에 자동으로 작동한다.
     * 자바 오브젝트를 리턴할 경우, MessageConverter 는 Jackson 라이브러리를 호출하여
     * user 오브젝트를 JSON 으로 변환하고, 이를 웹브라우저에게 응답으로 전송한다.
     * ======================================
     */

// http://localhost:8000/blog/dummy/user/3 => json 객체 데이터로 반환한다.
// http://localhost:8000/blog/dummy/user/4 => 예외 메시지를 반환한다.
    @GetMapping("/dummy/user/{id}")
/**
 * detail 메소드는 특정 사용자의 상세 정보를 조회하는 기능을 수행한다.
 *
 * @param id 사용자의 고유 식별자
 *  userRepository.findById(id)를 사용하여 데이터베이스에서 사용자 정보를 조회한다.
 *  만약 해당 ID에 대한 사용자 정보가 데이터베이스에 존재하지 않을 경우,
 *  orElseThrow()를 사용하여 사용자 정의 예외를 발생시킨다.
 *  이 메소드에서는 익명 클래스와 람다 표현식을 통해 예외 처리 방법을 보여준다.
 */
    public User detail(@PathVariable int id) {

        // 익명 클래스를 사용한 예외 처리 방법
        /**
         * TODO: 1. 익명 클래스를 활용한 예외 처리
         *  - 전통적인 방식이며, 복잡한 로직이 필요한 경우 유용하다.
         *  - userRepository.findById(id) : 데이터베이스에서 id에 해당하는 사용자 정보를 조회한다.
         *  - 조회된 정보가 없으면 .orElseThrow로 예외를 발생시킨다.
         *  - Supplier 인터페이스의 익명 구현체를 사용하여 IllegalArgumentException 예외를 생성하고 반환한다.
         */
        User user = userRepository.findById(id)
                .orElseThrow(new Supplier<IllegalArgumentException>() {
                    @Override
                    public IllegalArgumentException get() {
                        return new IllegalArgumentException("해당 정보는 잘못된 사용자 정보입니다. 사용자 ID : " + id);
                    }
                });

        // TODO : 아래 부분은 스프링 부트의 MessageConverter 에 대한 설명
        // 요청: 웹브라우저로부터의 HTTP 요청
        // user 객체: Java 오브젝트, 데이터베이스에서 조회된 사용자 정보를 담고 있다.
        // 변환: 웹브라우저가 이해할 수 있는 데이터 형식(JSON)으로 변환
        // 스프링 부트에서는 MessageConverter 가 응답 시에 자동으로 작동한다.
        // 자바 오브젝트를 리턴할 경우, MessageConverter 는 Jackson 라이브러리를 호출하여
        // user 오브젝트를 JSON 으로 변환하고, 이를 웹브라우저에게 응답으로 전송한다.
        return user; // 조회된 사용자 정보 또는 발생한 예외(예외 메시지)를 반환한다.


        // 람다 표현식을 사용한 예외 처리 방법
        /**
         * TODO: 2. 람다 표현식을 활용한 예외 처리
         *  - Java 8 이후 도입된 간단한 로직에 적합한 표현식이다.
         *  - 코드를 익명 클래스보다 더 간결하게 만들 수 있으며, 선언적으로 작성할 수 있다.
         *  - userRepository.findById(id) : 데이터베이스에서 id에 해당하는 사용자 정보를 조회한다.
         *  - 조회된 정보가 없으면 .orElseThrow로 람다 표현식을 사용하여 예외를 발생시킨다.
         */
//        User user = userRepository.findById(id)
//                .orElseThrow(() ->
//                        new IllegalArgumentException("해당 정보는 잘못된 사용자 정보입니다. 사용자 ID : " + id));
//
//        // TODO : 아래 부분은 스프링 부트의 MessageConverter 에 대한 설명
//        // 요청: 웹브라우저로부터의 HTTP 요청
//        // user 객체: Java 오브젝트, 데이터베이스에서 조회된 사용자 정보를 담고 있다.
//        // 변환: 웹브라우저가 이해할 수 있는 데이터 형식(JSON)으로 변환
//        // 스프링 부트에서는 MessageConverter 가 응답 시에 자동으로 작동한다.
//        // 자바 오브젝트를 리턴할 경우, MessageConverter 는 Jackson 라이브러리를 호출하여
//        // user 오브젝트를 JSON 으로 변환하고, 이를 웹브라우저에게 응답으로 전송한다.
//        return user; // 조회된 사용자 정보 또는 발생한 예외(예외 메시지)를 반환한다.

    } // end of detail


    /**
     * ======================================
     * FileName : DummyControllerTest
     * Author : DH.Lee
     * Date : 2023-12-21
     * Note : 27강(블로그 프로젝트) - 전체 select 및 paging 테스트
     * 1) 전체 조회(여러 건의 데이터)
     * 2) paging 처리를 위한 2가지 방법 : List<User> 반환 타입과 Page<User> 반환 타입
     * ======================================
     */

    /**
     * 전체 조회
     *
     * @GetMapping 어노테이션을 통해 /dummy/users URL에 대한 HTTP GET 요청을 list() 메소드에 매핑하며,
     * 이 메소드는 userRepository.findAll() 을 호출하여 모든 User 객체를 List 형태로 반환한다.
     */
    // http://localhost:8000/blog/dummy/users
    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    } // end of list

    /**
     * TODO: 1. paging 처리 List<User> 반환 타입 :
     *  반환된 페이지 정보에는 단순히 User 객체들의 리스트 정보만 제공한다.
     *  이 경우 페이지 정보가 필요하지 않은 경우에 적합하다.
     * @GetMapping 어노테이션을 통해 /dummy/user URL에 대한 HTTP GET 요청을 PageList 메소드에 매핑하며,
     *  이 메소드는 userRepository.findAll(pageable) 을 호출하여 User 객체를 한 페이지당 2건으로(페이지 크기를 2로 설정하고)
     */
    // http://localhost:8000/blog/dummy/user
    // http://localhost:8000/blog/dummy/user?page=0
    // http://localhost:8000/blog/dummy/user?page=1
    @GetMapping("/dummy/user")
    public List<User> PageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> pagingUser = userRepository.findAll(pageable);
        return pagingUser.getContent();
//    }

        /**
         * TODO: 2. paging 처리 Page<User> 반환 타입 :
         *  반환된 페이지 정보에는 User 객체의 리스트뿐만 아니라 총 페이지 수, 현재 페이지 번호 등의 페이징 관련 정보도 포함된다.
         * @GetMapping 어노테이션을 통해 /dummy/user URL에 대한 HTTP GET 요청을 PageList 메소드에 매핑하며,
         *  이 메소드는 userRepository.findAll(pageable)을 호출하여 User 객체의 페이지 정보를 반환한다.
         */
        // http://localhost:8000/blog/dummy/user
        // http://localhost:8000/blog/dummy/user?page=0
        // http://localhost:8000/blog/dummy/user?page=1
//    @GetMapping("/dummy/user")
//    public Page<User> PageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
//        return userRepository.findAll(pageable);

    } // end of PageList

} // end of class
