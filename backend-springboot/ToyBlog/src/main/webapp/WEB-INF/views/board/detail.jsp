<!-- FileName : detail.jsp -->
<!-- Note : 56강(블로그 프로젝트) - 글 상세보기 -->
<!-- Note : 57강(블로그 프로젝트) - 글 삭제하기 -->
<!-- Note : 58강(블로그 프로젝트) - 글 수정하기 -->
<!-- Note : 66강(블로그 프로젝트) - 댓글 디자인하기 -->
<!-- Note : 67강(블로그 프로젝트) - 댓글 목록 뿌리기 -->
<!-- Note : 68강(블로그 프로젝트) - 댓글 작성하기 -->
<!-- Note : 69강(블로그 프로젝트 ) - 댓글 작성시 Dto 사용해보기 -->
<!-- Note : 73강(블로그 프로젝트) - 댓글 삭제 마지막 강 -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- header.jsp, footer.jsp include 상위 경로 다름 --%>

<%-- header include --%>
<%@ include file="../layout/header.jsp" %>

<div class="container">
    <%-- button Start --%>
    <button class="btn btn-secondary" onClick="history.back()">돌아가기</button>
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
            <form>
                <!-- 현재 로그인한 사용자의 ID를 숨겨진 필드로 저장 -->
                <input type="hidden" id="userId" value="${principal.user.id}"/>
                <!-- 현재 보고 있는 게시글의 ID를 숨겨진 필드로 저장 -->
                <input type="hidden" id="boardId" value="${board.id}"/>
                <div class="card-body">
                    <!-- 댓글 입력을 위한 텍스트 영역 -->
                    <textarea id="reply-content" class="form-control" rows="1"></textarea>
                </div>
                <div class="card-footer">
                    <!-- 댓글 등록 버튼 -->
                    <button type="button" id="btn-reply-save" class="btn btn-primary">등록</button>
                </div>
            </form>
        </div>
    </div>
    <br/>
    <%-- 댓글 리스트 Start --%>
    <div>
        <div class="card">
            <div class="card-header">댓글 리스트</div>
            <ul id="reply-box" class="list-group">
                <!-- 댓글을 반복하여 리스트로 표시. 서버로부터 전달받은 댓글 목록(board.replys)을 순회한다. -->
                <c:forEach var="reply" items="${board.replys}">
                    <!-- 각 댓글에 고유 ID(reply.id)를 할당하여 DOM에서 쉽게 식별할 수 있게 함. -->
                    <li id="reply-${reply.id}" class="list-group-item d-flex justify-content-between">
                        <!-- 댓글 내용 테스트를 위한 하드 코딩 -->
                            <%--  <div>댓글 내용입니다!!</div> --%>
                            <%-- 서버로부터 받은 각 댓글의 내용을 웹 페이지에 표시 --%>
                        <div>${reply.content}</div>
                        <div class="d-flex">
                            <!-- 작성자 정보 표시 -->
                            <div class="font-italic">작성자 : ${reply.user.username} &nbsp;&nbsp;</div>
                            <!-- 댓글 삭제 버튼 -->
                            <!-- 댓글 삭제 버튼. 현재 로그인한 사용자가 댓글 작성자일 경우에만 표시됨 -->
                            <c:if test="${reply.user.id eq principal.user.id}">
                                <button onClick="index.replyDelete(${board.id}, ${reply.id})" class="badge">삭제</button>
                            </c:if>
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