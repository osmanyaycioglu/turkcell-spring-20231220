package training.spring.turkcellspring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain myFilterChain(HttpSecurity httpSecurityParam) throws Exception {
        return httpSecurityParam.csrf(AbstractHttpConfigurer::disable)
                                .cors(AbstractHttpConfigurer::disable)
                                .authorizeHttpRequests(a -> a.requestMatchers("/actuator/**",
                                                                              "/api/v1/hello/**")
                                                             .anonymous()
//                                                             .requestMatchers(new AntPathRequestMatcher("/api/v1/employee/provision/**"))
//                                                             .hasRole("ADMIN")
                                                             .anyRequest()
                                                             .authenticated())
                                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .formLogin(AbstractHttpConfigurer::disable)
                                .httpBasic(Customizer.withDefaults())
                                .build();
    }

}
