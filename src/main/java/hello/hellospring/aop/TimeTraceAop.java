package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/*
모든 메서드에 시간을 측정하는 구문을 추가한다면?
회원 가입에 시간을 측정하는 기능은 핵심 관심 사항이 아님(핵심 관심 사항은 중복 멤버 체크)
시간을 측정하는 로직은 공통 관심 사항
공통 관심 사항과 핵심 비즈니스 로직(핵심 관심 사항)이 섞이면 유지보수 어려움

이럴 때 AOP(Aspect Oriented Programming) 사용

AOP 동작 원리
기존 : helloController가 memberService 호출
AOP 적용
1. helloController가 프록시(가짜) memberService 호출
2. jointPoint.proceed() 동작
3. 실제 memberService 호출
4. 실제 memberService가 프록시 memberRepository 호출
5. joinPoint.proeceed() 동작
6. 실제 memberRepository 호출
*/

// @Aspect 붙어주어야 AOP로 사용 가능
// 컴포넌트(@Component) 등록하여 사용해도 되고
// AOP 같은 경우는 SpringBean에 등록하여 사용하는 것을 선호
@Aspect
@Component
public class TimeTraceAop {

    // @Around로 공통 관심 사항의 적용 범위를 설정해야 함
    // hello.hellospring 패키지 하위에는 다 적용하라는 의미
    @Around("execution(* hello.hellospring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START : " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END : " + joinPoint.toString() + " " + timeMs + "ms");
        }

    }
}
