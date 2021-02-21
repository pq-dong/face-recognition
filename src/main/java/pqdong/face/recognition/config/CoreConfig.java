package pqdong.face.recognition.config;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.iai.v20200303.IaiClient;
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

    private static IaiClient client;

    public IaiClient getClient(){
        if (client != null){
            return client;
        }
        Credential cred = new Credential(secretId, secretKey);
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("iai.tencentcloudapi.com");
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        client = new IaiClient(cred, "ap-beijing", clientProfile);
        return client;
    }
}
