package com.example.toyblog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.toyblog.model.User;
import com.example.toyblog.repository.UserRepository;

/**
 * ======================================
 * FileName : PrincipalDetailService
 * Note :
 * 52강(블로그 프로젝트) - 스프링 시큐리티 로그인
 * - 스프링 시큐리티의 인증 매니저를 구성하여 로그인 프로세스를 이용한다.
 * - 로그인 과정에서 사용자의 username과 password를
 *   인터셉트(intercept)하고 데이터베이스에서 사용자 정보를 검증하는 단계를 구현한다.
 * - PrincipalDetailService 클래스와 loadUserByUsername 메서드를 구현하지 않으면,
 *   스프링 시큐리티는 기본적인 사용자 정보만을 세션에 저장하고,
 *   애플리케이션에 특화된 추가적인 사용자 정보(예: 역할, 권한 등)를 관리할 수 없게 된다.
 *   이 클래스와 메서드는 애플리케이션에서 정의한 사용자 엔티티와 스프링 시큐리티 간의 연결고리 역할을 하며,
 *   커스터마이징한 사용자 정보를 스프링 시큐리티의 인증 과정에 통합하기 위해 필수적이다.
 * ======================================
 */

@Service // Bean 등록
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository; // 의존성 주입(DI)

    /**
     * 스프링 시큐리티가 로그인 과정 중에 사용자의 username과 password를 인터셉트(intercept) 할 때 호출되는 메서드이다.
     * 이 메서드는 제공된 username이 데이터베이스에 존재하는지 확인한다. password 검증은 스프링 시큐리티가 자동으로 처리한다.
     *
     * @param username 사용자 로그인 시 입력된 사용자 이름.
     * @return UserDetails 인터페이스를 구현한 PrincipalDetail 객체. 이 객체는 사용자의 인증 정보를 포함한다.
     * @throws UsernameNotFoundException 제공된 username에 해당하는 사용자를 찾을 수 없을 때 예외를 발생시킨다.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User principal = userRepository.findByUsername(username)
        .orElseThrow(()->{
            return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다.: "+username);
        });
        // PrincipalDetail 객체를 생성하여 스프링 시큐리티 세션에 사용자 인증 정보를 저장한다. 사용자 데이터베이스에 정의된 사용자 정보를 기반으로 인증 과정을 수행한다.
        return new PrincipalDetail(principal);
    }

} // end of class