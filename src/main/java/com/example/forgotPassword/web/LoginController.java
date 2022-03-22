package com.example.forgotPassword.web;


import com.example.forgotPassword.models.bindingsModels.AddUserLoginBindingModel;
import com.example.forgotPassword.models.servicesModels.UserLoginServiceModel;
import com.example.forgotPassword.sevices.CarouselService;
import com.example.forgotPassword.sevices.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/users")
public class LoginController {


    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CarouselService carouselService;

    public LoginController(ModelMapper modelMapper, UserService userService, CarouselService carouselService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.carouselService = carouselService;
    }


    @GetMapping("/login")
    public String login(Model model) {


        if (!model.containsAttribute("addUserLoginBindingModel")) {
            model.addAttribute("addUserLoginBindingModel", new AddUserLoginBindingModel());// todo make th:value  *
        }


        model.addAttribute("firstImg", this.carouselService.firstImage());
        model.addAttribute("secondImg", this.carouselService.secondImage());
        model.addAttribute("thirdImg", this.carouselService.thirdImage());


        return "login-bv1";
    }

    @PostMapping("/login")
    private String login(@Valid AddUserLoginBindingModel addUserLoginBindingModel,
                         BindingResult bindingResult, RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addUserLoginBindingModel", addUserLoginBindingModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.addUserLoginBindingModel", bindingResult);
            return "redirect:/users/login";
        }


        this.mappAddLoginBindingToServiceModel(addUserLoginBindingModel);


        return "home-bv1";

    }


    @PostMapping("/login-error")
    private String failedLOgin(@ModelAttribute(
            value = UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                                       String username, RedirectAttributes attributes) {


        attributes.addFlashAttribute("bad_credentials", true);
        attributes.addFlashAttribute("username", username);

        return "redirect:/users/login";

    }

    private void mappAddLoginBindingToServiceModel(AddUserLoginBindingModel addUserLoginBindingModel) {


        UserLoginServiceModel userLoginServiceModel = this.modelMapper.map(
                addUserLoginBindingModel, UserLoginServiceModel.class);

        this.userService.addUserLogin(userLoginServiceModel);

    }
}
