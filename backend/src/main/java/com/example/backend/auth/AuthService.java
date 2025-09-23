package com.example.backend.auth;

import com.example.backend.auth.dto.AuthResponseDto;
import com.example.backend.auth.dto.LoginRequestDto;
import com.example.backend.auth.dto.SignupRequestDto;
import com.example.backend.common.util.JwtUtil;
import com.example.backend.user.UserRepository;
import com.example.backend.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public void signup(SignupRequestDto signupRequestDto) {
        if (userRepository.findByEmail(signupRequestDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 사용중인 이메일입니다.");
        }

        User user = new User();
        user.setUserName(signupRequestDto.getUserName());
        user.setEmail(signupRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequestDto.getPassword()));
        user.setPhoneNumber(signupRequestDto.getPhoneNumber());

        userRepository.save(user);
    }

    public AuthResponseDto login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("이메일 또는 비밀번호가 일치하지 않습니다."));

        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 일치하지 않습니다.");
        }

        String token = jwtUtil.generateToken(user.getId());
        return new AuthResponseDto(token);
    }
}