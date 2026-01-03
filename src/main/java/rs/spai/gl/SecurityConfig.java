package rs.spai.gl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	    // CSRF (Cross-Site Request Forgery) protège contre certaines attaques web.
	        .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/h2/**").permitAll() // Autoriser la console H2
	            .anyRequest().hasRole("ADMIN")        // Tout le reste = ADMIN
	        )
	        .headers(headers -> headers.frameOptions().sameOrigin()) // Nécessaire pour H2
	        .httpBasic();// Active l’authentification HTTP Basic.

	    return http.build(); // renvoie la chaîne de filtres de sécurité.
	}
}
