package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// 클래스명이나 내부에 포커싱 후 Ctrl + Shift + T 누르고
// Create New Test... 클릭하고 설정하면 자동으로 테스트 소스 및 껍데기(메서드) 생성
// 스프링이 올라올 때 스프링 컨테이너가 @Service를 찾아 MemberService 등록(컴포넌트 스캔)
// @Service (자바 코드로 스프링 빈 등록하는 방식 사용하여 주석 처리)
public class MemberService {

    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;
    // 외부에서 주입할 수 있게 변경 MemberServiceTest.java 참조
    // MemberService의 입장에서 보면 직접 new ~ 하지 않고 외부에서 memberRepository를 넣어줌
    // Dependency Injection (DI, 의존성 주입)
    // 스프링 컨테이너에 있는 MemoryMemberRepotiory를 찾아서 주입

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /**
     * 회원 가입
     */
    public long join(Member member) {
        // 같은 이름이 있는 중복 회원X
        // memberRepository.findByName(member.getName()); 작성 후 Ctrl + Alt + V
        // 누르면 앞에 Optional<Member> byName = 이렇게 자동 완성되어서 변수명(또는 다른 바꿀 거) 바꿔주면 됨
        /*
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
         */

        // Optional을 바로 반환하는 게 좋지는 않음
        // 권장 방법
        // 또한 아래의 방법은 메서드로 뽑는 게 좋음
        // 블록 지정 후 Shift + Ctrl + Alt + T -> Extract Method
        validateDuplicateMember(member); // 중복 회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
