import com.example.toyblog.model.Reply;
import org.junit.jupiter.api.Test;

/**
 * ======================================
 * FileName : ReplyRepository
 * Note :
 * 70강(블로그 프로젝트) - 댓글 작성시 네이티브 쿼리 사용해보기
 * - Reply 객체의 toString 메서드 기능을 테스트한다.
 * ======================================
 */
public class ReplyObjectTest {

    /**
     * Reply 객체의 toString 메서드를 테스트하는 유닛 테스트.
     * Reply 객체를 생성하고, toString 메서드가 객체의 상태를 올바르게 문자열로 변환하는지 검증한다.
     */
    @Test
    public void 투스트링테스트() {
        // Reply 객체 생성
        Reply reply = Reply.builder()
                .id(1)
                .user(null)
                .board(null)
                .content("안녕")
                .build();

        // toString 메서드 호출하여 객체 상태 출력
        System.out.println(reply); // 오브젝트 출력시에 toString()이 자동 호출됨
    }

} // end of class