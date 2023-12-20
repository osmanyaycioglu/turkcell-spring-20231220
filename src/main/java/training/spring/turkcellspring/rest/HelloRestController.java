package training.spring.turkcellspring.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import training.spring.turkcellspring.configuration.MyAppProperties;

@RestController
@RequestMapping("/api/v1/hello")
@RequiredArgsConstructor
public class HelloRestController {
    private final MyAppProperties myAppProperties;


    @GetMapping("/hello1")
    public String hello() {
        return "Hello";
    }

    @GetMapping("/properties")
    public MyAppProperties prop() {
        return myAppProperties;
    }

}
