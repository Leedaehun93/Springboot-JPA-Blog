<%-- joinForm.jsp --%>
<%-- 60강(블로그 프로젝트) - 회원수정 1 --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- header include --%>
<%@ include file="../layout/header.jsp" %>

<%-- Bootstrap-4 ed Form Start --%>
<div class="container">
    <form>
        <input type="hidden" id="id" value="${principal.user.id}"/>
        <%-- Username Start --%>
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" value="${principal.user.username}" class="form-control" placeholder="Enter username" id="username" readonly>
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
            <input type="email" value="${principal.user.email}" class="form-control" placeholder="Enter email" id="email">
        </div>
        <%-- Email End --%>
    </form>
    <%-- 회원수정완료 button Start --%>
    <button id="btn-update" class="btn btn-primary">회원수정완료</button>
    <%-- 회원수정완료 button End --%>
</div>
<%-- Bootstrap-4 ed Form End --%>

<%-- 스크립트 script Start --%>
<!-- 사용자 정의 user 스크립트 -->
<script src="/js/user.js"></script>
<%-- 스크립트 script End --%>

<%-- footer include --%>
<%@ include file="../layout/footer.jsp" %>