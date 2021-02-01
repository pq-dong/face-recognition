package pqdong.face.recognition.config;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import java.sql.Timestamp;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig
 *
 * @author pengqd
 * @since 2021/01/31
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api(@Value("${face-recognition.swagger.enabled}") boolean enabled) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfo(
                        "face-recognition",
                        "人脸识别",
                        "2.0.0",
                        null,
                        null,
                        null,
                        null,
                        Collections.emptyList()
                ))
                .enable(enabled)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .directModelSubstitute(Timestamp.class, Long.class)
                .consumes(ImmutableSet.of("application/json"))
                .produces(ImmutableSet.of("application/json"))
                .useDefaultResponseMessages(false);
    }
}
