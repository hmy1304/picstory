package picstroy.backend.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import picstroy.backend.service.LoginService;
import picstroy.backend.web.dto.LoginRequest;
import picstroy.backend.web.dto.MemberResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final LoginService loginService;
    @PostMapping("/login")
    public MemberResponse login(@RequestBody LoginRequest request, HttpSession session) {
        return loginService.login(request, session);
    }

    @GetMapping("/me")
    public MemberResponse memberResponse(HttpSession session) {
        return loginService.me(session);
    }

    @PostMapping("/logout")
    public void logout(HttpSession session) {
        loginService.logout(session);
    }
}
