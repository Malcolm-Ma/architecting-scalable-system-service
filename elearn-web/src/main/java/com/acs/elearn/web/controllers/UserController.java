package com.acs.elearn.web.controllers;

import com.acs.elearn.dao.model.Book;
import com.acs.elearn.dao.model.User;
import com.acs.elearn.service.UserService;
import com.acs.elearn.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/user")
public class UserController {
    @Autowired // This means to get the bean called userService
    UserServiceImpl userService;

//    @PostMapping(path="/add") // Map ONLY POST Requests
//    public @ResponseBody String addNewBook (@RequestParam String name) {
//        // @ResponseBody means the returned String is the response, not a view name
//        // @RequestParam means it is a parameter from the GET or POST request
//        return bookService.addBook(name);
//    }

    @GetMapping(path="/get")
    @ResponseBody User getUserInfo(@RequestParam String userId) {
        // This returns a JSON or XML with the users
        return userService.getUserInfo(userId);
    }

    @PostMapping(path="/update")
    @ResponseBody String updateUserInfo(@RequestBody User user) {
        return userService.updateUserInfo(user);
    }

    @PostMapping(path="/add")
    @ResponseBody
    String addUserInfo(@RequestBody User user) {
        return userService.addUserInfo(user);
    }
}
