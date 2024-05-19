package ma.xproce.task_manager.web;


import jakarta.servlet.http.HttpSession;
import ma.xproce.task_manager.dao.repositories.UserRepository;
import ma.xproce.task_manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ma.xproce.task_manager.dao.entites.User;



@RequestMapping("/users")
@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

//signup

    @GetMapping("/signup")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "signup";
    }
    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return "signin";
    }

    //singin

    @GetMapping("/signin")
    public String  showSignInForm(Model model) {
        return "signin";
    }


    @PostMapping("/process_login")
    public String processLogin(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        User user = userRepository.findByUsername(username);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            // Login successful
            session.setAttribute("loggedInUser", user);
            model.addAttribute("username", username);
            return "redirect:/dashboard";
        } else {
            // Login failed
            model.addAttribute("error", "Invalid username or password.");
            return "redirect:/users/signin";
        }
    }


    //Still No traitement
    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }
    @GetMapping("/updateprofile")
    public String updateProfile() {
        return "updateprofile";
    }


}
