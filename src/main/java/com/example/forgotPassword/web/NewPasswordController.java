package com.example.forgotPassword.web;





import com.example.forgotPassword.dto.NewPasswordDto;
import com.example.forgotPassword.repositories.UserRepository;
import com.example.forgotPassword.sevices.EmailService;
import com.example.forgotPassword.sevices.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@RestController
@Controller
public class NewPasswordController {


    private final EmailService emailService;
    private final UserService userService;
    private final UserRepository userRepository;

    public NewPasswordController(EmailService emailService, UserService userService, UserRepository userRepository) {
        this.emailService = emailService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/reset")
    public String newPassword(Model model, @RequestParam("token") String token) {


        if (!model.containsAttribute("newPasswordDto")) {

            model.addAttribute("newPasswordDto", new NewPasswordDto());
        }
        model.addAttribute("token", token);


        return "new-password";

    }


    @PostMapping("/reset")

    public String newPasswordConfirm(@RequestParam String token,
                                     @Valid NewPasswordDto newPasswordDto,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes) {
        System.out.println(token);
        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("newPasswordDto", newPasswordDto);
            redirectAttributes.
                    addFlashAttribute(
                            "org.springframework.validation.BindingResult.newPasswordDto", bindingResult);
        }









        return "new-password";


    }


}
