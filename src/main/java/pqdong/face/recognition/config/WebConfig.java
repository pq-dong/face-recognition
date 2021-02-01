package pqdong.face.recognition.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebConfig
 *
 * @author pengqd
 * @since 2021/01/28
 */
@Configuration("webConfig")
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedHeaders("*")
            .allowCredentials(true)
            .allowedMethods("*")
            .allowedOrigins("*");
    }

}

