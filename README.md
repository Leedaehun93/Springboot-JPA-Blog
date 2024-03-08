# Springboot-JPA-Blog 블로그 웹 사이트

이 프로젝트는 Spring Boot와 JPA를 사용하여 블로그 웹 사이트를 구현한 것입니다. 모던 웹 개발 스킬을 학습하고, 실제 웹 애플리케이션에서 OAuth 2.0을 통한 소셜 로그인 및 보안 설정과 댓글 CRUD 구현을 학습하기 위해 만들어졌습니다.

## 🚀 프로젝트 학습 목표

-   웹 애플리케이션의 전체 생명 주기에 대한 실제 경험 및 최신 기술 버전과 변경 사항 이해
-   Spring Boot DevTools와 Lombok 등의 개발 툴을 활용하여 개발 효율성 향상과 이해
-   Spring Security와 JWT를 통한 보안 강화 및 인증 시스템 구현에 대한 이해
-   RESTful API 설계 및 구현을 통한 백엔드와 프론트엔드 간의 효율적인 데이터 통신 경험 강화
-   MySQL을 사용한 데이터베이스 설계 및 관리

## 🎮 사용 기술

-   언어 및 프레임워크 :

    -   ✨ Front-End :
        -   HTML5
        -   CSS3
        -   JavaScript (ES6)
    -   💻 Back-End :
        -   Java 11
        -   Spring Boot 2.7.17
    -   💾 DateBase :
        -   MySQL 8.0

## ⚙ 의존성 요약

이 프로젝트에서는 다음과 같은 주요 의존성들을 사용하고 있습니다.

-   `Spring Boot DevTools`: 클래스 변경 시 서버를 자동으로 재기동할 수 있게 하는 개발 도구입니다.
-   `Lombok`: 반복적인 코드 작성 없이, 간단한 어노테이션을 사용하여 메소드를 자동으로 생성합니다.
-   `Spring Boot Starter Data JPA`: 데이터 접근 계층을 구축하는 데 사용되는 스프링 부트 모듈입니다.
-   `MySQL Connector/J`: MySQL 데이터베이스와의 연결을 관리하는 JDBC 드라이버입니다.
-   `Tomcat Embed Jasper`: JSP 파일을 컴파일하고 실행하는 데 필요한 템플릿 엔진입니다.
-   `Spring Boot Starter Web`: 웹 애플리케이션을 구축하는 데 필요한 스프링 부트 모듈입니다.
-   `Spring Boot Starter Security`: 애플리케이션의 보안을 위한 스프링 부트 모듈입니다.
-   `Spring Security Taglibs`: JSP에서 스프링 시큐리티 기능을 태그로 사용할 수 있게 해주는 라이브러리입니다.
-   `JSTL`: JSP에서 사용할 수 있는 표준 태그 라이브러리입니다.

테스트 의존성(testDependencies)으로는:

-   `Spring Boot Starter Test`: 테스트에 필요한 주요 라이브러리를 포함합니다.
-   `Spring Security Test`: 스프링 시큐리티 설정을 테스트 할 때 사용합니다.

## 📸 화면구성

![슬라이드1](https://github.com/Leedaehun93/Springboot-JPA-Blog/assets/141594918/ffc895b5-5f0f-4dc0-8a5b-27f7551fc835)
![슬라이드2](https://github.com/Leedaehun93/Springboot-JPA-Blog/assets/141594918/cdc22a1a-0473-4c22-9040-35c87f98dd88)
![슬라이드3](https://github.com/Leedaehun93/Springboot-JPA-Blog/assets/141594918/46b0e752-d01a-4ede-85c0-e57ea445819d)
<br/>

## 📦 패키지 구조

<details>
<summary>자세히 보기</summary>
<pre>
   ToyBlog
   ├─ build.gradle                       # 프로젝트의 의존성과 빌드 구성을 정의
   ├─ src
   │  ├─ main
   │  │  ├─ java
   │  │  │  └─ com
   │  │  │     └─ example
   │  │  │        └─ toyblog          # 애플리케이션의 주요 Java 소스 코드
   │  │  │           ├─ config        # 스프링 설정 클래스들
   │  │  │           ├─ controller    # 컨트롤러 클래스들, API와 사용자 요청 처리
   │  │  │           ├─ dto           # 데이터 전송 객체들
   │  │  │           ├─ handler       # 예외 처리 관련 핸들러 클래스들
   │  │  │           ├─ model         # 도메인 모델 클래스들
   │  │  │           ├─ repository    # JPA 레포지토리 인터페이스들
   │  │  │           └─ service       # 서비스 계층 클래스들
   │  │  ├─ resources
   │  │  │  ├─ application.yml        # 애플리케이션 설정 파일
   │  │  │  ├─ logback-spring.xml     # 로깅 설정 파일
   │  │  │  ├─ schema.sql             # 데이터베이스 스키마 파일
   │  │  │  └─ static                 # 정적 리소스들 (HTML, JS, CSS, 이미지 등)
   │  │  └─ webapp
   │  │     └─ WEB-INF
   │  │        └─ views                # JSP 뷰 템플릿들
   └─ gradle
      └─ wrapper
         ├─ gradle-wrapper.jar           # Gradle 래퍼 JAR 파일
         └─ gradle-wrapper.properties    # Gradle 래퍼 설정 파일
</pre>
</details>

## 📄 라이센스

이 프로젝트는 MIT 라이센스를 따릅니다. 자세한 내용은 아래를 참고하세요.

<details>
<summary>자세히 보기</summary>
<pre>
The MIT License

Copyright (c) 2024 Ivan

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
...
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.

</pre>
</details>
