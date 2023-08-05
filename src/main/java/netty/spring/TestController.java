package netty.spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String umeTest(){
        return "it is umeTest!";
    }

    @GetMapping("/pro")
    public String umePro(){
        return "it is umePro!";
    }
}
