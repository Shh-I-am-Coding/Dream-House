package com.ssafy.happy.common.util;

import com.ssafy.happy.user.domain.RefreshToken;
import com.ssafy.happy.user.dto.TokenResponse;
import com.ssafy.happy.user.service.SecurityService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Base64;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private static final int TOKEN_START_IDX = 7;
    private static final long ACCESS_TOKEN_VALID_TIME = 60 * 60 * 1000L;  //1시간
    private static final long REFRESH_TOKEN_VALID_TIME = 14 * 24 * 60 * 1000L; //2주
    private static final String TOKEN_PREFIX = "Bearer ";

    @Value("${spring.jwt.secretKey}")
    private String secretKey;
    private final SecurityService securityService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public TokenResponse createTokens(String payload) {
        String accessToken = createAccessToken(payload);
        RefreshToken refreshToken = createRefreshToken(payload);
        return new TokenResponse(accessToken, refreshToken);
    }

    private String createAccessToken(String payload) {
        return createJwtToken(payload, ACCESS_TOKEN_VALID_TIME);
    }

    private RefreshToken createRefreshToken(String payload) {
        return new RefreshToken(createJwtToken(payload, REFRESH_TOKEN_VALID_TIME), payload);
    }

    private String createJwtToken(String payload, long tokenValidTime) {
        Claims claims = Jwts.claims().setSubject(payload);
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Authentication getAuthentication(String accessToken) {
        UserDetails userDetails = securityService.loadUserByUsername(getMemberEmail(accessToken));
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
                userDetails.getAuthorities());
    }

    public String getMemberEmail(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveAccessToken(HttpServletRequest httpServletRequest) {
        String auth;
        try {
            auth = httpServletRequest.getHeader("Authorization").substring(TOKEN_START_IDX);
        } catch (Exception e) {
            return null;
        }
        return auth;
    }

    public boolean validateAccessToken(String accessToken) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(accessToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
