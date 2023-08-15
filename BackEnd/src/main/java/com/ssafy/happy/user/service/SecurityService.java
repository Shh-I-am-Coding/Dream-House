package com.ssafy.happy.user.service;

import com.ssafy.happy.user.domain.User;
import com.ssafy.happy.user.dto.UserAccount;
import com.ssafy.happy.user.exception.NotExistUserException;
import com.ssafy.happy.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SecurityService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         User user = userRepository.findByEmail(username)
                .orElseThrow(NotExistUserException::new);
         return new UserAccount(user);
    }
}
