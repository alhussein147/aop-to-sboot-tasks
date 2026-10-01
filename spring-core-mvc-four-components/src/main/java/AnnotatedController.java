import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AnnotatedController {
    @GetMapping("/annotated")
    public String annotated(Model model) {
        model.addAttribute("handler", getClass().getName());
        return "annotated";
    }
}
