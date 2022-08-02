package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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


    // 템플릿 엔진 방식
    // 파라미터 정보는 Ctrl + P 단축키로 확인 가능
    // RequestParam은 url을 칠 때 파라미터로 넣어주어야 하는 값, required가 true(default)면 필수 false면 선택
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    // API 방식
    // ResponseBody : http는 header부와 body부로 나눠져있는데 body부에 반환되는 데이터를 직접 넣어주겠다.
    // viewResolver 대신에 HttpMessageConverter가 동작
    // 기본 문자 : StringConverter, StringHttpMessageConverter 동작
    // 기본 객체 : JsonConverter, MappingJackson2HttpMessageConverter 동작
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // name이 spring일 경우 "hello spring" 반환
    }

    // API 방식
    // 코드 어느 정도 작성하다가 Ctrl + Shift + Enter 단축키로 자동 완성 가능
    // json 방식으로 반환 값 보여줌
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    // Getter & Setter 메서드 자동 생성하는 단축키는 Alt + Insert 눌러서 확인
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
