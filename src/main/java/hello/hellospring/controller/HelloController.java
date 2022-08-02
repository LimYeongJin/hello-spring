package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    // 웹 어플리케이션에서 /hello 호출하면 아래의 메서드 호출
    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("data", "spring!!");
        // 컨트롤러에서 리턴 값으로 문자를 반환하면 뷰 리졸버(viewResolver)가 화면을 찾아서 처리
        // 스프링 부트 템플릿 엔진은 기본적으로 viewName 매핑 = resources:templates/ + {ViewName} + .html 을 찾음
        return "hello";
    }

    /*
    빌드하고 실행하기
    파워셸(터미널)에서 hello-spring 폴더로 이동 후 ./gradlew.bat build하면
    build 폴더 안의 libs 안에 build되고, build된 jar 파일(plain X)을 실행하면
    HelloSpringApplication이 실행되어 localhost:8080 ~ 접근이 가능하다.
    보통 실무에서는 이 jar파일을 배포
     */

}
