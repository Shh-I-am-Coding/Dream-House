package com.ssafy.happy.user.model.service;

import javax.mail.MessagingException;

public interface EmailService {
	String sendMail(String email) throws MessagingException;
}
