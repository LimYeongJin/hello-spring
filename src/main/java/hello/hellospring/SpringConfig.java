package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 자바 코드로 스프링 빈 등록
// @Configuration 클래스에 @Bean 메서드(생성자) 등록
@Configuration
public class SpringConfig {

    // 스프링 데이터 JPA 관련
    private final MemberRepository memberRepository;

    // 생성자 하나인 경우는 @Autowired 생략 가능
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }
    /*
    // 스프링 데이터 JPA 사용해야 해서 주석 처리

    // JPA 관련
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }
     */

    /*
    // JPA 사용해야 해서 주석 처리
    private DataSource dataSource;
    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
     */

    /*
    // 스프링 데이터 JPA 사용해야 해서 주석 처리
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
     */

    /*
    // 스프링 데이터 JPA 사용해야 해서 주석 처리
    @Bean
    public MemberRepository memberRepository() {
        // return new MemoryMemberRepository();

        // 스프링이 좋은 이유 : 다형성 활용 좋음
        // 인터페이스 구현체를 편리하게 바꿔 끼기 할 수 있다.
        // 개방 폐쇄 원칙(OCP, Open-Closed Principle) : 확장에는 열려있고, 수정, 변경에는 닫혀있음

        //return new JdbcMemberRepository(dataSource);

        // JdbcTemplate 관련 리포지토리 활용
        리포지토리 만들고 테스트하고 싶을 때, SpringConfig로 관리하면 여기만 바꿔주면 빠르게 테스트 가능

        // return new JdbcTemplateMemberRepository(dataSource);

        // JPA 사용

        return new JpaMemberRepository(em);
    }
    */

    // AOP는 컴포넌트 스캔으로 등록해서 사용해도 되고
    // Spring Bean에 직접 등록하여 사용해도 됨(후자를 선호)
    /*
    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }
     */
}
