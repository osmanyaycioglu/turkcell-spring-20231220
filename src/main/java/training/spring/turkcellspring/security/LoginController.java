package training.spring.turkcellspring.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import training.spring.turkcellspring.jpa.UserObj;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {
    private final JWTService            jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public String login(@RequestBody LoginObj loginObjParam) {
        Authentication authenticateLoc = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginObjParam.getUsername(),
                                                                                                                    loginObjParam.getPassword()));
        if (authenticateLoc == null) {
            return "Not valid user";
        }
        UserObj userObjLoc = new UserObj();
        userObjLoc.setUsername(loginObjParam.getUsername());
        return jwtService.generate(userObjLoc);
    }

    @GetMapping("/test")
    public String method() {
        return "Test";
    }

}
