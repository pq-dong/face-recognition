package pqdong.face.recognition.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Slf4j
@Data
@Component
@Configuration
public class CoreConfig {

    @Value("${face-recognition.secretId}")
    private String secretId;

    @Value("${face-recognition.secretKey}")
    private String secretKey;
}
