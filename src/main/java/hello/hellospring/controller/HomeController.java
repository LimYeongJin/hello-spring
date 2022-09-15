package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    /**
    localhost:8080 으로 들어오면 이게 호출
    url에 오는 것이 아무것도 없으면 정적 컨텐츠 찾아야 하지 않냐? => 정적컨텐츠 이미지.PNG 참조
     */
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
