package training.spring.turkcellspring.jpa;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/provision")
@RequiredArgsConstructor
public class UserProvisionController {
    private final IUserRepository userRepository;

    @PostMapping("/add")
    public String add(@Valid @RequestBody UserObj userObjParam){
        UserObj saveLoc = userRepository.save(userObjParam);
        return "CREATED : " + saveLoc.getUserId();
    }

    @GetMapping
    public UserObj findUserByUsername(@RequestParam String username){
        return userRepository.findByUsername(username);
    }

}
