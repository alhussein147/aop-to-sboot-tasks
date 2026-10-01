/**
 * Handler shape: our own type. Deliberately free of any Servlet or Spring API; nothing in
 * Spring knows how to call it until GreetingHandlerAdapter is registered.
 */
public interface GreetingHandler {

    String greet(String name);
}
