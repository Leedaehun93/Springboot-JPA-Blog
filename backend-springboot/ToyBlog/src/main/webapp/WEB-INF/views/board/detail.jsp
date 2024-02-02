<!-- FileName : detail.jsp -->
<!-- Note : 56강(블로그 프로젝트) - 글 상세보기 -->
<!-- Note : 57강(블로그 프로젝트) - 글 삭제하기 -->
<!-- Note : 58강(블로그 프로젝트) - 글 수정하기 -->
<!-- Note : 66강(블로그 프로젝트) - 댓글 디자인하기 -->
<!-- Note : 67강(블로그 프로젝트) - 댓글 목록 뿌리기 -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- header.jsp, footer.jsp include 상위 경로 다름 --%>

<%-- header include --%>
<%@ include file="../layout/header.jsp" %>

<div class="container">
    <%-- button Start --%>
    <button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
    <c:if test="${board.user.id == principal.user.id}">
        <a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
        <button id="btn-delete" class="btn btn-danger">삭제</button>
    </c:if>
    <%-- button End --%>
    <br/> <br/>
    <%-- 게시자 정보 Start --%>
    <div>
        글 번호 :<span id="id"><i>${board.id} </i></span>
        작성자 :<span><i>${board.user.username} </i></span>
    </div>
    <br/>
    <%-- 게시자 정보 End --%>
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

    <%-- 댓글 Start --%>
    <div>
        <div class="card">
            <div class="card-body"><textarea class="from-control" rows="1"></textarea></div>
            <div class="card-footer">
                <button class="btn btn-primary">등록</button>
            </div>
        </div>
    </div>
    <br/>
    <%-- 댓글 리스트 Start --%>
    <div>
        <div class="card">
            <div class="card-header">댓글 리스트</div>
            <%--  HTML  파일에서 개발자가 자신이 생성한 ID에 대해 특별한 네이밍 규칙으로는 ID에는 '--' (하이픈 두 개)를 사용하여 가독성을 높이는 방법이 있다. --%>
            <ul id="reply--box" class="list-group">
                <c:forEach var="reply" items="${board.replies}">
                    <li id="reply--1" class="list-group-item d-flex justify-content-between">
                        <div>댓글 내용입니다!!</div>
                        <div class=" d-flex">
                            <div class="font-italic">작성자 : ${reply.user.username} &nbsp;</div>
                            <button class="badge">삭제</button>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <%-- 댓글 리스트 End --%>
</div>
<%-- 댓글 End --%>

<%-- 스크립트 script Start --%>
<!-- 사용자 정의 board 스크립트 -->
<script src="/js/board.js"></script>
<%-- 스크립트 script End --%>

<%-- footer include --%>
<%@ include file="../layout/footer.jsp" %>