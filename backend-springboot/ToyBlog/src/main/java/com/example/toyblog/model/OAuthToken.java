package com.example.toyblog.model;

import lombok.Data;

/**
 * ======================================
 * FileName : OAuthToken
 * Note :
 * 65강(블로그 프로젝트) - 카카오 로그인 서비스 구현 완료
 *  - OAuth 인증 과정에서 사용되는 토큰 정보를 나타내는 클래스
 * ======================================
 */

/**
 * OAuth 인증 과정에서 사용되는 토큰 정보를 나타내는 클래스
 *
 * 이 클래스는 OAuth 인증 과정에서 얻은 토큰과 관련된 정보를 저장하는데 사용된다.
 * 주요 필드로는 액세스 토큰(access_token), 토큰 타입(token_type), 리프레시 토큰(refresh_token),
 * ID 토큰(id_token), 토큰의 유효 시간(expires_in), 범위(scope), 리프레시 토큰의 유효 시간(refresh_token_expires_in) 등이 있다.
 * 이 클래스의 인스턴스는 주로 API 요청에서 인증을 위해 사용되며,
 * 각 필드는 OAuth 인증 서버로부터 받은 응답에 따라 할당된다.
 *
 * @Data Lombok 라이브러리의  @Data  어노테이션을 사용하여 기본적인 getter와 setter, toString 등을 자동으로 생성
 */
@Data
public class OAuthToken {

    private String access_token;             // OAuth 액세스 토큰
    private String token_type;               // 토큰 타입
    private String refresh_token;            // 토큰 갱신을 위한 리프레시 토큰
    private String id_token;                 // 사용자 식별을 위한 ID 토큰
    private int expires_in;                  // 액세스 토큰의 유효 시간(초)
    private String scope;                    // 액세스 토큰의 범위
    private int refresh_token_expires_in;    // 리프레시 토큰의 유효 시간(초)

} // end of class OAuthToken
