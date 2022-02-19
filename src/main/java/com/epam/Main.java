package com.epam;

import com.epam.manager.UserManager;
import com.epam.model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
       User user = new User();
//        userManager.delete(6);

        user.setId(8);
        user.setName("sevo");
        user.setSurname("davo");
        user.setEmail("davo");
        user.setPassword("davo");
        userManager.update(user);
//
//        List<User> users = userManager.getAll();
//        for (User user1 : users) {
//            System.out.println(user1);
//        }
        // System.out.println(userManager.getById(2));
        System.out.println(userManager.getById(8));

    }
}
