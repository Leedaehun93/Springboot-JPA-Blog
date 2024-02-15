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
 * Note :
 * 49강(블로그 프로젝트) - 스프링 시큐리티 로그인 페이지 커스터마이징
 * - 스프링 시큐리티의 폼 로그인(form login) 기능을 사용자 정의 로그인 페이지로 변경
 * - 기존 기본 로그인 방식은 주석 처리하여 참조용으로 보존
 * TODO: 49강(블로그 프로젝트) - 스프링 시큐리티 기반 로그인 페이지 커스터마이징 구성 설정 방식 변경
 *  WebSecurityConfigurerAdapter deprecated 사용 불가하여(에러 발생) 2.7로 시큐리티 필터 로직으로 변경 필요
 *  - Spring Security 구성 방식을 Spring 5.4 이상의 권장 방식으로 변경
 *  - WebSecurityConfigurerAdapter 상속 대신 SecurityFilterChain 빈을 사용하여 보안 설정 구성
 *  - 로그인 페이지 URL 및 보안 설정의 코드 스타일을 현대적 방식으로 업데이트 수정
 * 50강(블로그 프로젝트) - 비밀번호 해시 후 회원가입하기
 * - BCryptPasswordEncoder(비크립트패스워드엔코더) 라는
 *   스프링 시큐리티 패키지를 사용해서 패스워드를 해시로 암호화한다.
 * - 이 메서드는 BCryptPasswordEncoder의 인스턴스를
 *   Spring IoC 컨테이너에 비밀번호를 복호화/암호화하는
 *   로직이 담긴 객체를 Bean으로 등록한다.
 *   이렇게 등록된 인코더는 애플리케이션 전반에 걸쳐 비밀번호 암호화에 사용된다.
 * 52강(블로그 프로젝트) - 스프링 시큐리티 로그인
 * - 스프링 시큐리티의 인증 매니저를 구성하여 로그인 프로세스를 이용한다.
 * 61강(블로그 프로젝트) - 회원수정 2
 * - 회원 정보 수정 이후 세션 정보를 갱신하기 위해 AuthenticationManager로
 *   새로운 인증 토큰을 생성하고 SecurityContext에 설정하여 현재 세션을 업데이트함.
 * - 회원 수정 로직 실행 시 트랜잭션이 종료되면서 데이터베이스와 세션의 일관성 유지.
 * - TODO: 아래 내용과 동일 확인해 보기
 *    스프링 시큐리티 5.7.1 버전부터 접근 방식 업데이트 됨
 * 49강(블로그 프로젝트) - 스프링 시큐리티 기반 로그인 페이지 커스터마이징 구성 설정 방식 변경
 * 72강(블로그 프로젝트) - 회원가입 문제와 게시글 삭제 문제 해결
 * ======================================
 */

/**
 * @Configuration : 빈 등록(IoC 관리). 이 클래스에서 스프링 빈으로 등록될 객체들을 정의한다.
 */
@Configuration
public class SecurityConfig {

    /**
     * 스프링 시큐리티 5.7.1 버전부터 접근 방식 업데이트 됨
     *
     * 인증을 위해 필요한 컴포넌트로, 스프링 시큐리티의 인증 과정에서 중앙 역할을 담당한다.
     * 스프링 시큐리티의 AuthenticationManager 빈을 설정한다.
     * 스프링 시큐리티 5.7.1 이후 버전에서는 WebSecurityConfigurerAdapter의 상속 없이
     * AuthenticationManager를 빈으로 직접 등록할 수 있다. 이를 통해 인증 프로세스를 관리하는
     * AuthenticationManager를 얻을 수 있다.
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
                .loginProcessingUrl("/auth/loginProc") // 스프링 시큐리티가 해당 주소로 요청 오는 로그인을 가로채서 대신 로그인을 해준다.
                .defaultSuccessUrl("/") // 정상적인 요청일 때 이 URL로 리다이렉트한다.
        );
        return http.build();
    } // end of configure

} // end of encodePWD