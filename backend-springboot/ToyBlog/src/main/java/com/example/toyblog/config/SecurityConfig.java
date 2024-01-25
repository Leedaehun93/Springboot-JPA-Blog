package com.example.toyblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * ======================================
 * FileName : SecurityConfig
 * Author : DH.Lee
 * Date : 2024-01-22
 * Note : 49강(블로그 프로젝트) - 스프링 시큐리티 로그인 페이지 커스터마이징
 * 스프링 시큐리티의 폼 로그인(form login) 기능을 사용자 정의 로그인 페이지로 변경
 * 기존 기본 로그인 방식은 주석 처리하여 참조용으로 보존
 * <p>
 * TODO: Date : 2024-01-26
 *  WebSecurityConfigurerAdapter deprecated 사용 불가하여
 *  2.7로 시큐리티 필터 로직으로 변경
 * ======================================
 */

/**
 * @Configuration : 빈 등록(IoC 관리). 이 클래스에서 스프링 빈으로 등록될 객체들을 정의한다.
 */
@Configuration
public class SecurityConfig {

    /**
     * AuthenticationManager를 스프링의 빈으로 등록한다.
     * 인증을 위해 필요한 컴포넌트로, 스프링 시큐리티의 인증 과정에서 중앙 역할을 담당한다.
     *
     * @param authenticationConfiguration 스프링 시큐리티의 기본 인증 구성
     * @return 스프링 시큐리티의 AuthenticationManager 빈
     * @throws Exception 인증 관련 설정에서 발생할 수 있는 예외 처리
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Note : 50강(블로그 프로젝트) - 비밀번호 해시 후 회원가입하기
     */
    /**
     * BCryptPasswordEncoder를 사용하여 비밀번호를 해시로 암호화한다.
     * 이 메서드는 BCryptPasswordEncoder의 인스턴스를 Spring IoC 컨테이너에
     * 비밀번호를 복호화/암호화하는 로직이 담긴 객체를 Bean으로 등록한다.
     * 이렇게 등록된 인코더는 애플리케이션 전반에 걸쳐 비밀번호 암호화에 사용된다.
     *
     * @Bean: 스프링 컨테이너에서 객체를 관리 할 수 있게 하는 것
     * 리턴하는 new BCryptPasswordEncoder 객체를 IoC 컨테이너에서 관리되는 빈으로 등록(관리)한다.
     */
    @Bean
    public BCryptPasswordEncoder encodePWD() {
        return new BCryptPasswordEncoder();
    }

    /**
     * TODO: 2.7로 시큐리티 필터 로직 변경
     * 스프링 시큐리티의 HTTP 보안 설정을 위한 SecurityFilterChain을 정의한다.
     * 이 설정은 모든 HTTP 요청에 대한 보안 요구사항을 구성한다.
     *
     * @param http HttpSecurity 객체를 통해 보안 설정을 정의한다.
     * @return HttpSecurity 객체를 통해 구성된 SecurityFilterChain
     * @throws Exception 보안 설정 과정에서 발생할 수 있는 예외 처리
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 1. csrf 비활성화
        // CSRF 보호 기능을 비활성화한다.(테스트시 사용) 실제 운영 환경에서는 보안 위험을 고려하여 적절한 CSRF 보호를 활성화해야 한다.
        http.csrf().disable();

        // 2. 인증 주소 설정
        // http 요청에 대해서 아래의 하위 경로(**(와일드카드))는 URL 보안 검사를 생략하고 요청은 누구나하여 접근할 수 있다.
        // 그 외 모든 요청에 대해서는 인증된 사용자만 접근할 수 있도록 설정 즉, 로그인한 상태의 사용자만이 애플리케이션의 다른 부분(경로)에 접근할 수 있다.
        http.authorizeRequests(
                authorize -> authorize.antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**", "/dummy/**").permitAll()
                        .anyRequest().authenticated()
        );

        // 3. 로그인 처리 프로세스 설정
        // 폼 기반 로그인 설정을 위한 메서드로 설정을 활성화 한다.
        http.formLogin(f -> f.loginPage("/auth/loginForm") // 사용자가 이 URL로 접속하면, 스프링 시큐리티가 제공하는 기본 로그인 폼 대신 사용자 정의 로그인 페이지(커스텀)가 표시됨
                /**
                 * Note : 52강(블로그 프로젝트) - 스프링 시큐리티 로그인
                 */
                .loginProcessingUrl("/auth/loginProc") // 스프링 시큐리티가 해당 주소로 요청 오는 로그인을 가로채서 대신 로그인을 해준다.
                .defaultSuccessUrl("/") // 정상적인 요청일 때 이 URL로 리다이렉트한다.
        );

        return http.build();
    } // end of configure

} // end of encodePWD