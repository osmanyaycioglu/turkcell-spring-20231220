package training.spring.turkcellspring;

import a.b.c.MyOtherPackageConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import training.spring.turkcellspring.event.ApplicationEventsListener;

//@SpringBootApplication(scanBasePackages = {"training.spring.turkcellspring","a.b.c"})
@SpringBootApplication
@Import({MyOtherPackageConfig.class})
@EnableConfigurationProperties
@EnableAsync
public class TurkcellSpringApplication {

    public static void main(String[] args) {
//        SpringApplication.run(TurkcellSpringApplication.class,
//                              args);

        SpringApplication applicationLoc = new SpringApplication(TurkcellSpringApplication.class);
        applicationLoc.addListeners(new ApplicationEventsListener());
        applicationLoc.run(args);
    }

}
