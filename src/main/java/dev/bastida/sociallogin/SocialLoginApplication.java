package dev.bastida.sociallogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.security.config.Customizer.withDefaults;

@SpringBootApplication
public class SocialLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialLoginApplication.class, args);
    }

}

@RestController
class HomeController {

    @GetMapping("/")
    public String home() {
        return "Hello, home!";
    }

    @GetMapping("/secured")
    public String secured() {
        return "Hello, Secured!";
    }
}

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/").permitAll();
                    auth.anyRequest().authenticated();
                })
                .oauth2Login(withDefaults())
                .formLogin(withDefaults())
                .build();
    }
}
