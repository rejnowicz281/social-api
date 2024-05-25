package com.social.socialapi.profile;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/profiles")
@RequiredArgsConstructor
public class ProfileController {
    public final ProfileService profileService;

    @GetMapping
    public List<Profile> getProfiles() {
        return profileService.getProfiles();
    }

    @PostMapping
    public void createProfile(@RequestBody Profile body) {
        profileService.createProfile(body.getDescription());
    }
}
