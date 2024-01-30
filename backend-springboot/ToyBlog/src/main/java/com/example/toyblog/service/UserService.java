package com.example.toyblog.service;

import com.example.toyblog.model.RoleType;
import com.example.toyblog.model.User;
import com.example.toyblog.repository.UserRepository;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
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
 * 60강(블로그 프로젝트) - 회원수정 1
 * 65강(블로그 프로젝트) - 카카오 로그인 서비스 구현 완료
 * - @Autowired UserRepository
 * - 회원찾기 메서드 로직
 * - 카카오 로그인 생성 계정에 대한 비밀번호 변경 로직 제한 추가
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
    private UserRepository userRepository; // 의존성 주입(DI)

    /**
     * 스프링의 의존성 주입(Dependency Injection, DI)을 위한 어노테이션
     * 비밀번호 암호화를 위한 BCryptPasswordEncoder
     * BCryptPasswordEncoder의 인스턴스가 자동으로 주입
     */
    @Autowired
    private BCryptPasswordEncoder encoder; // 의존성 주입(DI)


    /**
     * 사용자 이름을 기반으로 사용자를 찾는 회원찾기 메서드
     * 트랜잭션 관리를 통해 데이터베이스 읽기 작업의 일관성을 보장한다. (readOnly = true)
     *
     * @param username 찾고자 하는 사용자의 이름
     * @return 찾은 사용자 또는 새로운 사용자 객체. 사용자가 없을 경우 새 User 인스턴스 반환.
     * <p>
     * userRepository.findByUsername(username)은 username에 해당하는 User 객체를 찾는 JPA 리포지토리 메서드이다.
     * 이 메서드는 Optional<User> 객체를 반환하며, 해당 사용자가 없을 경우 기본값으로 새 User 객체를 반환한다. (orElseGet 사용)
     * @Transactional(readOnly = true) 어노테이션은 이 메서드의 데이터베이스 작업이 읽기 전용임을 나타낸다.
     */
/*    @Transactional(readOnly = true)
    public  User 회원찾기(String username) {
        User user = userRepository.findByUsername(username).get();
        return user;
    }*/
    @Transactional(readOnly = true)
    public User 회원찾기(String username) {
        User user = userRepository.findByUsername(username).orElseGet(() -> {
            return new User();
        });
        return user;
    }

    /**
     * 사용자를 받아 회원가입 로직을 처리하는 회원가입 메서드
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
     * 주어진 사용자 정보로 기존 회원 정보를 수정하는 메서드.
     * 이 메서드는 트랜잭션 내에서 실행되며, JPA의 영속성 컨텍스트를 이용해 사용자 정보의 영속화와 변경 감지(더티 체킹)를 관리한다.
     * findById 메서드를 통해 사용자 엔티티를 조회하고, 이를 영속화 상태로 만들어 필드의 변경을 감지하도록 한다.
     * 변경된 엔티티는 트랜잭션이 종료될 때 데이터베이스에 자동으로 반영된다.
     *
     * @param user 수정할 사용자 정보를 담고 있는 엔티티. ID를 통해 기존 사용자를 찾고 정보를 업데이트한다.
     * @throws IllegalIdentifierException 사용자를 찾지 못한 경우 발생하는 사용자 정의 예외를 발생시킨다.
     */
    @Transactional
    public void 회원수정(User user) {
        // findById를 통해 영속화 할 사용자 객체를 조회한다.
        User persistence = userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalIdentifierException("회원 찾기 실패"));

        // Validate 체크
        // 카카오 로그인으로 생성된 계정이 아닐 때만 비밀번호를 업데이트한다. 즉 oauth 필드에 값이 없으면 수정 가능하다.
        if (persistence.getOauth() == null || persistence.getOauth().isEmpty()) {
            // 사용자로부터 받은 비밀번호를 암호화하여 설정한다.
            String rawPassword = user.getPassword();
            String encPassword = encoder.encode(rawPassword);
            persistence.setPassword(encPassword);
            // 이메일 정보를 업데이트한다.
            persistence.setEmail(user.getEmail());
        }
        // 카카오 로그인으로 생성된 계정인 경우 비밀번호 변경을 허용하지 않으므로, 비밀번호 변경 로직을 생략한다.
    }
    // 트랜잭션 종료 시 변경 감지가 발생하며, 변경된 내용이 데이터베이스에 자동으로 반영된다.
    // 즉, 회원수정 함수 종료시 = 서비스 종료 = 트랜잭션 종료 = commit

    /**
     * Note : 49강 - 스프링 시큐리티 기반 로그인 페이지 커스터마이징으로 기존 기본 로그인 방식은 주석 처리하여 참조용으로 보존
     */
/*    @Transactional(readOnly = true) // Select 할 때 트랜잭션 시작 서비스 종료시에 트랜잭션 종료(정합성 유지가 됨)
    public User 로그인(User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }*/

} // end of class