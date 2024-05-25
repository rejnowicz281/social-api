package com.social.socialapi.user;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
    public final UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("{user_id}/favorites")
    public List<User> getFavorites(@PathVariable("user_id") Integer user_id) {
        return userService.getFavorites(user_id);
    }

    @PostMapping("favorites")
    public void makeFavorite(@RequestBody MakeFavoriteRequest request) {
        userService.makeFavorite(request.getReceiver_id());
    }

}
