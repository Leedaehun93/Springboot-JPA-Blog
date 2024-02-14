<%-- index.jsp --%>
<%--
  Created by IntelliJ IDEA.
  User: dhlee
  Date: 2024-01-04
  Time: 오후 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- header include --%>
<%@ include file="../layout/header.jsp" %>

<%-- Bootstrap-4 ed Form Start --%>
<div class="container">
    <form>
        <%-- Username Start --%>
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" class="form-control" placeholder="Enter username" id="username">
        </div>
        <%-- Username End --%>

        <%-- Password Start --%>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" placeholder="Enter password" id="password">
        </div>
        <%-- Password End --%>

        <%-- Email Start --%>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" class="form-control" placeholder="Enter email" id="email">
        </div>
        <%-- Email End --%>
    </form>
    <%-- 회원가입완료 button Start --%>
    <button id="btn-save" class="btn btn-primary">회원가입완료</button>
    <%-- 회원가입완료 button End --%>
</div>
<%-- Bootstrap-4 ed Form End --%>

<%-- 스크립트 script Start --%>
<%-- TODO: [오류 해결] 문제 : "회원가입버튼" 클릭 후 동작하지 않음(Include omitted for the script reference)"
       - 참고 : 50강(블로그 프로젝트) - 비밀번호 해시 후 회원가입하기
       - 원인 : user.js 스크립트 로드 문제 발생.
       - 해결 : 버튼 클릭 이벤트를 처리하는 로직을 포함하고 있으나 비활성화 상태(주석 처리) 로 인한 이슈 발생으로 주석 해제, 충동으로 인해 사용자 정의 스크립트 위치를 footer.jsp -> joinForm.jsp 위치로 변경함
              나머지 스크립트는 header.jsp <head> 태그 하단에 위치(스크립트 로딩 순서 중요)
--%>
<!-- 사용자 정의 user 스크립트 -->
<script src="/js/user.js"></script>
<%-- 스크립트 script End --%>

<%-- footer include --%>
<%@ include file="../layout/footer.jsp" %>