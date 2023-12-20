package training.spring.turkcellspring;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import training.spring.turkcellspring.conditional.MyOptionalBean;

@Component
@RequiredArgsConstructor
public class InitialRun implements CommandLineRunner {
    private final HelloWorld helloWorld;

    @Autowired(required = false)
    private MyOptionalBean myOptionalBean;

    @Override
    public void run(final String... args) throws Exception {
        System.out.println("Hello from commandlinerunner ");
        System.out.println(helloWorld.hello("osman"));
        if (myOptionalBean != null){
            System.out.println("doIt : " + myOptionalBean.doIt());
        }
    }
}
