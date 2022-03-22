package com.example.forgotPassword.sevices.impl;


import com.example.forgotPassword.sevices.CarouselService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {


    private Logger LOGGER = LoggerFactory.getLogger(CarouselServiceImpl.class);
    List<String> carouselImagesList = new ArrayList<>();


    public CarouselServiceImpl(@Value("${carousel.images}") List<String> images) {

        this.carouselImagesList.addAll(images);

    }

    @PostConstruct
    public void afterInitialize() {
        if (this.carouselImagesList.size() < 3) {
            throw new IllegalArgumentException("Sorry must configure  at least  three images...");
        }
    }


    @Override
    public String firstImage() {
        return this.carouselImagesList.get(0);
    }

    @Override
    public String secondImage() {
        return this.carouselImagesList.get(1);
    }

    @Override
    public String thirdImage() {
        return this.carouselImagesList.get(2);
    }


    @Scheduled(cron = "${carousel.refresh-cron}")
    public void refresh() {
        LOGGER.info("Shuffling images.....");
        Collections.shuffle(carouselImagesList);


    }
}
