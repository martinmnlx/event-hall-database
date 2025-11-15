package Controller;

import Model.User;
import Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    // Display login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // returns login.html from src/main/resources/templates
    }

    // Handle login form submission
    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password) {

        User user = userRepository.findByEmail(email);

        if (user != null && user.getPasswordHash().equals(password)) {
            // Successful login
            // TODO: Add session handling if needed
            return "redirect:/dashboard"; // redirect to dashboard/home page
        } else {
            // Login failed
            return "redirect:/login?error=true";
        }
    }

    // Optional: logout endpoint
    @GetMapping("/logout")
    public String logout() {
        // TODO: Clear session if using sessions
        return "redirect:/login";
    }
}
