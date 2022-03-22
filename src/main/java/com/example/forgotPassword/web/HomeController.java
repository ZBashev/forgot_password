package com.example.forgotPassword.web;


import com.example.forgotPassword.sevices.CarouselService;
import com.example.forgotPassword.sevices.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CarouselService carouselService;


    private final UserService userService;


    public HomeController(CarouselService carouselService,
                          UserService userService) {
        this.carouselService = carouselService;
        this.userService = userService;

    }

    @GetMapping("/")
    public String index(Model model) {


        model.addAttribute("firstImg", this.carouselService.firstImage());
        model.addAttribute("secondImg", this.carouselService.secondImage());
        model.addAttribute("thirdImg", this.carouselService.thirdImage());

     //   this.userService.viewCountArticelInNav(model);

        return "index-bv1";
    }

    @GetMapping("/home")
    public String home(Model model) {


//        int countInNav = this.userService.getCountPurchasesByUser();

     //   model.addAttribute("seeCounterPurchases", countInNav);


        return "home-bv1";
    }


}
