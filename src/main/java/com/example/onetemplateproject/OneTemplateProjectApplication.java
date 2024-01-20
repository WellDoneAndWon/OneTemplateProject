package com.example.onetemplateproject;

import com.example.onetemplateproject.security.dto.AuthDto;
import com.example.onetemplateproject.security.model.User;
import com.example.onetemplateproject.security.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.security.auth.login.LoginException;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@SpringBootApplication
public class OneTemplateProjectApplication {

    private final UserRepository userRepository;
    //   private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(OneTemplateProjectApplication.class, args);
    }

    @GetMapping("/")
    public String getPage(Model model) {
        UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userName", ud.getUsername());
        return "hello-world";
    }

    @GetMapping("/auth-form")
    public String getAuthForm() {
        return "auth-form";
    }

    @PostMapping("/auth")
    public RedirectView auth(@Valid @ModelAttribute AuthDto authDto) throws LoginException {

//        Authentication a = new UsernamePasswordAuthenticationToken(authDto.getLogin(), authDto.getPassword());
//        Authentication b = authenticationManager.authenticate(a);
//        var tt = b.getPrincipal();
//
//        ((UserDetails) tt).getUsername();

        Optional<User> user = userRepository.findByLogin(authDto.getLogin());

        if (!user.isEmpty()) {
            System.err.println("Пользователь с таким логином уже есть в системе!");
            return new RedirectView("/auth-form");
        }

        User newUser = new User();
        newUser.setLogin(authDto.getLogin());
        newUser.setPassword(passwordEncoder.encode(authDto.getPassword()));
        newUser.setFio(authDto.getFio());
        newUser.setRole("USER");
        newUser.setStatus("ACTIVE");

        userRepository.save(newUser);

        return new RedirectView("/login");
    }
}
