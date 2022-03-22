package com.example.forgotPassword.web;



import com.example.forgotPassword.dto.ResetPasswordEmailDto;
import com.example.forgotPassword.models.entities.UserEntity;
import com.example.forgotPassword.sevices.EmailService;
import com.example.forgotPassword.sevices.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class PasswordResetController {

    private final UserService userService;
    private final EmailService emailService;

    public PasswordResetController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }


    @GetMapping("/reset-password")
    public String resetPassword(Model model) {


        if (!model.containsAttribute("emailDto")) {
            model.addAttribute("emailDto", new ResetPasswordEmailDto());
        }


        return "forgot-password";
    }

    @PostMapping("/reset-password")
    public String confirmEmail(@Valid ResetPasswordEmailDto emailDto, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes, @RequestParam("email") String email,
                               HttpServletRequest request
    ) {


        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("emailDto", emailDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.emialDto", bindingResult);


            return "redirect:/users/reset-password";
        }


        Optional<UserEntity> optionalUser = this.userService.findUserByEmail(email);


        if (optionalUser.isEmpty()) {


// TODO EXEPTION "this address does not exist  OR UPS something went wrong"
            return "redirect:/users/reset-password";

        } else {


            this.emailService.sendRequestResetPassword(request, email);



            return "redirect:/";


        }


    }
}

