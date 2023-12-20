package training.spring.turkcellspring.actuator;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import training.spring.turkcellspring.configuration.MyAppProperties;

@Component
@RequiredArgsConstructor
public class MyHealthIndicator implements HealthIndicator {
    private final MyAppProperties myAppProperties;


    @Override
    public Health health() {
        return Health.down()
                     .withDetail("property",
                                 myAppProperties)
                     .build();
    }

}
