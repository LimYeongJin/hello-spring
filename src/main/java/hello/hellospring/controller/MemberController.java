package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
스프링이 처음에 실행될 때 스프링 컨테이너라는 통이 생기는데 거기에 @Controller가 있으면
 MemberController 객체를 생성해 스프링 컨테이너 안에 넣는다.
스프링 컨테이너에서 빈(Bean)이 관리된다 라고 표현
 */
@Controller
public class MemberController {

    // 이렇게 만들 필요가 없음
    // 여러 개의 인스턴스를 생성할 이유가 없음
    // private final MemberService memberService = new MemberService();

    private final MemberService memberService;
    /**
    스프링 컨테이너가 뜰 때 MemberController 객체가 생성이 되는데 그 때 이 생성자를 호출
    @Autowired가 있으면 스프링 컨테이너가 MemberService를 찾아 주입해줌(의존성 주입)
    단 MemberService가 순수한 자바 클래스기 때문에 MemberService 클래스에 @Service 써줘야 함
     */
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // GET : URL에 직접 치는 방식(주로 조회할 때 사용)
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    // POST : 데이터를 form 같은 곳에 넣어 전달(members/createMemberForm.html 참조)
    // MemberForm 타입을 파라미터로 넣었는데 이 안의 name 변수에 Spring이 form에 있는 name과 대응시킨다.
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " + member.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
