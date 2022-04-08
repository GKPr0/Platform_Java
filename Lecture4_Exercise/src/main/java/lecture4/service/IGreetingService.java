package lecture4.service;

public interface IGreetingService {

    default String greet(String name)
    {
        return "Hi %s".formatted(name);
    }
}
