package training.spring.turkcellspring;

import a.b.c.MyOtherPackageBean;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import training.spring.turkcellspring.conditional.MyOptionalBean;

@Component
@RequiredArgsConstructor
public class InitialRun implements CommandLineRunner {
    private final HelloWorld         helloWorld;
    private final MyOtherPackageBean myOtherPackageBean;

    @Autowired(required = false)
    private MyOptionalBean myOptionalBean;


    @Override
    public void run(final String... args) throws Exception {
        System.out.println("Hello from commandlinerunner ");
        System.out.println(helloWorld.hello("osman"));
        if (myOptionalBean != null) {
            System.out.println("doIt : " + myOptionalBean.doIt());
        }
        myOtherPackageBean.doIt();
    }

    @PreDestroy
    public void destroy() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException eParam) {
        }
        System.out.println("Initial Run Destroy");
    }

}
