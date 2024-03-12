package fr.efrei.ordersharingsystem.configuration;

import fr.efrei.ordersharingsystem.presentation.interceptors.AuthorizationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

    @Bean
    public AuthorizationInterceptor authorizationInterceptor() {
        return new AuthorizationInterceptor();
    }

    @Override
    public void addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry registry) {
        registry
                .addInterceptor(authorizationInterceptor())
                .addPathPatterns("/api/v1/parks/**/products**")
                .addPathPatterns("/api/v1/parks/**/alleys/**/orders/**")
                .addPathPatterns("/api/v1/parks/**/alleys/**/currentSession");
    }
}