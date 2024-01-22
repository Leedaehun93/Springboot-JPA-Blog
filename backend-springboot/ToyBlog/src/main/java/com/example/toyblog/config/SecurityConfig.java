package com.example.toyblog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
 * @Configuration : 빈 등록(IoC 관리). 이 클래스에서 스프링 빈으로 등록될 객체들을 정의한다.
 * @EnableWebSecurity : 시큐리티 필터가 등록이 된다. 이 어노테이션을 사용함으로써 Spring Security 설정을 활성화한다.
 * @EnableGlobalMethodSecurity(prePostEnabled = true) : 특정 주소로 접근을 하면 권한 및 인증을 미리 체크한다. 메소드 단위에서 미리 보안 검사를 할 수 있게 한다.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class SecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    /**
                     * HTTP 보안 관련 설정을 시작
                     */
                    .authorizeRequests()    // HTTP 요청에 대한 보안을 설정하기 위한 메서드, 호출을 통해 이후에 나오는 보안 관련 설정이 HTTP 요청에 적용

                    /**
                     *  "/auth/**" 경로로 시작하는 URL은 보안 검사를 생략
                     *  즉, 이 경로(http://localhost:8000/auth/**)에 대한 요청은 누구나 접근할 수 있다.
                     */
                    .antMatchers("/auth/**")    // 특정 경로 패턴을 적용하는 메서드로 "/auth/"로 시작하는 모든 URL 패턴을 지정 + 와일드카드(**)를 사용하여 모든 하위 경로를 포함

                    /**
                     * 그 외 모든 요청에 대해서는 인증된 사용자만 접근할 수 있도록 설정
                     * 즉, 로그인한 상태의 사용자만이 애플리케이션의 다른 부분(경로)에 접근할 수 있다.
                     */
                    .permitAll()        // 로그인하지 않은 사용자도 이 경로에 접근 가능
                    .anyRequest()       // antMatchers("/auth/**")로 지정되지 않은 모든 요청에 적용
                    .authenticated()   // anyRequest()에 의해 적용된 요청들이 인증된 사용자에 의해서만 접근 가능, 사용자가 로그인한 상태여야 해당 요청에 접근할 수 있음

                    .and() // HTTP 보안 설정의 연결을 위해 사용. 이전 설정과 다음 설정을 연결해줌

                    /**
                     * 폼 로그인을 사용할 경우의 설정을 정의
                     */
                    .formLogin()         // 폼 기반 로그인 설정을 위한 메서드
                    /**
                     * 사용자 정의 로그인 페이지 지정
                     * 로그인 페이지로 사용자가 직접 만든 "/auth/loginForm" URL을 사용하도록 설정
                     */
                    .loginPage("/auth/loginForm"); // 사용자가 지정한 로그인 페이지 URL. 사용자가 이 URL로 접속하면, 스프링 시큐리티가 제공하는 기본 로그인 폼 대신 사용자 정의 로그인 페이지가 표시됨
        }

} // end of class
