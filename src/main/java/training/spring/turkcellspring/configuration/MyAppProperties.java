package training.spring.turkcellspring.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "my.app")
@Data
public class MyAppProperties {
    private String                  appVersion;
    private String                  appTest;
    private String                  defaultLanguage;
    private long                    responseTimeout;
    private Department              department;
    private List<String>            defaultNames;
    private List<Department>        departments;
    private Map<String, Department> departmentMap;
}
