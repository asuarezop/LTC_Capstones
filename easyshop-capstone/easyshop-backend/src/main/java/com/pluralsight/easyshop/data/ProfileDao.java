package com.pluralsight.easyshop.data;


import com.pluralsight.easyshop.models.Profile;

import java.util.List;

public interface ProfileDao {
    List<Profile> getAllProfiles();
    Profile getByUserId(int id);
    Profile create(Profile profile);
    void update(int id, Profile profile);
}
