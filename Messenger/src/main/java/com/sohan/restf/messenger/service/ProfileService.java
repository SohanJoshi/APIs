package com.sohan.restf.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sohan.restf.messenger.database.Database;
import com.sohan.restf.messenger.model.Profile;


public class ProfileService {

	private Map<String, Profile> profiles = Database.getAllProfiles();

	public ProfileService() {
		profiles.putIfAbsent("Profile1" , new Profile(1L, "Profile 1 ", "Jim", "Jany"));
		profiles.putIfAbsent("Profile2" , new Profile(2L, "Profile 2", "Tom", "Teddy"));
	}
	
	public List<Profile> getAllProfiles(){
		return new ArrayList<>(profiles.values());
				
	}
	
	public Profile getProfile(String profileName) {
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile) {
		long id = profiles.size() + 1;
		profile.setId(id);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile updatepProfile(Profile profile) {
		if(profile.getProfileName().isEmpty())
			return null;
		profile = profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile deleteProfile(String profileName) {
		return profiles.remove(profileName);
	}
}
