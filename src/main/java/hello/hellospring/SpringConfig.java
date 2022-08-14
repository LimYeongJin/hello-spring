package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

// 자바 코드로 스프링 빈 등록
// @Configuration 클래스에 @Bean 메서드(생성자) 등록
@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        // return new MemoryMemberRepository();
        // 스프링이 좋은 이유 : 다형성 활용 좋음
        // 인터페이스 구현체를 편리하게 바꿔 끼기 할 수 있다.
        // 개방 폐쇄 원칙(OCP, Open-Closed Principle) : 확장에는 열려있고, 수정, 변경에는 닫혀있음
        //return new JdbcMemberRepository(dataSource);
        // JdbcTemplate 관련 리포지토리 활용
        // 리포지토리 만들고 테스트하고 싶을 때, SpringConfig로 관리하면 여기만 바꿔주면 빠르게 테스트 가능
        return new JdbcTemplateMemberRepository(dataSource);
    }

}
