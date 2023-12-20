package training.spring.turkcellspring.rest.event;

import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SpringEventListener  {

    @EventListener(ContextStartedEvent.class)
    public void handle(ContextStartedEvent startedEventParam){
        System.out.println("Context started : " + startedEventParam);
    }
}
