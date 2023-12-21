package training.spring.turkcellspring.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
public class FireEventController {
    private final ApplicationEventPublisher eventPublisher;
    private final MyStorage myStorage;

    private AtomicInteger count = new AtomicInteger();
    private List<String> stringList = new Vector<>();
    private List<String> stringList2 = Collections.synchronizedList(new ArrayList<>());
    private Map<String,String> stringStringMap = new ConcurrentHashMap<>();

    @GetMapping("/fire")
    public void fire() {
        stringList.add("abc");
        myStorage.add("osman", "xyz" + count.incrementAndGet());
        eventPublisher.publishEvent(MyEvent.builder()
                                           .withSource(this)
                                           .withDestination("xyz")
                                           .withCount(count.incrementAndGet())
                                           .withIndex(1)
                                           .withPhone("7284876234")
                                           .build());
    }

}
