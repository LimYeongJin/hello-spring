package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
    통합 테스트(DB까지 연결)하기에 필요한 어노테이션 2개
    스프링 컨테이너와 테스트를 함께 실행해야 함 => @SpringBootTest가 해줌
    테스트는 반복을 할 수 있어야 함 => @Transactional => 테스트 끝나면 rollback 해줌
    @Transactional 순서 : 트랜잭션 시작 -> 테스트 시작 -> 테스트 -> 테스트 완료 -> 롤백
    결론적으로 DB에 데이터가 남지 않게 됨
 */
@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    /**
    통합 테스트도 중요하지만
    진짜 좋은 테스트는 단위 테스트를 잘 만드는 것이 더 중요
     */

    // 테스트할 때는 필드 인젝션 기반으로 하는 경우 많음
    // Why? : 편하니까
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        // given : 뭔가가 주어짐
        Member member = new Member();
        member.setName("spring");

        // when : 이걸 실행했을 때
        long saveId = memberService.join(member);

        // then : 결과가 이렇게 나와야 함
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        // then
    }
}