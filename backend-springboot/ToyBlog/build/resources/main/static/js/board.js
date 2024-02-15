/**
 * ======================================
 * FileName : board.js
 * Note :
 * 53강(블로그 프로젝트) - 글쓰기 완료
 * 57강(블로그 프로젝트) - 글 삭제하기
 * 58강(블로그 프로젝트) - 글 수정하기
 * 68강(블로그 프로젝트) - 댓글 작성하기
 * 69강(블로그 프로젝트 ) - 댓글 작성시 Dto 사용해보기
 * ======================================
 */

/**
 * 웹 페이지의 동작을 관리하는 board 객체를 정의
 * init 함수와 save 함수를 포함
 */
let index = {
    /**
     * init 함수 : 버튼에 클릭 이벤트 리스너를 연결한다.
     * 화살표 함수를 사용하여 이벤트 핸들러 내부에서 this가 index 객체를 참조하도록 한다.
     */
    init: function () {
        $("#btn-save").on("click", () => { // 버튼이 클릭되면 this.save 즉, index.save 함수가 호출 된다.
            this.save();
        });
        $("#btn-delete").on("click", () => { // 버튼이 클릭되면 this.deleteById 즉, index.delete 함수가 호출 된다.
            this.deleteById();
        });
        $("#btn-update").on("click", () => { // 버튼이 클릭되면 this.update 즉, index.update 함수가 호출 된다.
            this.update();
        });
        $("#btn-reply-save").on("click", () => { // 버튼이 클릭되면 this.repltSave 즉, index.replySave 함수가 호출 된다.
            this.replySave();
        });
    },
    /**
     * save 함수 : 사용자가 입력한 데이터를 가져와서 JSON 객체로 변환한다. $.ajax를 사용하여 서버에 POST 요청을 보내고 JSON 형식으로 전송된다.
     * 1) .done 성공 처리 로직 : 요청이 성공하면 사용자를 다른 페이지(/)로 리디렉션하고
     * 2) .fail 오류 처리 로직 : 실패할 경우, 오류 메시지를 표시한다.
     */
    save: function () {
        /**
         * 사용자 입력 데이터를 JSON 객체로 만들어 서버에 전송한다.
         */
        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        }
        /**
         * Ajax를 통해 서버에 글쓰기 데이터를 비동기적으로 전송한다.
         */
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
    }, deleteById: function () {
        let id = $("#id").text();

        /**
         * Ajax를 통해 서버에 글삭제 데이터를 비동기적으로 전송한다.
         */
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

    }, update: function () {
        let id = $("#id").val();

        /**
         * 사용자 입력 데이터를 JSON 객체로 만들어 서버에 전송한다.
         */
        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        };
        // console.log(id); // 서버 콘솔에 로그 출력 // 테스트 완료
        // console.log(data); // 서버 콘솔에 로그 출력 // 테스트 완료
        /**
         * Ajax를 통해 서버에 글이 수정된 데이터를 비동기적으로 전송한다.
         */
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
    replySave: function () {
        /**
         * 사용자 입력 데이터를 JSON 객체로 만들어 서버에 전송한다.
         */
        let data = {
            userId : $("#userId").val(),
            boardId : $("#boardId").val(),
            /**
             * TODO: [오류 해결] 문제 : content: $("#reply-content").val()에서 undefined 반환
             *  - 참고 : 68강(블로그 프로젝트) - 댓글 작성하기
             *  - 원인 : HTML 문서(detail.jsp)에서 id가 btn-content 로 작성한 오타 발생.
             *  - 해결 :  <textarea id="reply-content" class="form-control" rows="1"></textarea> 수정 후 사용자 입력 데이터 정상 반환.
             */
            content: $("#reply-content").val()
        };

        /**
         * Ajax를 통해 서버에 글쓰기 데이터를 비동기적으로 전송한다.
         */
        $.ajax({
            type: "POST",
            url: `/api/board/${data.boardId}/reply`, // 댓글을 저장하는 서버의 API 주소
            data: JSON.stringify(data), // 전송할 데이터. JSON 형태로 변환
            contentType: "application/json; charset=utf-8", // 내용 유형 지정
            dataType: "json" // 데이터 유형 지정
        }).done(function (resp) {
            // 요청이 성공하였을 때 실행된다.
            alert("댓글작성이 완료되었습니다."); // 성공 메시지 표시 // 화면 테스트 완료
            location.href = `/board/${data.boardId}`; // 해당 게시글로 페이지 리다이렉션
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