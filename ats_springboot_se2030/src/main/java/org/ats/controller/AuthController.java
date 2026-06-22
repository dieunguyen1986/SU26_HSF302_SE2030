package org.ats.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.ats.dto.UserRequest;
import org.ats.entities.User;
import org.ats.services.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/register")
    public String registerForm(){
        return "/views/auth/register";
    }

    @GetMapping
    public String login() {
        return "login";
    }

    @PostMapping
    public String processLogin(@RequestParam(name = "email") String email,
                               @RequestParam(name = "password") String password,
                               HttpSession session
    ) {

        log.info("User Credentials {}{}", email, password);

        // Call service
        User user = authService.authenticate(new UserRequest(email, password));
        session.setAttribute("user", user);

        return "redirect:/jobs/search";
    }
}
