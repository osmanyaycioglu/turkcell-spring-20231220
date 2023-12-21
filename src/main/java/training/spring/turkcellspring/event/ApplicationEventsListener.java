package training.spring.turkcellspring.event;

import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationEventsListener implements ApplicationListener<SpringApplicationEvent> {

    @Override
    public void onApplicationEvent(final SpringApplicationEvent event) {
        System.out.println("______ EVENT : " + event.getClass().getSimpleName());
    }

}
