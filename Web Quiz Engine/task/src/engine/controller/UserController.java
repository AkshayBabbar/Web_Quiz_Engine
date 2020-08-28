package engine.controller;

import engine.entity.User;
import engine.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/register")
public class UserController {


    private UserRepository users;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {

        System.out.println("Input request is " + user);


        System.out.println("This is the user details " + user.toString());
        System.out.println("This is the user repository " + users.findByEmail(user.getEmail()));
        if (users.findByEmail(user.getEmail()).isPresent()) {
            System.out.println(user.getEmail());
            return ResponseEntity.badRequest().build();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println("user response is " + users);
        return ResponseEntity.ok(users.save(user));

    }
}
