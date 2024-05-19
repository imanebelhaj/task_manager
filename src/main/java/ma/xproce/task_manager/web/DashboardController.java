package ma.xproce.task_manager.web;

import jakarta.servlet.http.HttpSession;
import ma.xproce.task_manager.dao.entites.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/users/signin";
        }

        model.addAttribute("user", loggedInUser);
        return "dashboard";
    }
}
