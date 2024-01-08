package com.example.toyblog.service;

import com.example.toyblog.model.entity.User;
import com.example.toyblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ======================================
 * FileName : UserService
 * Author : DH.Lee
 * Date : 2024-01-08
 * Note : 39강(블로그 프로젝트) - 회원가입 하기 두번째 완료
 * 1) 사용자 관련 비즈니스 로직을 처리하기 위해 UserService 클래스 구성
 *    회원가입 메서드 : 사용자를 받아서 회원가입 로직을 처리하고
 *    트랜잭션 관리를 통해 데이터베이스 작업을 관리한다.
 * ======================================
 */

/**
 * UserService 클래스는 사용자 관련 비즈니스 로직을 처리하기 위해 구성한다.
 * UserService 클래스는 사용자 데이터를 관리하고,
 * 데이터베이스와의 상호작용을 담당하는 UserRepository와 연결된다.
 */
@Service
public class UserService {

    /**
     * 스프링의 의존성 주입(Dependency Injection, DI)을 위한 어노테이션
     * UserRepository의 인스턴스가 자동으로 주입
     */
    @Autowired
    private UserRepository userRepository; // DI

    /**
     * 회원가입 메서드: 사용자를 받아 회원가입 로직을 처리한다.
     * 트랜잭션 관리를 통해 데이터베이스 작업의 일관성과 무결성을 보장한다.
     *
     * @param user 회원가입할 사용자 정보
     * @return 성공 시 1, 실패 시 -1 반환. 예외 발생 시 스택 트레이스를 출력한다.
     *
     * userRepository.save(user)는 User 객체를 데이터베이스에 저장하는 JPA 리포지토리 메서드이다.
     * 이 메서드는 객체를 영구적으로 저장하거나 이미 존재하는 객체를 업데이트한다.
     *
     * @Transactional 어노테이션은 메서드 내의 모든 데이터베이스 작업을 하나의 트랜잭션으로 묶는다.
     */
    @Transactional
    public int 회원가입(User user) {
        try {
            userRepository.save(user);
            return 1; // 성공 시 1 반환
        } catch (Exception e) {
            e.printStackTrace(); // 예외 발생 시 스택 트레이스를 출력
            System.out.println("UserService:회원가입() :" + e.getMessage());
        }
        return -1; // 실패 시 -1 반환
    }

} // end of class