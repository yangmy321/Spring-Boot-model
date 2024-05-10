package top.test.boot.fi.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.test.boot.fi.interceptor.*;

/**
 * @author 34746
 */
@Configuration
@AllArgsConstructor
public class SpringMvcConfig implements WebMvcConfigurer {
    private final CustomInterceptor customInterceptor;
    private final AuthenticationInterception authenticationInterceptor;
    private final LoggingInterceptor loggingInterceptor;
    private final PerformanceInterceptor performanceInterceptor;
    private final ImageProcessingInterceptor imageProcessingInterceptor;

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedOriginPattern("*");
        corsConfiguration.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor);
        registry.addInterceptor(customInterceptor);
        registry.addInterceptor(loggingInterceptor);
        registry.addInterceptor(performanceInterceptor);
        registry.addInterceptor(imageProcessingInterceptor);
    }
}