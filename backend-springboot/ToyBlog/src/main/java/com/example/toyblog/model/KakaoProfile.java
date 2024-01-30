package com.example.toyblog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * ======================================
 * FileName : KakaoProfile
 * Note :
 * 65강(블로그 프로젝트) - 카카오 로그인 서비스 구현 완료
 * - 카카오 로그인 API로부터 받은 사용자 프로필 데이터를 나타내는 클래스
 * - KakaoProfile 클래스는 jsonschema2pojo를 이용해 자동 생성
 * - 사용자의 선택적 동의에 따라 이메일 정보 접근이 제한됨. 이메일 필드 null 허용 변경으로 테스트(구현 X) *
 * ======================================
 */

/**
 * 카카오 로그인 API로부터 받은 사용자 프로필 데이터를 나타내는 클래스
 * 각 필드는 카카오 API의 응답과 매핑되어 사용자의 기본 정보 및 계정 정보를 포함한다.
 * 이 클래스는 jsonschema2pojo를 이용해 생성되었으며,
 * Lombok 라이브러리의 @Data 어노테이션을 사용하여 기본적인 getter와 setter를 자동으로 생성한다.
 * 자바에서는 하나의 클래스에는 public  하나만 생성 된다.
 * UserController 에서 JSON의 키 네이밍 전략을 스네이크 케이스(snake_case)로 설정한다
 */
@Data
public class KakaoProfile {

    public Long id; // 사용자의 고유 ID
    public String connectedAt; // 연결 시간
    public Properties properties; // 사용자 프로필 이미지 및 썸네일 이미지
    public KakaoAccount kakaoAccount; // 사용자 계정 정보

    /**
     * 사용자의 프로필 이미지와 썸네일 이미지를 포함하는 내부 클래스
     */
    @Data
    public class Properties {

        public String profileImage; // 프로필 이미지 URL
        public String thumbnailImage; // 썸네일 이미지 URL
    } // end of class Properties

    /**
     * 사용자의 카카오 계정 정보를 포함하는 내부 클래스
     * 프로필 이미지 사용 동의 여부 및 상세 프로필 정보를 포함한다.
     */
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class KakaoAccount {

//        public Boolean profile_needs_agreement; // 프로필 이미지 사용 동의 여부
        public Boolean profileImageNeedsAgreement; // 프로필 이미지 사용 동의 여부
        public Profile profile; // 프로필 정보

        /**
         * 사용자의 프로필 이미지와 썸네일 이미지 URL, 기본 이미지 사용 여부를 포함하는 내부 클래스
         */
        @Data
        public class Profile {

            public String thumbnailImageUrl; // 썸네일 이미지 URL
            public String profileImageUrl; // 프로필 이미지 URL
            public Boolean isDefaultImage; // 기본 이미지 사용 여부

        } // end of class Profile

    } // end of class KakaoAccount

} // end of class KakaoProfile