public class HelloGreeter implements GreetingHandler {

    @Override
    public String greet(String name) {
        return "Hello, " + name + "!";
    }
}
