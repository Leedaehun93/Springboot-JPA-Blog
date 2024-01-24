package com.example.toyblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * ======================================
 * FileName : SecurityConfig
 * Author : DH.Lee
 * Date : 2024-01-22
 * Note : 49강(블로그 프로젝트) - 스프링 시큐리티 로그인 페이지 커스터마이징
 * 스프링 시큐리티의 폼 로그인(form login) 기능을 사용자 정의 로그인 페이지로 변경
 * 기존 기본 로그인 방식은 주석 처리하여 참조용으로 보존
 * ======================================
 */

/**
 * 빈 등록 : 스프링 컨테이너에서 객체를 관리 할 수 있게 하는 것
 *
 * @Configuration : 빈 등록(IoC 관리). 이 클래스에서 스프링 빈으로 등록될 객체들을 정의한다.
 * @EnableWebSecurity : 시큐리티 필터가 등록이 된다. 이 어노테이션을 사용함으로써 Spring Security 설정을 활성화한다.
 * @EnableGlobalMethodSecurity(prePostEnabled = true) : 특정 주소로 접근을 하면 권한 및 인증을 미리 체크한다. 메소드 단위에서 미리 보안 검사를 할 수 있게 한다.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Note : 50강(블로그 프로젝트) - 비밀번호 해시 후 회원가입하기
     */
    /**
     * BCryptPasswordEncoder를 사용하여 비밀번호를 해시로 암호화한다.
     * 이 메서드는 BCryptPasswordEncoder의 인스턴스를 Spring IoC 컨테이너에
     * 비밀번호를 복호화/암호화하는 로직이 담긴 객체를 Bean으로 등록한다.
     * 이렇게 등록된 인코더는 애플리케이션 전반에 걸쳐 비밀번호 암호화에 사용된다.
     */
    @Bean // 리턴하는 new BCryptPasswordEncoder 객체를 IoC 컨테이너에서 관리되는 빈으로 등록(관리)한다.
    public BCryptPasswordEncoder encodePWD() {
        return new BCryptPasswordEncoder();
    }

    /**
     * @Override : WebSecurityConfigurerAdapter 클래스를 상속한다.
     *  상속된 클래스에 정의된 메서드를 오버라이드하여 스프링 시큐리티 설정을 커스터마이즈한다.
     *  만약 WebSecurityConfigurerAdapter 클래스에서 configure 메서드의
     *  시그니처(고유 메서드로 식별)가 변경되거나 메서드가 제거된다면,
     *  오버라이드 어노테이션을 사용함으로써 컴파일 시점에 즉시 오류를 발견할 수 있다.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http                                     // HTTP 보안 설정을 정의하는 메서드, 호출을 통해 이후에 나오는 보안 관련 설정이 HTTP 요청에 적용
                .csrf().disable()                // csrf 토큰 비활성화(테스트시 걸어두는 게 좋으)
                /**
                 * HTTP 보안 관련 설정을 시작
                 *  http 요청에 대해서 모든 사용자가 /** 경로로 요청할 수 있지만, /member/** , /admin/** 경로는 인증된 사용자만 요청이 가능하다.
                 */
                .authorizeRequests()                // HTTP 요청에 대한 보안을 설정한다. 호출을 통해 이후에 나오는 보안 관련 설정이 HTTP 요청에 적용

                /**
                 *  즉, 아래의 경로는 URL 보안 검사를 생략하고 요청은 누구나 접근할 수 있다.
                 *  antMatchers("pathPattern")
                 */
                .antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**", "/lib/**")    // **(와일드카드) 하위 경로까지의 URL 요청에 대해서는 모든 사용자가 접근할 수 있도록 허용한다.

                /**
                 * 그 외 모든 요청에 대해서는 인증된 사용자만 접근할 수 있도록 설정
                 * 즉, 로그인한 상태의 사용자만이 애플리케이션의 다른 부분(경로)에 접근할 수 있다.
                 */
                .permitAll()                        // antMatchers 메서드에 정의된 경로에 대한 접근을 모두에게 허용한다.
                .anyRequest()                       // antMatchers("/auth/**")로 지정되지 않은 모든 요청에 대해
                .authenticated()                    // 인증된 사용자만 접근이 가능하도록 설정한다.

                /**
                 * HTTP 보안 설정의 연결을 위해 사용. 이전 설정과 다음 설정을 연결해 줌
                 */
                .and()

                /**
                 * 폼 로그인을 사용할 경우의 설정을 정의
                 */
                .formLogin()                        // 폼 기반 로그인 설정을 위한 메서드로 설정을 활성화 한다.
                /**
                 * 사용자 정의 로그인 페이지(커스터마이징) 지정
                 * 로그인 페이지로 사용자가 직접 만든 "/auth/loginForm" URL을 사용하도록 설정
                 */
                .loginPage("/auth/loginForm");      // 사용자가 지정한 로그인 페이지 URL(http://localhost:8000/auth/joinForm). 사용자가 이 URL로 접속하면, 스프링 시큐리티가 제공하는 기본 로그인 폼 대신 사용자 정의 로그인 페이지가 표시됨
    } // end of configure

} // end of encodePWD