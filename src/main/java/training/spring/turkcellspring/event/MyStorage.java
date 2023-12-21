package training.spring.turkcellspring.event;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MyStorage {
    private Map<String, String> stringStringMap = new ConcurrentHashMap<>(1_000,
                                                                          0.9f,
                                                                          100);

    public void add(String str1,
                    String str2) {
        stringStringMap.put(str1,
                            str2);
    }

}
