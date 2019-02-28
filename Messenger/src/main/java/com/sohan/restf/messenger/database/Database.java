package com.sohan.restf.messenger.database;

import java.util.HashMap;
import java.util.Map;

import com.sohan.restf.messenger.model.Message;
import com.sohan.restf.messenger.model.Profile;

public class Database {

	private static Map<Long, Message> messages = new HashMap<>();
	private static Map<String, Profile> profiles = new HashMap<>();
	
	public static Map<Long, Message> getAllMessages(){
		return messages;
	}
	
	public static Map<String, Profile> getAllProfiles(){
		return profiles;
	}
}
