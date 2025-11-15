package Controller;

import Model.User;
import Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Show login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // login.html
    }

    // Handle login form submission
    @PostMapping("/login")
    public String loginUser(@RequestParam String username,
                            @RequestParam String password,
                            Model model) {

        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (user.getPassword().equals(password)) {
                // Password correct
                if ("ADMIN".equals(user.getRole())) {
                    return "redirect:/adminDashboard";
                } else {
                    return "redirect:/userDashboard";
                }
            } else {
                model.addAttribute("error", "Invalid password");
                return "login";
            }
        } else {
            model.addAttribute("error", "User not found");
            return "login";
        }
    }

    // Show registration page (optional)
    @GetMapping("/register")
    public String showRegisterPage() {
        return "createAccount"; // createAccount.html
    }
}

