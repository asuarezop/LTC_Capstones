package com.pluralsight.easyshop.controllers;

import com.pluralsight.easyshop.data.ProfileDao;
import com.pluralsight.easyshop.models.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/profile")
public class ProfileController {
    private ProfileDao profileDao;

    public ProfileController(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    @GetMapping
    public List<Profile> getAllProfiles() {
        return profileDao.getAllProfiles();
    }

    @GetMapping("/{id}")
    public Profile getByUserId(@PathVariable int id) {
        try {
            Profile profile = profileDao.getByUserId(id);
            if (profile == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

            return profile;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody Profile profile) {
        try {
            profileDao.update(id, profile);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
