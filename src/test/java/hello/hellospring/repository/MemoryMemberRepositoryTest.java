package hello.hellospring.repository;

import hello.hellospring.domain.Member;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
/**
    test 패키지부터 아래의 아무 패키지 클릭 후 우클릭하면 원하는 범위에서 테스트 돌릴 수 있음
    클래스에서 테스트 돌리면 아래의 메서드들이 전부 돌아감(단 테스트되는 메서드 순서는 무작위)
    그렇기 때문에 테스트는 서로 순서, 의존 관계 없이 설계가 되어야 함
 */
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    /**
        아래 findAll() 메서드 부분 주석 읽고 올라오삼
        메서드가 테스트 실행이 끝날 때마다 동작을 한다.
     */
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    // 메서드 하나에서만 테스트 돌릴 수 있음
    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        /**
        findById()의 반환 타입이 Optional인데 Optional에서 값을 꺼낼 때는 get()으로 꺼내면 됨
        get()으로 꺼내는 것이 좋은 방법은 아니나 테스트 코드에서는 크게 상관 없음
         */
        Member result = repository.findById(member.getId()).get();

        // 이 방법은 글자를 볼 수가 없음 -> Assertions 사용
        // System.out.println("result = " + (result == member));

        // import org.junit.jupiter.api.Test
        // Assertions.assertEquals(member, result);

        // import org.assertj.core.api.Assertions
        // Assertions.assertThat(member).isEqualTo((result));

        // 위의 Assertions에 포커싱한 다음 Alt + Enter 눌러서 static import 추가하면 간단하게 사용 가능
        // import static org.assertj.core.api.Assertions.*
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        // 위에꺼 복붙한 다음 member1에 포커싱 후 Shift + F6 눌러 Rename하여 붙여 넣은 member1 변수 전부 변경 가능
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    // findAll()까지 만들고 위 class에서 테스트 돌리면 오류남
    // findAll()에서 저장한 member객체가 다른 곳에 영향을 미침
    // 그렇기 때문에 테스트가 끝나고 나면 데이터를 클리어해주어야 함
    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
