package com.example.forgotPassword.web;


import com.example.forgotPassword.models.bindingsModels.UserRegisterBindingModel;
import com.example.forgotPassword.models.servicesModels.UserRegisterServiceModel;
import com.example.forgotPassword.sevices.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class RegisterController {

    private final ModelMapper modelMapper;
    private final UserService userService;
    private final UserRegisterServiceModel userRegisterServiceModel;

    public RegisterController(ModelMapper modelMapper, UserService userService,
                              UserRegisterServiceModel userRegisterServiceModel) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.userRegisterServiceModel = userRegisterServiceModel;
    }


    @GetMapping("/register")
    public String register(Model model) {


        if (!model.containsAttribute("userRegisterBindingModel")) {

            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }


        return "register-bv1";
    }

    @PostMapping("/register")
    public String confirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                          BindingResult bindingResult, RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "redirect:/users/register";
        }

        if (this.userService.existingUsername(userRegisterBindingModel.getUsername())) {

            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("existingUsername", true);

            return "redirect:/users/register";

        }


        mapUserRegister(userRegisterBindingModel);


        return "redirect:/";
    }

    private void mapUserRegister(UserRegisterBindingModel userRegisterBindingModel) {

        UserRegisterServiceModel userRegisterServiceModel = this.modelMapper.map(userRegisterBindingModel,
                UserRegisterServiceModel.class);

        this.userService.addUserRegister(userRegisterServiceModel);

    }


}
