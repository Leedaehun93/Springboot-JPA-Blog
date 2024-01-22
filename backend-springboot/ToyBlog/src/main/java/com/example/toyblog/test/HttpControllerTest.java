package com.example.toyblog.test;

import org.springframework.web.bind.annotation.*;

/**
 * ======================================
 * FileName : HttpControllerTest
 * Author : DH.Lee
 * Date : 2023-12-12
 * Note : Springboot - 나만의 블로그 만들기 10강 ~ 11강 http 요청 실습 컨트롤러
 * 1) 웹 브라우저가 주로 GET 요청을 사용하여 서버에 데이터를 요청한다.
 * 웹 브라우저는 주로 GET 요청을 사용하여 서버에 데이터를 요청하며,
 * TODO: 이 때 쿼리 스트링을 사용하는 것이 가장 일반적인 방법이다.
 * 그러나 폼을 사용한 GET 요청도 가능하다.
 * (1-1) get 요청 시 RequestParam을 사용해서 Mapping 받기
 *      @GetMapping 메소드에서 @RequestParam을 사용하여 GET 요청의 파라미터를 매핑하는 방법
 * (1-2) get 요청 시 Member Objet 를 활용하여 Mapping 받기
 *      스프링 프레임워크가 객체를 통해 자동으로 요청 파라미터를 바인딩하여 쉽게 사용 할 수 있음
 *
 * 2) post 요청 시 RequestBody를 사용하여 파싱한다.
 * @RequestBody는 클라이언트로부터 받은 JSON이나 텍스트 데이터를 Java 객체로 변환하는 데 사용된다.
 * (2-1) post 요청 시 text/plain으로 보내는 방법
 * @RequestBody 를 사용하여 text/plain 타입의 데이터를 String 형태로 받는 방법으로 문자열 형태의 데이터를 받아 그대로 반환한다.
 * (2-2) application/json으로 보내는 방법
 * @RequestBody 를 사용하여 application/json 형태의 데이터를 받아 Member 객체로 파싱한 후, 해당 객체의 필드를 사용하여 응답 문자열을 구성한다.
 * <p>
 * TODO: MessageConverter 기능과 역할:
 *        스프링 프레임워크에서 클라이언트와 서버 간의 데이터 교환을 위해
 *        HTTP 요청 및 응답 본문을 특정 형식으로 변환하는 역할을 한다.
 *        주로 REST API에서 JSON, XML 같은 데이터 형식으로 객체를 변환하는 데 사용되고
 *        예시로 SON 데이터를 자바 객체로 파싱해주는 역할을 한다.(변환하거나 반대로 변환하는 역할)
 *        REST API를 구현하는 데 있어서 데이터 형식 간의 변환이 필수적인데,
 *        이 변환 과정을 MessageConverter가 담당하여 개발자가 보다 효율적으로 API를 구현할 수 있도록 도와준다.
 * <p>
 * TMI
 * 스프링 프레임워크에서 MessageConverter의 "기능"은 HTTP 요청과 응답을 특정 데이터 형식으로 변환하는 것
 * 반면, MessageConverter의 "역할"은 스프링 MVC 아키텍처 내에서 데이터 변환을 관리하고 REST API의 효율적인 구현을 지원하는 것
 * ======================================
 */

// 사용자가 요청 - > 응답(HTML 파일)
// @Controller

// 사용자가 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {

    private static final String TAG = "HttpControllerTest : ";

    //  TODO: http://localhost:8080/http/lombok
// TODO: 에서 서버 포트와 컨텍스트패스 변경함
    //  TODO: http://localhost:8000/blog/http/lombok
    @GetMapping("/http/lombok")
    public String lombokTest() {
//        Member m = new Member(1, "ssar", "1234", "email");
        Member m = Member.builder().username("ssar").password("1234").email("ssar@tate.com").build();
        System.out.println(TAG + "getter : " + m.getUsername());
        m.setUsername("cos");
        System.out.println(TAG + "setter : " + m.getUsername());
        return "lombok test 완료";
    }

// TODO: 1) 웹 브라우저가 주로 GET 요청을 사용하여 서버에 데이터를 요청한다.
//  TODO: (1-1) get 요청 시 RequestParam을 사용해서 Mapping 받기
    // http://localhost:8080/http/get(select)
//    @GetMapping("/http/get")
//    public String getTest(@RequestParam int id, @RequestParam String username) {
//        return "get 요청 : " + id + ", " + username ;
//    }

    //   TODO: (1-2) Member Objet를 활용하여 Mapping 받기
    // TODO: http://localhost:8080/http/get(select)
    @GetMapping("/http/get")
    public String getTest(Member m) { // id=1&username=ssar&password=1234&email=ssar@nate.com // MessageConverter(스프링)
        return "get 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }

    // TODO: 2) post 요청 시  RequestBody를 사용하여 파싱한다.
    // TODO: http://localhost:8080/http/post(insert)
    @PostMapping("/http/post")
    // TODO: (2-1) text/plain으로 보내는 방법
//    public String postTest(@RequestBody String test) { // MessageConverter(스프링)
//        return "post 요청 : " + test ;
//    }
    // TODO: (2-2) application/json으로 보내는 방법
    public String postTest(@RequestBody Member m) { // MessageConverter(스프링)
        return "post 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }

    // TODO: http://localhost:8080/http/put(update)
    @PutMapping("/http/put")
    public String putTest(@RequestBody Member m) {
        return "put 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }

    // TODO: http://localhost:8080/http/get(delete)
    @DeleteMapping("/http/delete")
    public String deleteTest() {
        return "delete 요청";
    }

} // end of class
