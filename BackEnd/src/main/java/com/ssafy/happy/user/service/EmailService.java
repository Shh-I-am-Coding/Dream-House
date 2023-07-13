package com.ssafy.happy.user.service;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
	private final JavaMailSender javaMailSender;

	public String sendMail(String toEmail) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");

		helper.setFrom("happyhouseprj@naver.com");
		helper.setTo(toEmail);
		helper.setSubject("꿈의집 회원가입 이메일 인증");
		String certifiedCode = getCertifiedCode();

		String content = "<!DOCTYPE html>"
			+ "<html>"
			+ "<head>"
			+ "</head>"
			+ "<body>"
			+ " <div"
			+ "	style=\"font-family: 'Apple SD Gothic Neo', 'sans-serif' !important; width: 400px; height: 600px; border-top: 4px solid #02b875; margin: 100px auto; padding: 30px 0; box-sizing: border-box;\">"
			+ "	<h1 style=\"margin: 0; padding: 0 5px; font-size: 28px; font-weight: 400;\">"
			+ "		<span style=\"font-size: 15px; margin: 0 0 10px 3px;\">꿈의 집</span><br />"
			+ "		<span style=\"color: #02b875\">메일인증</span> 안내입니다." + "	</h1>\n"
			+ "	<p style=\"font-size: 16px; line-height: 26px; margin-top: 50px; padding: 0 5px;\">"
			+ "안녕하세요.<br />"
			+ "꿈의 집에 관심을 주셔서 진심으로 감사드립니다.<br />"
			+ "아래 인증코드를 입력하여 회원가입을 완료해주세요.<br />"
			+ "		감사합니다."
			+ "</p>"
			+ "<h2>" + certifiedCode + "</h2>"
			+ "	<div style=\"border-top: 1px solid #DDD; padding: 5px;\"></div>"
			+ " </div>"
			+ "</body>"
			+ "</html>";

		helper.setText(content, true);
		javaMailSender.send(mimeMessage);
		return certifiedCode;
	}

	private String getCertifiedCode() {
		Random random = new Random();
		StringBuilder sb = new StringBuilder();

		do {
			int num = random.nextInt(75) + 48;
			if (num <= 57 || num >= 65 && num <= 90 || num >= 97) {
				sb.append((char)num);
			}
		} while (sb.length() < 10);
		return sb.toString();
	}
}
