package training.spring.turkcellspring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("tr")
@Configuration
public class HelloConfig {

    @Bean
    public HelloWorld helloWorld(){
        return new HelloWorld("selam");
    }

}
