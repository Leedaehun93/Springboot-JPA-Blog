package com.example.toyblog;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * ======================================
 * FileName : EndTest
 * Note : 50강(블로그 프로젝트) - 비밀번호 해시 후 회원가입하기
 * - BCryptPasswordEncoder(비크립트패스워드엔코더) 라는
 * - 스프링 시큐리티 패키지를 사용해서 패스워드를 해시로 암호화 테스트 해 보기
 * ======================================
 */
public class EndTest {

    /**
     * JUnit 5 테스트 프레임워크를 사용한 테스트 클래스
     * <p>
     * EndTest 클래스에서 해시_암호화 메서드는 @Test 어노테이션으로 표시되어 있으며, JUnit 테스트로 인식된다.
     * 이 메서드는 BCryptPasswordEncoder를 사용하여 "1234" 문자열을 해시 처리하고 결과를 콘솔에 출력한다.
     * 이 테스트는 별도의 main 메서드 없이도 JUnit 테스트 러너에 의해 실행된다.
     * JUnit은 @Test, @Before, @After 등의 애노테이션들을 사용하여 테스트 메서드를 식별하고 실행한다.
     */
    @Test
    public void 해시_암호화() {
        String encPassword = new BCryptPasswordEncoder().encode("1234"); // BCryptPasswordEncoder의 빈을 생성(비밀번호 암호화에 사용된다.)
        System.out.println("1234해시:" + encPassword);
    }

} // end of class