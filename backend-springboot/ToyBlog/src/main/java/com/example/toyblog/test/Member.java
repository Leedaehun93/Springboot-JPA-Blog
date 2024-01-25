package com.example.toyblog.test;

import lombok.*;

/**
 * ======================================
 * FileName : Member
 * Author : DH.Lee
 * Date : 2023-12-12
 * Note : Springboot - 나만의 블로그 만들기 10강 ~ 11강 http 요청 실습 모델 클래스
 * 1) 필드 정의 : 클래스의 구성 요소
 * 2) 생성자 정의 : 객체의 필드 초기화
 * 3) getter, setter 메서드 정의 : 게터 메서드는 각 필드의 값을 반환하고, 세터 메서드는 각 필드에 새로운 값을 설정
 * 4) Lombok 라이브러리 사용 : 복잡한 생성자를 수동으로 작성하는 수고를 덜고 코드의 가독성을 높일 수 있다.
 * @Data : 자바 클래스에 대해 자주 사용되는 메서드들을 자동으로 생성해주는 역할로 아래 생략 어노ㅔ이션을 자동 생략할 수 있게 자동 제공하여 가독성을 높임
 * @AllArgsConstructor : 클래스의 모든 필드를 매개변수로 갖는 전체 생성자(Full-Argument Constructor)를 자동으로 생성
 * @NoArgsConstructor : 매개변수가 없는 기본 생성자(Default Constructor)를 자동으로 생성
 * @Builder : 객체의 생성을 캡슐화하여, 필요한 속성만을 설정하여 객체를 생성할 수 있도록 도와주는 빌더 클래스를 자동으로 생성
 * ======================================
 */

/**
 * Lombok 라이브러리
 */
@Data // 생략가능해짐 @Getter, @Setter, @toString(), @equals(), @hashCode()
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
//  1) 필드 정의
    private int id;
    private String username;
    private String password;
    private String email;

//    2) 생성자 정의
//    @Builder // 빌더 패턴은 객체 생성을 위한 디자인 패턴으로
               // 객체의 생성 과정과 표현 방법을 분리하여, 동일한 생성 과정에서 다른 표현 결과를 얻을 수 있게 합니다.
               // 이 패턴은 특히 많은 매개변수를 갖는 객체를 생성할 때 유용
//    public Member(int id, String username, String password, String email) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//        this.email = email;
//    }
//
//    3) getter, setter 메서드 정의
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }

} // end of class