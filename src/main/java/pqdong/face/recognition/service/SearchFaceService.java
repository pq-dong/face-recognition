package pqdong.face.recognition.service;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.iai.v20200303.IaiClient;
import com.tencentcloudapi.iai.v20200303.models.Result;
import com.tencentcloudapi.iai.v20200303.models.SearchFacesRequest;
import com.tencentcloudapi.iai.v20200303.models.SearchFacesResponse;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pqdong.face.recognition.config.CoreConfig;
import pqdong.face.recognition.data.dto.FaceImage;
import pqdong.face.recognition.data.dto.SearchFaceParam;
import pqdong.face.recognition.data.dto.SearchResult;

@Slf4j
@Service
public class SearchFaceService {

    @Resource
    private CoreConfig coreConfig;

    public SearchResult searchFace(FaceImage faceImage) throws Exception {
        SearchFacesResponse searchFacesResponse = searchFaces(SearchFaceParam.builder()
            .groupIds("face")
            .image(faceImage.getImage())
            .maxFaceNum(5)
            .maxPersonNum(5)
            .minFaceSize(1)
            .build());
        if (searchFacesResponse == null){
            throw new Exception("调用API查找失败");
        }
        Result[] results = searchFacesResponse.getResults();
        if (results == null) {
            throw new Exception("无匹配用户");
        }
        return new SearchResult(searchFacesResponse.getResults()[0].getCandidates()[0].getPersonId());
    }

    private SearchFacesResponse searchFaces(SearchFaceParam searchFaceParam){
        try{

            Credential cred = new Credential(coreConfig.getSecretId(), coreConfig.getSecretKey());

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("iai.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            IaiClient client = new IaiClient(cred, "ap-beijing", clientProfile);

            SearchFacesRequest req = new SearchFacesRequest();
            String[] groupIds1 = {searchFaceParam.getGroupIds()};
            req.setGroupIds(groupIds1);

            req.setImage(searchFaceParam.getImage());
            req.setMaxFaceNum(searchFaceParam.getMaxFaceNum());
            req.setMinFaceSize(searchFaceParam.getMinFaceSize());
            req.setMaxPersonNum(searchFaceParam.getMaxPersonNum());

            return client.SearchFaces(req);
//            System.out.println(SearchFacesResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            log.error("search faces error: {}", e.toString());
        }
        return null;
    }
}
