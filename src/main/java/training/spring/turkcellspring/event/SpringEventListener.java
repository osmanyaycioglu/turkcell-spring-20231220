package training.spring.turkcellspring.event;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class SpringEventListener {

    @EventListener
    public void handle(ContextRefreshedEvent startedEventParam) {
        System.out.println("**00 CONTEXT REFRESHED EVENT");
    }

    @EventListener
    public void handle(ApplicationReadyEvent event) {
        System.out.println("----- APPLICATION READY EVENT ");
    }

    @Order(3)
    @EventListener
    public void handle1(MyEvent event) {
        System.out.println("11-&&&&&&& MY EVENT : " + event.getCount() + " Thread : " + Thread.currentThread()
                                                                                           .getName());
    }

    @Order(2)
    @EventListener
    public void handle2(MyEvent event) {
        System.out.println("12-&&&&&&& MY EVENT : " + event.getCount() + " Thread : " + Thread.currentThread()
                                                                                             .getName());
    }

    @Order(1)
    @EventListener
    public void handle3(MyEvent event) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException eParam) {
            throw new RuntimeException(eParam);
        }
        System.out.println("13-&&&&&&& MY EVENT : " + event.getCount() + " Thread : " + Thread.currentThread()
                                                                                             .getName());
    }

}
