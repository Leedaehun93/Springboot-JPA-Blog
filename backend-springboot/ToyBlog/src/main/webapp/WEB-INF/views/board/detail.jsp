<!-- FileName : detail.jsp -->
<!-- Note : 56강(블로그 프로젝트) - 글 상세보기 -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- header.jsp, footer.jsp include 상위 경로 다름 --%>

<%-- header include --%>
<%@ include file="../layout/header.jsp" %>

<div class="container">
    <%-- button Start --%>
    <button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
    <button id="btn-update" class="btn btn-warning">수정</button>
    <button id="btn-delete" class="btn btn-danger">삭제</button>
    <br/>
    <br/>
    <%-- button End --%>
    <%-- title Start --%>
    <div class="form-group">
        <h3>${board.title}</h3>
    </div>
    <%-- title End --%>
    <br/>
    <%-- content Start --%>
    <div class="form-group">
        <div>${board.content}</div>
    </div>
    <%-- content End --%>
    <br/>
</div>

<%-- 스크립트 script Start --%>
<!-- 사용자 정의 board 스크립트 -->
<script src="/js/board.js"></script>
<%-- 스크립트 script End --%>

<%-- footer include --%>
<%@ include file="../layout/footer.jsp" %>