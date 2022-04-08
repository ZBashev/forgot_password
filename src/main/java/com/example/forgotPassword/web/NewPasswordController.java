package com.example.forgotPassword.web;


import com.example.forgotPassword.dto.NewPasswordDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;



@Controller
public class NewPasswordController {




    @GetMapping("/reset")
    public String newPassword(@RequestParam("token") String token, Model model) {


        if (!model.containsAttribute("newPasswordDto")) {

            model.addAttribute("newPasswordDto", new NewPasswordDto());
        }

        model.addAttribute("resetToken", token);

        return "new-password";

    }


    @PostMapping("/reset")
    public String newPasswordConfirm(
            @Valid NewPasswordDto newPasswordDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes


    ) {


        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("newPasswordDto", newPasswordDto);
            redirectAttributes.
                    addFlashAttribute(
                            "org.springframework.validation.BindingResult.newPasswordDto", bindingResult);
        }




        return "redirect: index-bv1.html";


    }


}
