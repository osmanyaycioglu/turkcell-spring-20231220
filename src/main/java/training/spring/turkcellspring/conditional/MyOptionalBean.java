package training.spring.turkcellspring.conditional;

import org.springframework.stereotype.Component;

@MyCondition(10)
@Component
public class MyOptionalBean {

    public String doIt(){
        return "OK";
    }

}
