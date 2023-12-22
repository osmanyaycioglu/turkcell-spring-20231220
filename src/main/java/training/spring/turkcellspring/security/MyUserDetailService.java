package training.spring.turkcellspring.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import training.spring.turkcellspring.jpa.IUserRepository;
import training.spring.turkcellspring.jpa.UserObj;

@Service
@RequiredArgsConstructor
public class MyUserDetailService implements UserDetailsService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final IUserRepository       userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        UserObj byUsernameLoc = userRepository.findByUsername(username);
        if (byUsernameLoc == null) {
            throw new UsernameNotFoundException("Böyle bir user yok");
        }
        return User.builder()
                   .username(byUsernameLoc.getUsername())
                   .password(passwordEncoder.encode(byUsernameLoc.getPassword()))
                   .authorities(byUsernameLoc.getRole()
                                             .toString())
                   .build();
    }

}
