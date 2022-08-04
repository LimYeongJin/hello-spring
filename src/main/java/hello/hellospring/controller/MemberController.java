package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// 스프링이 처음에 실행될 때 스프링 컨테이너라는 통이 생기는데 거기에 @Controller가 있으면
//  MemberController 객체를 생성해 스프링 컨테이너 안에 넣는다.
// 스프링 컨테이너에서 빈이 관리된다 라고 표현
@Controller
public class MemberController {

    // 이렇게 만들 필요가 없음
    // 여러 개의 인스턴스를 생성할 이유가 없음
    // private final MemberService memberService = new MemberService();

    private final MemberService memberService;

    // 스프링 컨테이너가 뜰 때 MemberController 객체가 생성이 되는데
    // 그 때 이 생성자를 호출
    // @Autowired가 있으면 스프링 컨테이너가 MemberService를 찾아 주입해줌(의존성 주입)
    // 단 MemberService가 순수한 자바 클래스기 때문에 MemberService 클래스에 @Service 써줘야 함
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
