package training.spring.turkcellspring.actuator;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;
import training.spring.turkcellspring.configuration.MyAppProperties;

import java.lang.reflect.Field;

@Endpoint(id = "app-config")
@Component
@RequiredArgsConstructor
public class MyAppConfigEndpoint {
    private final MyAppProperties myAppProperties;

    @ReadOperation
    public MyAppProperties getMyAppProperties() {
        return myAppProperties;
    }

    @ReadOperation
    public String getMyAppPropertiesField(@Selector String field) {
        Field[] declaredFieldsLoc = myAppProperties.getClass()
                                                   .getDeclaredFields();
        for (Field declaredFieldLoc : declaredFieldsLoc) {
            if (declaredFieldLoc.getName()
                                .equals(field)) {
                try {
                    declaredFieldLoc.setAccessible(true);
                    return "" + declaredFieldLoc.get(myAppProperties);
                } catch (Exception eParam) {
                    return "null";
                }
            }
        }
        return "null";
    }

    @WriteOperation
    public String setMyAppPropertiesField(String field,
                                          String value) {
        Field[] declaredFieldsLoc = myAppProperties.getClass()
                                                   .getDeclaredFields();
        for (Field declaredFieldLoc : declaredFieldsLoc) {
            if (declaredFieldLoc.getName()
                                .equals(field)) {
                try {
                    declaredFieldLoc.setAccessible(true);
                    declaredFieldLoc.set(myAppProperties,
                                         value);
                    return "OK";
                } catch (Exception eParam) {
                    return "NOT OK";
                }
            }
        }
        return "NOT OK";
    }

}
