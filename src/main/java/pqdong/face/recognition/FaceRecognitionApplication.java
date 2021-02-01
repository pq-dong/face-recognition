package pqdong.face.recognition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class FaceRecognitionApplication {

    public static void main(String[] args) {
        SpringApplication.run(FaceRecognitionApplication.class, args);
    }
}
