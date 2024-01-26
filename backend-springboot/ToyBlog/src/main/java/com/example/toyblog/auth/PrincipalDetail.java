package com.example.toyblog.auth;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.toyblog.model.User;
import java.util.ArrayList;
import java.util.Collection;

/**
 * ======================================
 * FileName : PrincipalDetail
 * Note :
 * 52강(블로그 프로젝트) - 스프링 시큐리티 로그인
 * - 로그인 과정에서 사용자의 username과 password를
 * - 인터셉트(intercept)하고 데이터베이스에서 사용자 정보를 검증하는 단계를 구현한다.
 * 52강(블로그 프로젝트) - 스프링 시큐리티 로그인
 * - 스프링 시큐리티의 인증 매니저를 구성하여 로그인 프로세스를 이용한다.
 * - 스프링 시큐리티는 기본적인 사용자 정보만을 세션에 저장하고,
 *   애플리케이션에 특화된 추가적인 사용자 정보(예: 역할, 권한 등)를 관리할 수 없게 된다.
 *   이 클래스와 메서드는 애플리케이션에서 정의한 사용자 엔티티와 스프링 시큐리티 간의
 *   연결고리 역할을 하며,커스터마이징한 사용자 정보를
 *   스프링 시큐리티의 인증 과정에 통합하기 위해 필수적이다.
 * ======================================
 */

/**
 * 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면
 * UserDetail 타입의 오브젝트를 스프링 시큐리티의 고유한 세션저장소에 저장을 해준다.
 */
@Getter
public class PrincipalDetail implements UserDetails {
    /**
     * 콤포지션(composition) : "has-a" 관계로, 한 클래스가 다른 클래스의 인스턴스를 멤버 변수로 포함하는 것
     * 상속(extends) : "is-a" 관계로, 한 클래스가 다른 클래스의 모든 속성과 행동을 상속받는 것
     */
    private User user; // 콤포지션

    /**
     * User 객체를 기반으로 스프링 시큐리티의 UserDetails 인터페이스를 구현한다.
     * PrincipalDetail 생성자 메서드는 사용자 인증 정보를 스프링 시큐리티가 사용할 수 있도록 제공한다.
     *
     * @param user 사용자의 정보를 담고 있는 객체
     */
    public PrincipalDetail(User user) {
        this.user = user;
    }

    /**
     * 사용자의 패스워드를 반환한다.
     *
     * @return 사용자 패스워드
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * 사용자의 사용자 이름을 반환한다.
     *
     * @return 사용자 이름
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * 계정이 만료되지 않았는지 리턴한다.
     *
     * @return true : 만료안 됨
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 계정이 잠겨있지 않았는지 리턴한다.
     *
     * @return true : 잠기지 않음
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 비밀번호가 만료되지 않았는지 리턴한다.
     *
     * @return true : 만료안 됨
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 계정이 활성화(사용가능)인지 리턴한다.
     *
     * @return true : 활성화
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * 사용자 계정이 갖고 있는 권한(역할) 목록을 반환한다.
     * 권한이 여러 개 있을 수 있어서 루프를 돌아야 하는데
     * 현재 구현에서는 사용자가 단일 역할을 가지고 있다고 가정한다.
     * 따라서 반환되는 권한 목록에는 한 개의 역할만 포함된다.
     *
     * @return GrantedAuthority 객체의 컬렉션, 사용자의 역할을 나타냄
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collectors = new ArrayList<>();
        collectors.add(() -> {
            return "ROLE_" + user.getRole(); // "ROLE_" 접두어를 사용하여 권한을 나타냄
        });

        return collectors;
    }
} // end of class