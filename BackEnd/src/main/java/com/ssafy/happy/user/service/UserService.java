package com.ssafy.happy.user.service;

import com.ssafy.happy.user.domain.User;
import com.ssafy.happy.user.dto.UserJoinRequest;
import com.ssafy.happy.user.dto.UserLoginRequest;
import com.ssafy.happy.user.dto.UserLoginResponse;
import com.ssafy.happy.user.dto.UserModifyRequest;
import com.ssafy.happy.user.dto.UserResponse;
import com.ssafy.happy.user.repository.UserRepository;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
	private final UserRepository userRepository;
	private final JwtTokenProvider jwtTokenProvider;

	@Value("${oauth2.kakao.restApiKey}")
	private String kakao_restApiKey;

	@Value("${oauth2.kakao.redirectUri}")
	private String kakao_redirectUri;


	public void join(UserJoinRequest userJoinRequest) {
		userRepository.save(userJoinRequest.toEntity());
	}

	@Transactional(readOnly = true)
	public UserResponse findUser(Long id) {
		return UserResponse.of(userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다.")));
	}

	public void delete(Long id) {
		userRepository.delete(userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다.")));
	}

	@Transactional
	public void updateAccount(Long id, UserModifyRequest userModifyRequest) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
		user.update(userModifyRequest);
		userRepository.save(user);
	}

//	public String findPasswordByPhone(String id, String name, String phone) {
//		return userRepository.findPasswordByPhone(id, name, phone);
//	}
//
//	public String findPasswordByEmail(String id, String name, String email) {
//		return userRepository.findPasswordByEmail(id, name, email);
//	}

	public String getKakaoAccessToken(String code) {
		String access_Token = "";
		String refresh_Token = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";

		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();

			//POST 요청을 위해 기본값이 false인 setDoOutput을 true로
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			//POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			String sb = "grant_type=authorization_code"
				+ "&client_id="
				+ kakao_restApiKey
				+ "&redirect_uri="
				+ kakao_redirectUri // TODO 인가코드 받은 redirect_uri 입력
				+ "&code=" + code;
			bw.write(sb);
			bw.flush();

			//결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);
			//요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			StringBuilder result = new StringBuilder();

			while ((line = br.readLine()) != null) {
				result.append(line);
			}
			System.out.println("response body : " + result);

			//Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
			JsonElement element = JsonParser.parseString(result.toString());

			access_Token = element.getAsJsonObject().get("access_token").getAsString();
			refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

			System.out.println("access_token : " + access_Token);
			System.out.println("refresh_token : " + refresh_Token);

			br.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return access_Token;
	}

	public void createKakaoUser(String token) {
		String reqURL = "https://kapi.kakao.com/v2/user/me";

		//access_token을 이용하여 사용자 정보 조회
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();

			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setRequestProperty("Authorization", "Bearer " + token); //전송할 header 작성, access_token전송

			//결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			//요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			StringBuilder result = new StringBuilder();

			while ((line = br.readLine()) != null) {
				result.append(line);
			}
			System.out.println("response body : " + result);

			//Gson 라이브러리로 JSON파싱
			JsonElement element = JsonParser.parseString(result.toString());

			String nickname = element.getAsJsonObject()
				.get("properties")
				.getAsJsonObject()
				.get("nickname")
				.getAsString();
			boolean hasEmail = element.getAsJsonObject()
				.get("kakao_account")
				.getAsJsonObject()
				.get("has_email")
				.getAsBoolean();
			String email = "";
			if (hasEmail) {
				email = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
			}

			join(new UserJoinRequest(email, null, nickname, "", "user"));
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Transactional(readOnly = true)
	public void confirmPassword(Long id, String password) {
		boolean confirmed = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다.")).checkPassword(password);
		if(!confirmed) {
			throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
		}
	}

	public UserLoginResponse login(UserLoginRequest userLoginRequest) {
		User user = userRepository.findByEmail(userLoginRequest.getEmail())
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
		if(!user.checkPassword(userLoginRequest.getPassword())) {
			throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
		}
		return UserLoginResponse.of(user, generateJwtToken(user.getId()));
	}

	private String generateJwtToken(Long id) {
		String accessToken;
		try {
			accessToken = jwtTokenProvider.createToken(String.valueOf(id));
		} catch (Exception e) {
			throw new IllegalArgumentException("JWT 토큰 생성에 실패하였습니다.");
		}
		return accessToken;
	}
}
