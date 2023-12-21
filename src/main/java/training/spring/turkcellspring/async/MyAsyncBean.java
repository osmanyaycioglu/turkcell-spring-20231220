package training.spring.turkcellspring.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Component
public class MyAsyncBean {

    private int count = 0;

    public synchronized int inc() {
        return count++;
    }

    public String syncMethod1() {
        try {
            Thread.sleep(2_000);
        } catch (InterruptedException eParam) {
        }

        int incLoc = inc();
        return "Sync method1 " + incLoc + " th : " + Thread.currentThread()
                                                          .getName();
    }

    public String syncMethod2() {
        try {
            Thread.sleep(2_000);
        } catch (InterruptedException eParam) {
        }
        int incLoc = inc();
        return "Sync method2 " + incLoc + " th : " + Thread.currentThread()
                                                           .getName();
    }

    public String syncMethod3() {
        try {
            Thread.sleep(2_000);
        } catch (InterruptedException eParam) {
        }
        int incLoc = inc();
        return "Sync method3 " + incLoc + " th : " + Thread.currentThread()
                                                           .getName();
    }

    @Async("defaultThreadPool")
    public Future<String> asyncMethod1() {
        System.out.println("Running async with th : " + Thread.currentThread()
                                                              .getName());
        try {
            Thread.sleep(2_000);
        } catch (InterruptedException eParam) {
        }
        int incLoc = inc();
        System.out.println("Running async about to complete th : " + Thread.currentThread()
                                                                           .getName());
        return CompletableFuture.completedFuture("ASync1 method " + incLoc + " th : " + Thread.currentThread());

    }

    @Async("defaultThreadPool")
    public Future<String> asyncMethod2() {
        System.out.println("Running async with th : " + Thread.currentThread()
                                                              .getName());
        try {
            Thread.sleep(2_000);
        } catch (InterruptedException eParam) {
        }
        int incLoc = inc();
        System.out.println("Running async about to complete th : " + Thread.currentThread()
                                                                           .getName());
        return CompletableFuture.completedFuture("ASync2 method " + incLoc + " th : " + Thread.currentThread());

    }

    @Async("defaultThreadPool")
    public Future<String> asyncMethod3() {
        System.out.println("Running async with th : " + Thread.currentThread()
                                                              .getName());
        try {
            Thread.sleep(2_000);
        } catch (InterruptedException eParam) {
        }
        int incLoc = inc();
        System.out.println("Running async about to complete th : " + Thread.currentThread()
                                                                           .getName());
        return CompletableFuture.completedFuture("ASync3 method " + incLoc + " th : " + Thread.currentThread());

    }

}
