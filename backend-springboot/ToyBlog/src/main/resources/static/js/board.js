/**
 * ======================================
 * FileName : board.js
 * Note :
 * 53강(블로그 프로젝트) - 글쓰기 완료
 * 57강(블로그 프로젝트) - 글 삭제하기
 * 58강(블로그 프로젝트) - 글 수정하기
 * 68강(블로그 프로젝트) - 댓글 작성하기
 * 69강(블로그 프로젝트 ) - 댓글 작성시 Dto 사용해보기
 * 73강(블로그 프로젝트) - 댓글 삭제 마지막 강
 * ======================================
 */

/**
 * 웹 페이지의 동작을 관리하는 board 객체를 정의한다.
 * init 함수와 여러 이벤트 처리 함수들을 포함한다.
 */
let index = {
    /**
     * init 함수: 웹 페이지에 이벤트 리스너를 설정한다.
     * 화살표 함수를 사용하여 이벤트 핸들러 내부에서 this가 index 객체를 참조하도록 한다.
     */
    init: function () {
        $("#btn-save").on("click", () => {
            this.save();
        });
        $("#btn-delete").on("click", () => {
            this.deleteById();
        });
        $("#btn-update").on("click", () => {
            this.update();
        });
        $("#btn-reply-save").on("click", () => {
            this.replySave();
        });
    },
    /**
     * save 함수: 사용자 입력 데이터를 서버에 POST 요청으로 보낸다.
     * 성공 시 성공 메시지와 함께 홈페이지로 리디렉션, 실패 시 오류 메시지를 표시한다.
     */
    save: function () {
        // 사용자 입력 데이터를 JSON 객체로 만들어 서버에 전송한다.
        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        }
        // Ajax를 통해 서버에 글쓰기 데이터를 비동기적으로 전송한다.
        $.ajax({
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (resp) {
            // 요청이 성공하였을 때 실행된다.
            alert("글쓰기가 완료되었습니다."); // 화면 테스트 완료
            location.href = "/";
        }).fail(function (error) {
            // 요청이 실패하였을 때 실행된다.
            alert(JSON.stringify(error));
        });

        /**
         * deleteById 함수: 특정 ID를 가진 게시글을 삭제하기 위해 서버에 DELETE 요청을 보낸다.
         * 성공 시 성공 메시지와 함께 홈페이지로 리디렉션, 실패 시 오류 메시지를 표시한다.
         */
    }, deleteById: function () {
        let id = $("#id").text();
        // Ajax를 통해 서버에 글삭제 데이터를 비동기적으로 전송한다.
        $.ajax({
            type: "DELETE",
            url: "/api/board/" + id,
            dataType: "json"
        }).done(function (resp) {
            // 요청이 성공하였을 때 실행된다.
            alert("삭제가 완료되었습니다."); // 화면 테스트 완료
            location.href = "/";
        }).fail(function (error) {
            // 요청이 실패하였을 때 실행된다.
            alert(JSON.stringify(error));

        });

        /**
         * update 함수: 사용자가 입력한 데이터로 게시글을 수정하기 위해 서버에 PUT 요청을 보낸다.
         * 성공 시 성공 메시지와 함께 홈페이지로 리디렉션, 실패 시 오류 메시지를 표시한다.
         */
    }, update: function () {
        let id = $("#id").val();

        // 사용자 입력 데이터를 JSON 객체로 만들어 서버에 전송한다.
        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        };
        // console.log(id); // 서버 콘솔에 로그 출력 // 테스트 완료
        // console.log(data); // 서버 콘솔에 로그 출력 // 테스트 완료
        // Ajax를 통해 서버에 글이 수정된 데이터를 비동기적으로 전송한다.
        $.ajax({
            type: "PUT",
            url: "/api/board/" + id,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (resp) {
            // 요청이 성공하였을 때 실행된다.
            alert("글 수정이 완료되었습니다."); // 화면 테스트 완료
            location.href = "/";
        }).fail(function (error) {
            // 요청이 실패하였을 때 실행된다.
            alert(JSON.stringify(error));
        });
    },

    /**
     * replySave 함수: 사용자가 입력한 데이터로 댓글을 작성하기 위해 서버에 POST 요청을 보낸다.
     * 성공 시 성공 메시지와 함께 게시글 페이지로 리디렉션, 실패 시 오류 메시지를 표시한다.
     */
    replySave: function () {
        // 사용자 입력 데이터를 JSON 객체로 만들어 서버에 전송한다.
        let data = {
            userId: $("#userId").val(),
            boardId: $("#boardId").val(),
            /**
             * TODO: [오류 해결] 문제 : content: $("#reply-content").val()에서 undefined 반환
             *  - 참고 : 68강(블로그 프로젝트) - 댓글 작성하기
             *  - 원인 : HTML 문서(detail.jsp)에서 id가 btn-content 로 작성한 오타 발생.
             *  - 해결 :  <textarea id="reply-content" class="form-control" rows="1"></textarea> 수정 후 사용자 입력 데이터 정상 반환.
             */
            content: $("#reply-content").val()
        };
        // Ajax를 통해 서버에 댓글쓰기 데이터를 비동기적으로 전송한다.
        $.ajax({
            type: "POST",
            url: `/api/board/${data.boardId}/reply`, // 댓글을 저장하는 서버의 API 주소
            data: JSON.stringify(data), // 전송할 데이터. JSON 형태로 변환
            contentType: "application/json; charset=utf-8", // 내용 유형 지정
            dataType: "json" // 응답 데이터 유형 지정
        }).done(function (resp) {
            // 요청이 성공하였을 때 실행된다.
            alert("댓글작성이 완료되었습니다."); // 성공 메시지 표시 // 화면 테스트 완료
            location.href = `/board/${data.boardId}`; // 해당 게시글로 페이지 리다이렉션
        }).fail(function (error) {
            // 요청이 실패하였을 때 실행된다.
            alert(JSON.stringify(error)); // 오류 메시지 표시
        });
    },

    /**
     * replyDelete 함수: 특정 게시글의 특정 댓글을 삭제하기 위해 서버에 DELETE 요청을 보낸다.
     * 성공 시 성공 메시지와 함께 게시글 페이지로 리디렉션, 실패 시 오류 메시지를 표시한다.
     *
     * @param boardId 댓글이 속한 게시글의 ID.
     * @param replyId 삭제할 댓글의 ID.
     */
    replyDelete: function (boardId, replyId) {
        // Ajax를 통해 서버에 댓글삭제 데이터를 비동기적으로 전송한다.
        $.ajax({
            type: "DELETE",
            url: `/api/board/${boardId}/reply/${replyId}`,  // 댓글 삭제를 위한 서버의 API 주소
            dataType: "json" // 응답 데이터 유형 지정
        }).done(function (resp) {
            // 요청이 성공하였을 때 실행된다.
            alert("댓글삭제 성공"); // 성공 메시지 표시 // 화면 테스트 완료
            location.href = `/board/${boardId}`; // 해당 게시글로 페이지 리다이렉션
        }).fail(function (error) {
            // 요청이 실패하였을 때 실행된다.
            alert(JSON.stringify(error)); // 오류 메시지 표시
        });
    },

} // end of index

/**
 * init 함수는 페이지가 로드되었을 때 호출된다.
 * 스크립트 실행 시 init 함수를 호출하여 이벤트 리스너를 설정한다.
 */
index.init();