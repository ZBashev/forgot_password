package com.example.forgotPassword.web;


import com.example.forgotPassword.dto.NewPasswordDto;
import com.example.forgotPassword.repositories.UserRepository;
import com.example.forgotPassword.sevices.EmailService;
import com.example.forgotPassword.sevices.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;


@Controller

public class NewPasswordController {



    private final ModelMapper modelMapper;

    public NewPasswordController(  ModelMapper modelMapper) {


        this.modelMapper = modelMapper;
    }

    @GetMapping("/reset")
    public String newPassword(Model model ) {




        if (!model.containsAttribute("newPasswordDto")) {

            model.addAttribute("newPasswordDto", new NewPasswordDto());
        }



        return "new-password";

    }


    @PostMapping("/reset")
    public String newPasswordConfirm(
            @Valid NewPasswordDto newPasswordDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            @RequestParam Map<String, String> token

    ) {






        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("newPasswordDto", newPasswordDto);
            redirectAttributes.
                    addFlashAttribute(
                            "org.springframework.validation.BindingResult.newPasswordDto", bindingResult);
        }
        String tokenUUID=token.get("_csrf");


        System.out.println(tokenUUID);

        return "/";


    }


}
