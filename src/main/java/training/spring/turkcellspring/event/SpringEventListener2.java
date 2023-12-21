package training.spring.turkcellspring.event;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class SpringEventListener2 {
    @Order(6)
    @EventListener
    public void handle1(MyEvent event) {
        System.out.println("21-&&&&&&& MY EVENT : " + event.getCount() + " Thread : " + Thread.currentThread()
                                                                                           .getName());
    }
    @Order(5)
    @EventListener
    public void handle2(MyEvent event) {
        System.out.println("22-&&&&&&& MY EVENT : " + event.getCount() + " Thread : " + Thread.currentThread()
                                                                                             .getName());
    }
    @Order(4)
    @EventListener
    public void handle3(MyEvent event) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException eParam) {
            throw new RuntimeException(eParam);
        }
        System.out.println("23-&&&&&&& MY EVENT : " + event.getCount() + " Thread : " + Thread.currentThread()
                                                                                             .getName());
    }

}
