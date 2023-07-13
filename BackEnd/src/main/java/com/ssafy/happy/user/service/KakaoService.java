package com.ssafy.happy.user.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.ssafy.happy.user.domain.User;
import com.ssafy.happy.user.dto.UserJoinRequest;
import com.ssafy.happy.user.dto.UserLoginResponse;
import com.ssafy.happy.user.repository.UserRepository;
import com.ssafy.happy.common.util.JwtTokenProvider;
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

@Service
@Transactional
@RequiredArgsConstructor
public class KakaoService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${oauth2.kakao.restApiKey}")
    private String kakao_restApiKey;

    @Value("${oauth2.kakao.redirectUri}")
    private String kakao_redirectUri;

    public UserLoginResponse login(String code) {
        code = JsonParser.parseString(code).getAsJsonObject().get("code").getAsString();
        String accessToken = getKakaoAccessToken(code);
        JsonElement element = getKakaoUserInfo(accessToken);

        String email = getEmail(element);
        String nickname = getNickname(element);
        User user = userRepository.findByEmail(email).orElseGet(() -> join(email, nickname));

        return UserLoginResponse.of(user, jwtTokenProvider.createToken(String.valueOf(user.getId())));
    }

    private User join(String email, String nickname) {
        return userRepository.save(new UserJoinRequest(email, null, nickname, "", "user").toEntity());
    }

    private String getKakaoAccessToken(String code) {
        String accessToken;
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            String sb = "grant_type=authorization_code"
                    + "&client_id="
                    + kakao_restApiKey
                    + "&redirect_uri="
                    + kakao_redirectUri
                    + "&code=" + code;
            bw.write(sb);
            bw.flush();

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = "";
                StringBuilder result = new StringBuilder();

                while ((line = br.readLine()) != null) {
                    result.append(line);
                }

                JsonElement element = JsonParser.parseString(result.toString());
                accessToken = element.getAsJsonObject().get("access_token").getAsString();

                br.close();
            } else {
                throw new IllegalArgumentException("카카오 로그인에 실패하였습니다.");
            }

            bw.close();
        } catch (IOException e) {
            throw new IllegalArgumentException("카카오 로그인에 실패하였습니다.");
        }
        return accessToken;
    }

    public String getEmail(JsonElement element) {
        return element.getAsJsonObject()
                .get("kakao_account")
                .getAsJsonObject()
                .get("email")
                .getAsString();
    }

    private String getNickname(JsonElement element) {
        return element.getAsJsonObject()
                .get("properties")
                .getAsJsonObject()
                .get("nickname")
                .getAsString();
    }

    private JsonElement getKakaoUserInfo(String token) {
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        JsonElement element;

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + token); //전송할 header 작성, access_token전송

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = "";
                StringBuilder result = new StringBuilder();

                while ((line = br.readLine()) != null) {
                    result.append(line);
                }
                //Gson 라이브러리로 JSON파싱
                element = JsonParser.parseString(result.toString());
                br.close();
            } else {
                throw new IllegalArgumentException("카카오에서 정보를 가져오는 데 실패하였습니다.");
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("카카오에서 정보를 가져오는 데 실패하였습니다.");
        }
        return element;
    }
}
