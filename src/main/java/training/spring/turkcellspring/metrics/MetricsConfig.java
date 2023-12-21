package training.spring.turkcellspring.metrics;

import io.micrometer.core.aop.CountedAspect;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsConfig {

    @Bean
    public CountedAspect countedAspect(MeterRegistry meterRegistryParam) {
        return new CountedAspect(meterRegistryParam);
    }

    @Bean
    public TimedAspect timedAspect(MeterRegistry meterRegistryParam) {
        return new TimedAspect(meterRegistryParam);
    }

    @Bean
    public Counter myAppCounter(MeterRegistry meterRegistryParam) {
        return Counter.builder("api.my.counter")
               .tag("tag1",
                    "deneme")
               .register(meterRegistryParam);
    }
}
