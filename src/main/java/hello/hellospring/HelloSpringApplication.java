package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {

	/**
	스프링 빈을 등록하는 2가지 방법
	(참고 : 스프링은 스프링 컨테이너에 스프링 빈을 등록할 때 '기본적으로' 싱글톤으로 등록(유일하게 하나만 등록)하여 공유
		같은 스프링 빈이면 모두 같은 인스턴스)
	(참고 : 스프링 컨테이너에 올라가는 객체들에 한해서만 @Autowired가 동작 가능)

	1. 컴포넌트 스캔과 자동 의존관계 설정
	스프링이 올라올 때 @Component 및 @Component를 포함하는 어노테이션을 전부 찾아(@Controller, @Service, @Repository)
	스프링 컨테이너에 객체로 만들어 등록해 놓음(컴포넌트 스캔)
	(Ctrl 누르고 @Controller, @Service, @Repository 클릭하여 정의한 곳으로 이동하면 @Component가 포함된 것 알 수 있음)
	@Autowired는 스프링 컨테이너에 만들어져 있는 객체들로
	MemberController -> MemberService -> MemberRepository 식으로 연결(자동 의존관계 설정)

	컴포넌트 스캔 기준 범위는 특정 설정이 없다면
	main()가 있는 HelloSpringApplication 클래스가 있는 패키지인
	hello.hellospring 패키지와 하위 패키지들
	(Ctrl 누르고 @SpringBootApplication 클릭하여 정의한 곳으로 이동하면 스캔 범위 확인 가능)


	2. 자바 코드로 직접 스프링 빈 등록
	@Controller는 남겨두고 @Service, @Autowired(Controller에 있는 것은 지우면 안 됨), @Repository 지운다.
	그런 다음 SpringConfig 클래스에 자바 코드로 직접 빈 등록
	@Configuration(설정)안에 있는 @Bean 찾아 등록
	여러 가지 클래스를 최소한의 변경으로 테스트할 때 유용


	참고 : 실무에서는 주로 정형화된 컨트롤러, 서비스, 리포지토리 같은 코드는 컴포넌트 스캔 사용
	정형화되지 않거나(예 : 데이터 저장소 아직 안 정해짐), 상황에 따라 구현 클래스를 변경해야 하면 설정을 통해 스프링 빈 등록
	 */
////////////////////////////////////////////////////////////////////////////////////////
	/**
	DI(Dependency Injection, 의존성 주입)에는 3가지 방법이 있다.

	1. 생성자 주입(최근에 가장 권장되는 방식)
	생성자에 @Autowired
	이 도메인 내의 소스들은 생성자 주입 적용
	의존 관계가 실행 중에 동적으로 변하는 경우는 거의 없으므로 생성자 주입 권장

	2. 필드 주입
	@Autowired
	private MemberService memberService;
	같은 방식으로 필드에 주입
	좋은 방법은 아님(필드에 바로 적용하면 바꿀 수 있는 방법이 없기 때문)

	3. setter 주입
	@Autowired
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	같은 방식으로 setter에 주입
	단점 : MemberController 호출했을 때 setter가 public으로 노출되어 있어야 함
	 */

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}
