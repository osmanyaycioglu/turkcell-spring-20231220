package training.spring.turkcellspring;

public class HelloWorld {
    private String prefix;

    public HelloWorld(final String prefixParam) {
        prefix = prefixParam;
    }

    public String hello(String name) {
        return prefix + " " + name;
    }
}
