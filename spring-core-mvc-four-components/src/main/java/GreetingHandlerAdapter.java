import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

/**
 * Our own HandlerAdapter. The DispatcherServlet asks every adapter supports(handler) and
 * uses the first that says yes; handle() is where the servlet world is translated into a
 * call on our type. It reads ?name=..., calls greet(), and returns a view name plus model.
 */
public class GreetingHandlerAdapter implements HandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return handler instanceof GreetingHandler;
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String name = request.getParameter("name");
        String greeting = ((GreetingHandler) handler).greet(name == null || name.isBlank() ? "world" : name);

        ModelAndView mav = new ModelAndView();
        mav.addObject("greeting", greeting);
        mav.setViewName("greet");
        return mav;
    }

    // Abstract in Spring 6.0/6.1, a deprecated default method in 6.2. Declared without
    // @Override so the class compiles against all of them.
    public long getLastModified(HttpServletRequest request, Object handler) {
        return -1;
    }
}
