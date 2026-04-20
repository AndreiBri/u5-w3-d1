package u5_w2_d5.andreibri.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import u5_w2_d5.andreibri.payloads.LoginDTO;
import u5_w2_d5.andreibri.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO body) {
        String token = authService.checkCredentialsAndGenerateToken(body);
        return ResponseEntity.ok(token);
    }
}