package com.ssafy.happy.user.controller;

import com.ssafy.happy.common.dto.ApiResponse;
import com.ssafy.happy.user.dto.UserAccount;
import com.ssafy.happy.user.dto.UserJoinRequest;
import com.ssafy.happy.user.dto.UserLoginRequest;
import com.ssafy.happy.user.dto.UserLoginResponse;
import com.ssafy.happy.user.dto.UserModifyRequest;
import com.ssafy.happy.user.dto.UserResponse;
import com.ssafy.happy.user.service.KakaoService;
import com.ssafy.happy.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final KakaoService kakaoService;

    @PostMapping("/confirm-password")
    @ApiOperation(value = "비밀번호 확인", notes = "비밀번호가 같은 지 확인")
    public ResponseEntity<ApiResponse<?>> confirmPassword(@AuthenticationPrincipal UserAccount account,
                                                          @RequestBody String password) {
        userService.confirmPassword(account, password);
        return ApiResponse.successWithNoContent();
    }

    @PostMapping("/login")
    @ApiOperation(value = "일반 로그인", notes = "이메일과 비밀번호로 로그인")
    public ResponseEntity<ApiResponse<UserLoginResponse>> login(@RequestBody UserLoginRequest userLoginRequest) {
        return ApiResponse.successWithData(userService.login(userLoginRequest));
    }

    @PostMapping("/login/oauth/kakao")
    @ApiOperation(value = "카카오 로그인", notes = "카카오 로그인 api 콜백 함수")
    public ResponseEntity<ApiResponse<UserLoginResponse>> kakaoLogin(@RequestBody String code) {
        return ApiResponse.successWithData(kakaoService.login(code));
    }

    @GetMapping
    @ApiOperation(value = "현재 로그인된 회원 정보 반환", notes = "회원 정보 반환")
    public ResponseEntity<ApiResponse<UserResponse>> getInfo(@AuthenticationPrincipal UserAccount account) {
        return ApiResponse.successWithData(UserResponse.of(account.getUser()));
    }

    @PostMapping
    @ApiOperation(value = "회원 가입", notes = "일반 회원 가입")
    public ResponseEntity<ApiResponse<?>> join(@RequestBody UserJoinRequest userJoinRequest) {
        userService.join(userJoinRequest);
        return ApiResponse.successWithNoContent();
    }

    @PostMapping("/send-email")
    @ApiOperation(value = "이메일 인증 확인", notes = "회원 가입 시 이메일 인증")
    public ResponseEntity<ApiResponse<String>> sendEmail(@RequestBody String email) {
        return ApiResponse.successWithData(userService.sendEmail(email));
    }

    @PutMapping
    @ApiOperation(value = "회원 정보 수정", notes = "기존 회원의 정보 수정")
    public ResponseEntity<ApiResponse<?>> update(@AuthenticationPrincipal UserAccount account,
                                                 @RequestBody UserModifyRequest userModifyRequest) {
        userService.update(account, userModifyRequest);
        return ApiResponse.successWithNoContent();
    }

    @DeleteMapping
    @ApiOperation(value = "회원 탈퇴", notes = "회원 탈퇴로 인한 삭제")
    public ResponseEntity<ApiResponse<?>> delete(@AuthenticationPrincipal UserAccount account) {
        userService.delete(account);
        return ApiResponse.successWithNoContent();
    }

//	@PostMapping("/findPasswordByPhone")
//	public String findPasswordByPhone(String id, String name, String phone, Model model) {
//		String pw = userService.findPasswordByPhone(id, name, phone);
//		if (pw != null) {
//			model.addAttribute("msg", "비밀번호는 " + pw + "입니다.");
//			return "/user/login";
//		} else {
//			model.addAttribute("msg", "회원정보가 올바르지 않습니다.");
//			return "/user/findPassword";
//		}
//	}
//
//	@PostMapping("/findPasswordByEmail")
//	public String findPasswordByEmail(String id, String name, String email, Model model) {
//		String pw = userService.findPasswordByEmail(id, name, email);
//		if (pw != null) {
//			model.addAttribute("msg", "비밀번호는 " + pw + "입니다.");
//			return "/user/login";
//		} else {
//			model.addAttribute("msg", "회원정보가 올바르지 않습니다.");
//			return "/user/findPassword";
//		}
//	}
}
