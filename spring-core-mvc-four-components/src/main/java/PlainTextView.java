import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.View;

import java.util.Map;

public class PlainTextView implements View {
    @Nullable
    @Override
    public String getContentType() {
        return View.super.getContentType();
    }

    @Override
    public void render(@Nullable Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

    }
}
