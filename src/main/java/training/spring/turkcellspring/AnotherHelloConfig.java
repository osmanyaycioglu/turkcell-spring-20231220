package training.spring.turkcellspring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("eng")
@Configuration
public class AnotherHelloConfig {

    @Bean
    public HelloWorld helloWorld(){
        return new HelloWorld("hello");
    }

}
