package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

// Shift + F10은 이전에 실행했던 것을 다시 실행시켜줌
class MemberServiceTest {

    MemberService memberService;

    // 클리어해주기 위해 가져옴
    // 가져올 때 MemoryMemberRepository() 하면 MemberService 인스턴스 안에 있는 new MemoryMemberRepository() 인스턴스도 있고 서로 다름
    // 물론 지금은 MemoryMemberRepository 클래스 안의 store 변수가 static이라 문제는 되지 않지만
    // 만약 static이 없다면? 문제가 될 수도 있음, 또한 다른 리포지토리가 아닌 같은 리포지토리로 테스트 하는 것이 좋음
    // MemberService 클래스에서 작업해야 함
    //MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    MemoryMemberRepository memberRepository;

    // 각 메서드들 동작하기 전에 넣어준다.
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }


    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    // 테스트는 과감하게 한글로 바꿔도 됨
    // 게다가 테스트 코드는 빌드될 때 실제 코드에 포함되지 않음
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

    // 테스트는 정상 실행 사례를 체크하는 것도 중요하지만 예외를 검증하는 것이 더 중요
    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        // 이런 거에 try-catch() 넣는 게 좀 애매함
        /*
        try {
            memberService.join(member2);
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
         */
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


        // then
    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}