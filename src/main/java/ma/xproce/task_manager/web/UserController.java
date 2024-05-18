package ma.xproce.task_manager.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@RequestMapping("/users")
@Controller
public class UserController {


    @GetMapping("/signup")
    public String signup() {
      return "signup";
    }

    @GetMapping("/process_registration")
    public String process_registration() {
        return "process_registration";

    }
    @GetMapping("/signin")
    public String signin() {
        return "signin";
    }
    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }
    @GetMapping("/updateprofile")
    public String updateProfile() {
        return "updateprofile";
    }




    /*

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "index"; // Returns the signup form template
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return "register_success";
    }




    @PostMapping("/signup")
    public String signUp(@RequestParam String username,
                         @RequestParam String fullName,
                         @RequestParam String email,
                         @RequestParam String phone,
                         @RequestParam String password,
                         @RequestParam String confirmPassword,
                         @RequestParam String imageUrl,
                         Model model) {
        // Call UserService to sign up user
        try {
            userService.signUp(username, fullName, email, phone, password, confirmPassword, imageUrl);
            return "redirect:/login"; // Redirect to login page after successful signup
        } catch (IllegalArgumentException e) {
            // Failed signup, add error message to model and return signup form again
            model.addAttribute("error", e.getMessage());
            return "signup";
        }
    }

    @GetMapping("/signin")
    public String showSignInForm(Model model) {
        // Add attributes to the model if needed
        return "signin"; // Returns the signin form template
    }

    @PostMapping("/signin")
    public String signIn(@RequestParam String username,
                         @RequestParam String password,
                         Model model) {
        // Call UserService to sign in user
        try {
            userService.signIn(username, password);
            return "redirect:/dashboard"; // Redirect to dashboard after successful signin
        } catch (RuntimeException e) {
            // Failed signin, add error message to model and return signin form again
            model.addAttribute("error", e.getMessage());
            return "signin";
        }
    }

 */


}
