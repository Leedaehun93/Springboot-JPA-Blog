package com.example.toyblog.service;

import com.example.toyblog.model.RoleType;
import com.example.toyblog.model.User;
import com.example.toyblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ======================================
 * FileName : UserService
 * Note :
 * 39강(블로그 프로젝트) - 회원가입 하기 두번째 완료
 * - 사용자 관련 비즈니스 로직을 처리하기 위해 UserService 클래스 구성
 * - 회원가입 메서드 : 사용자를 받아서 회원가입 로직을 처리하고
 * 트랜잭션 관리를 통해 데이터베이스 작업을 관리한다.
 * 49강(블로그 프로젝트) - 스프링 시큐리티 기반 로그인 페이지 커스터마이징
 * - 스프링 시큐리티의 폼 로그인(form login) 기능을 사용자 정의 로그인 페이지로 변경
 * - 기존 기본 로그인 방식은 주석 처리하여 참조용으로 보존
 * 50강(블로그 프로젝트) - 비밀번호 해시 후 회원가입하기
 * - 회원가입 메서드 : 사용자를 받아 회원가입 로직을 처리한다.
 * ======================================
 */

/**
 * UserService 클래스는 사용자 관련 비즈니스 로직을 처리한다.
 * UserRepository와 연결되어 사용자 데이터를 관리하며 데이터베이스와의 상호작용을 담당한다.
 */
@Service
public class UserService {

    /**
     * 스프링의 의존성 주입(Dependency Injection, DI)을 위한 어노테이션
     * 사용자 데이터 접근을 위한 JPA 리포지토리
     * UserRepository의 인스턴스가 자동으로 주입
     */
    @Autowired
    private UserRepository userRepository; // DI

    /**
     * 스프링의 의존성 주입(Dependency Injection, DI)을 위한 어노테이션
     * 비밀번호 암호화를 위한 BCryptPasswordEncoder
     * BCryptPasswordEncoder의 인스턴스가 자동으로 주입
     */
    @Autowired
    private BCryptPasswordEncoder encoder; // DI

    /**
     * 회원가입 메서드 : 사용자를 받아 회원가입 로직을 처리한다.
     * 트랜잭션 관리를 통해 데이터베이스 작업의 일관성과 무결성을 보장한다.
     *
     * @param user 회원가입할 사용자 정보
     * @return 성공 시 1, 실패 시 -1 반환. 예외 발생 시 스택 트레이스를 출력한다.
     * <p>
     * userRepository.save(user)는 User 객체를 데이터베이스에 저장하는 JPA 리포지토리 메서드이다.
     * 이 메서드는 객체를 영구적으로 저장하거나 이미 존재하는 객체를 업데이트한다.
     * @Transactional 어노테이션은 메서드 내의 모든 데이터베이스 작업을 하나의 트랜잭션으로 묶는다.
     */
    @Transactional
    public void 회원가입(User user) {
        // BCryptPasswordEncoder 스프링 시큐리티 패키지를 사용해서 패스워드를 해시로 암호화
        String rawPassword = user.getPassword();            // 원본 패스워드
        String encPassword = encoder.encode(rawPassword);   // 해시(암호화)된 패스워드
        user.setPassword(encPassword);                      // 암호화된 패스워드 설정

        user.setRole(RoleType.USER);                        // 기본 사용자 권한 설정
        try {
            userRepository.save(user);                      // 사용자 정보 데이터베이스에 저장
        } catch (Exception e) {
            e.printStackTrace();
            throw e;

        }
    }

    /**
     * Note : 49강 - 스프링 시큐리티 기반 로그인 페이지 커스터마이징으로 기존 기본 로그인 방식은 주석 처리하여 참조용으로 보존
     */
//    @Transactional(readOnly = true) // Select 할 때 트랜잭션 시작 서비스 종료시에 트랜잭션 종료(정합성 유지가 됨)
//    public User 로그인(User user) {
//        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//    }

} // end of class