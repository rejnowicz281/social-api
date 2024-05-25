package com.social.socialapi.profile;

import com.social.socialapi.user.User;
import com.social.socialapi.user.UserRepository;
import com.social.socialapi.utils.authUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public List<Profile> getProfiles() {
        return profileRepository.findAll();
    }

    @Transactional
    public void createProfile(String description) {
        String currentUserEmail = authUtils.getAuthenticatedUser().getUsername();

        User user = userRepository.findByEmail(currentUserEmail).orElseThrow();

        if(user.getProfile() != null) {
            throw new IllegalStateException("Profile already exists");
        }

        Profile profile = new Profile();
        profile.setDescription(description);
        profile.setUser(user);

        profileRepository.save(profile);

        user.setProfile(profile);

        userRepository.save(user);
    }
}
