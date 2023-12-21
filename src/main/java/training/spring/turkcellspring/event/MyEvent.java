package training.spring.turkcellspring.event;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@Builder(setterPrefix = "with")
@Getter
@ToString
public class MyEvent extends ApplicationEvent {

    private Object source;
    private Integer index;
    private Integer count;
    private String  destination;
    private String  phone;

    public MyEvent(final Object source,
                   final Integer indexParam,
                   final Integer countParam,
                   final String destinationParam,
                   final String phoneParam) {
        super(source);
        this.source = source;
        index       = indexParam;
        count       = countParam;
        destination = destinationParam;
        phone       = phoneParam;
    }
}
