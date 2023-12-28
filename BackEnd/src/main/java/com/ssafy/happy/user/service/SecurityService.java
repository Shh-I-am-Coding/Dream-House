package com.ssafy.happy.user.service;

import com.ssafy.happy.common.util.JwtTokenProvider;
import com.ssafy.happy.user.domain.User;
import com.ssafy.happy.user.dto.TokenResponse;
import com.ssafy.happy.user.dto.UserAccount;
import com.ssafy.happy.user.dto.UserLoginResponse;
import com.ssafy.happy.user.exception.NotExistUserException;
import com.ssafy.happy.user.exception.UnauthorizedException;
import com.ssafy.happy.user.repository.RefreshTokenRepository;
import com.ssafy.happy.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SecurityService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(NotExistUserException::new);
        return new UserAccount(user);
    }

    public String createToken(UserLoginResponse userLoginResponse) {
        TokenResponse tokenResponse = jwtTokenProvider.createTokens(userLoginResponse.getEmail());
        userLoginResponse.setAccessToken(tokenResponse.getAccessToken());
        return refreshTokenRepository.save(tokenResponse.getRefreshToken()).getRefreshToken();
    }

    public TokenResponse reissueToken(String refreshToken) {
        TokenResponse newTokenResponse = jwtTokenProvider.createTokens(jwtTokenProvider.getMemberEmail(refreshToken));
        refreshTokenRepository.deleteById("refreshToken:" + refreshToken);
        refreshTokenRepository.save(newTokenResponse.getRefreshToken());
        return newTokenResponse;
    }

    @Transactional(readOnly = true)
    public boolean validateRefreshToken(String refreshToken) {
        return refreshToken != null &&
                refreshTokenRepository.findById("refreshToken:" + refreshToken)
                        .map(token -> token.isSameUser(jwtTokenProvider.getMemberEmail(refreshToken)))
                        .orElse(false);
    }

    public UnauthorizedException logout() {
        //ToDo logout 로직 작성. 액세스/리프레시 토큰 삭제
        return new UnauthorizedException();
    }
}
