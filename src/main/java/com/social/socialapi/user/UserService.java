package com.social.socialapi.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.social.socialapi.utils.authUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getFavorites(Integer user_id) {
        User user = userRepository.findById(user_id).orElseThrow();

        return user.getFavorites();
    }

    public void makeFavorite(Integer receiver_id) {
        String userEmail = authUtils.getAuthenticatedUser().getUsername();
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        User receiver = userRepository.findById(receiver_id).orElseThrow();

        List<User> favorites = user.getFavorites();
        favorites.add(receiver);

        user.setFavorites(favorites);

        userRepository.save(user);
    }
}
