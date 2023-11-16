package com.fe.neuroHub.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
	
    @Bean
    public CorsFilter corsFilter() {
    	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    	CorsConfiguration config = new CorsConfiguration();

    	// Permite requisições de qualquer origem
    	config.addAllowedOrigin("*");
    	
    	// Permite os métodos HTTP: GET, POST, PUT, DELETE, OPTIONS
    	config.addAllowedMethod("GET");
    	config.addAllowedMethod("POST");
    	config.addAllowedMethod("PUT");
    	config.addAllowedMethod("DELETE");
    	config.addAllowedMethod("OPTIONS");

    	// Permite cabeçalhos específicos
    	config.addAllowedHeader("*");

    	source.registerCorsConfiguration("/**", config);
    	return new CorsFilter(source);
    }
}
