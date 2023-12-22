package training.spring.turkcellspring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurityParam,
                                                       MyUserDetailService myUserDetailServiceParam) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                httpSecurityParam.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(myUserDetailServiceParam)
                                    .passwordEncoder(bCryptPasswordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain myFilterChain(HttpSecurity httpSecurityParam,
                                             JWTService jwtServiceParam,
                                             MyUserDetailService myUserDetailServiceParam) throws Exception {
        return httpSecurityParam.csrf(AbstractHttpConfigurer::disable)
                                .cors(AbstractHttpConfigurer::disable)
                                .authorizeHttpRequests(a -> a.requestMatchers("/actuator/**",
                                                                              "/api/v1/hello/**",
                                                                              "/auth/**")
                                                             .anonymous()
//                                                             .requestMatchers(new AntPathRequestMatcher("/api/v1/employee/provision/**"))
//                                                             .hasRole("ADMIN")
                                                             .anyRequest()
                                                             .authenticated())
                                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .formLogin(AbstractHttpConfigurer::disable)
                                .httpBasic(AbstractHttpConfigurer::disable)
                                .addFilterBefore(new JWTFilter(jwtServiceParam,
                                                               myUserDetailServiceParam),
                                                 UsernamePasswordAuthenticationFilter.class)
                                .build();
    }

}
