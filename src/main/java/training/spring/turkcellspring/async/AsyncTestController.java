package training.spring.turkcellspring.async;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import training.spring.turkcellspring.event.MyEvent;
import training.spring.turkcellspring.event.MyStorage;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api/v1/async")
@RequiredArgsConstructor
public class AsyncTestController {
    private final MyAsyncBean myAsyncBean;

    @GetMapping("/test1")
    public String test1() {
        StringBuilder builderLoc = new StringBuilder();
        builderLoc.append(myAsyncBean.syncMethod1());
        builderLoc.append(" ^^^^^^^^^^^^^^ ");
        builderLoc.append(myAsyncBean.syncMethod2());
        builderLoc.append(" ^^^^^^^^^^^^^^ ");
        builderLoc.append(myAsyncBean.syncMethod3());

        return "Controller test1 th : " + Thread.currentThread()
                                                .getName() + " ---- " + builderLoc.toString();
    }

    @GetMapping("/test2")
    public String test2() {
        StringBuilder builderLoc = new StringBuilder();

        Future<String> stringFutureLoc1 = myAsyncBean.asyncMethod1();
        Future<String> stringFutureLoc2 = myAsyncBean.asyncMethod2();
        Future<String> stringFutureLoc3 = myAsyncBean.asyncMethod3();

        System.out.println("Test2 method th : " + Thread.currentThread()
                                                        .getName());
        try {
            builderLoc.append(stringFutureLoc1.get());
            builderLoc.append(stringFutureLoc2.get());
            builderLoc.append(stringFutureLoc3.get());
        } catch (Exception eParam) {
        }
        return "Controller test2 th : " + Thread.currentThread()
                                                .getName() + " ---- " + builderLoc.toString();
    }

}
