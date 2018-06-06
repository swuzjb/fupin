package com.ty.fuping.startup;

import com.ty.fuping.controller.UserController;
import com.ty.fuping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import javax.xml.ws.Action;

/**
 * Created by ty on 2018/6/6.
 */
public class StartUpRun implements ApplicationRunner {

    @Autowired
    private UserService userService;
    @Autowired
    private UserController userController;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        if (userService.findUserByUserName("admin") != null)
            return;
        else {
            userController.install();
        }
    }
}
