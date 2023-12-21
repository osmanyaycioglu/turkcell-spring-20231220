package training.spring.turkcellspring.rest;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import training.spring.turkcellspring.configuration.MyAppProperties;

@RestController
@RequestMapping("/api/v1/hello")
public class HelloRestController {
    private MyAppProperties myAppProperties;
    private Counter counter;

    public HelloRestController(final MyAppProperties myAppPropertiesParam,
                               @Qualifier("myAppCounter")  final Counter counterParam) {
        myAppProperties = myAppPropertiesParam;
        counter         = counterParam;
    }

    @Counted("api.v1.hello.hello1")
    @Timed("api.v1.hello.hello1.timed")
    @GetMapping("/hello1")
    public String hello() {
        counter.increment();
        return "Hello";
    }

    @GetMapping("/properties")
    public MyAppProperties prop() {
        return myAppProperties;
    }

}
