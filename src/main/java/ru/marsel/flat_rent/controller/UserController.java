package ru.marsel.flat_rent.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.marsel.flat_rent.domain.User;
import ru.marsel.flat_rent.domain.Views;
import ru.marsel.flat_rent.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }

    @PostMapping
    @JsonView(Views.UserInfo.class)
    public boolean create(
            @RequestBody User user
    ) {
        return userService.createUser(user);
    }

    @PutMapping("{id}")
    @JsonView(Views.UserInfo.class)
    public User update(
            @PathVariable("id") User userFromDb,
            @RequestBody User user,
            @AuthenticationPrincipal User currentUser
    ) {
        return userService.updateUser(userFromDb, user, currentUser);
    }
}
