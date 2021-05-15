package com.example.BookReview.helper;

import com.example.BookReview.business.model.base.Administrator;
import com.example.BookReview.business.model.create.AdministratorCreateModel;
import com.example.BookReview.business.service.implementation.AdministratorServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {


    @Autowired
    private AdministratorServiceImplementation administratorService;

    @Override
    public void run(String... args) throws Exception {
        Administrator administrator = administratorService.findByEmail(AppConstants.defaultAdminEmail);

        if (administrator == null) {
            AdministratorCreateModel defaultAdministrator = new AdministratorCreateModel(AppConstants.defaultAdminPass, AppConstants.defaultAdminEmail, AppConstants.defaultAdminName);
            administratorService.create(defaultAdministrator);
        }
    }
}
