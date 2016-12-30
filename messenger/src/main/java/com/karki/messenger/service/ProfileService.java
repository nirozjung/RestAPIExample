package com.karki.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.karki.messenger.database.DatabaseClass;
import com.karki.messenger.model.Profile;

public class ProfileService {

	private Map<String, Profile> profiles = DatabaseClass.getProfiles();

	public ProfileService() {
		profiles.put("crazy", new Profile(1, "crazy", "Niroz", "Karki"));
		profiles.put("singer", new Profile(2, "singer", "Sanu", "Maharjan"));
	}

	public List<Profile> getAllProfiles() {

		return new ArrayList<Profile>(profiles.values());

	}

	public Profile getProfile(String profileName) {
		return profiles.get(profileName);

	}

	public Profile addProfile(Profile profile) {
		profile.setId(profiles.size() + 1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}

	public Profile updateProfile(Profile profile) {
		if (profile.getProfileName().isEmpty()) {
			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}

	public Profile removeProfile(String profileName) {
		return profiles.remove(profileName);
	}

}
