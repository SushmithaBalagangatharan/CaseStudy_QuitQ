package com.hexaware.quitqecom.service;

import java.util.List;

public interface Miscellaneous {
	void sendEmailNotification(String to, String subject, String message);
	boolean isUserAuthorized(int userId, String action);
	List<String> getAutoSuggestKeywords(String prefix);

}
