package Service;

import Model.User;
import Repository.UserRepository;
// it secures users passwords by making it into a hash
// plain text -> encrypted version
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // for registering a new user with their hashed passwords
    public User registerUser(User user, String password) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("Email already exists!");
        }
        String hashedPassword = bCryptPasswordEncoder.encode(password);
        user.setPasswordHash(hashedPassword);
        return userRepository.save(user);
    }

    public User checkUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && bCryptPasswordEncoder.matches(password, user.getPasswordHash())) {
            return user;
        }
        return null;
    }
}
