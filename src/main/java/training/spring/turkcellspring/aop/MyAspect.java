package training.spring.turkcellspring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {


    @Before("execution(* training.spring.turkcellspring.rest.HelloRestController.*())")
    public void beforeMethod() {
        System.out.println("Before running");
    }

    @After("execution(* training.spring.turkcellspring.rest.HelloRestController.*())")
    public void afterMethod() {
        System.out.println("After running");
    }

    @Pointcut("execution(* training.spring.turkcellspring.rest.HelloRestController.*(String)) && args(name)")
    public void intercept(String name) {
    }

    @Before("intercept(name)")
    public void beforeMethod2(String name) {
        System.out.println("Before2 running : " + name);
    }

    @After("intercept(name)")
    public void afterMethod2(JoinPoint joinPointParam,
                             String name) {
        System.out.println("After2 running : " + name + " joinPoint:" + joinPointParam);
    }

    @AfterReturning(value = "intercept(name)", returning = "retVal")
    public void afterReturningMethod(String name,
                                     String retVal) {
        System.out.println("After Returning running : " + name + " returned : " + retVal);
    }

    @AfterThrowing(value = "intercept(name)", throwing = "exp")
    public void afterReturningMethod(String name,
                                     Exception exp) {
        System.out.println("After Returning running : " + name);
    }

    @Around("execution(* training.spring.turkcellspring.rest.HelloRestController.*(String))")
    public Object aroundMethod(ProceedingJoinPoint joinPointParam) {
        try {
            System.out.println("Around Running : " + joinPointParam.toLongString());
            Object[] argsLoc = joinPointParam.getArgs();
            if (argsLoc[0] instanceof String) {
                String stringLoc = (String) argsLoc[0];
                if (stringLoc.equals("osman")) {
                    return "Sana geciş yok";
                } else if (stringLoc.equals("ali")) {
                    Object[] objectsLoc = new Object[1];
                    objectsLoc[0] = "deneme";
                    return joinPointParam.proceed(objectsLoc);
                }
            }
            String proceedLoc = (String) joinPointParam.proceed();
            return proceedLoc + " intercepted";
        } catch (Throwable eParam) {
            return null;
        }
    }

    @Around("@annotation(logMe)")
    public Object method(ProceedingJoinPoint joinPointParam,
                         LogMe logMe) throws Throwable {
        try {
            System.out.println(logMe.value() + " Before Log");
            long   delta      = System.nanoTime();
            Object proceedLoc = joinPointParam.proceed();
            System.out.println(logMe.value() + " After Log delta : " + (System.nanoTime() - delta));
            return proceedLoc;
        } catch (Throwable eParam) {
            throw eParam;
        }

    }

}
