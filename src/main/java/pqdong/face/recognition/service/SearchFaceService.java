package pqdong.face.recognition.service;

import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.iai.v20200303.models.Result;
import com.tencentcloudapi.iai.v20200303.models.SearchFacesRequest;
import com.tencentcloudapi.iai.v20200303.models.SearchFacesResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pqdong.face.recognition.config.CoreConfig;
import pqdong.face.recognition.data.dto.FaceImage;
import pqdong.face.recognition.data.dto.PersonInfo;
import pqdong.face.recognition.data.dto.SearchFaceParam;
import pqdong.face.recognition.data.entity.PersonEntity;
import pqdong.face.recognition.repository.PersonRepository;

@Slf4j
@Service
public class SearchFaceService {

    @Resource
    private CoreConfig coreConfig;

    @Resource
    private PersonRepository personRepository;

    public PersonInfo searchFace(FaceImage faceImage) throws Exception {
        SearchFacesResponse searchFacesResponse;
        try {
            searchFacesResponse = searchFaces(SearchFaceParam.builder()
                .groupIds("face")
                .image(faceImage.getImage())
                .maxFaceNum(5)
                .maxPersonNum(5)
                .minFaceSize(1)
                .build());
        } catch (Exception e){
            throw e;
        }
        if (searchFacesResponse == null){
            throw new Exception("调用API查找失败");
        }
        Result[] results = searchFacesResponse.getResults();
        if (results == null) {
            throw new Exception("无匹配用户");
        }
        return PersonInfo.builder()
            .personId(searchFacesResponse.getResults()[0].getCandidates()[0].getPersonId())
            .personName(searchFacesResponse.getResults()[0].getCandidates()[0].getPersonName())
            .faceId(searchFacesResponse.getResults()[0].getCandidates()[0].getFaceId())
            .score(searchFacesResponse.getResults()[0].getCandidates()[0].getScore())
            .faceBase(personRepository.findAllByPersonId(searchFacesResponse.getResults()[0].getCandidates()[0].getPersonId()).getFaceBase())
            .build();
    }

    private SearchFacesResponse searchFaces(SearchFaceParam searchFaceParam){
        try{
            SearchFacesRequest req = new SearchFacesRequest();
            String[] groupIds1 = {searchFaceParam.getGroupIds()};
            req.setGroupIds(groupIds1);

            req.setImage(searchFaceParam.getImage());
            req.setMaxFaceNum(searchFaceParam.getMaxFaceNum());
            req.setMinFaceSize(searchFaceParam.getMinFaceSize());
            req.setMaxPersonNum(searchFaceParam.getMaxPersonNum());

            return coreConfig.getClient().SearchFaces(req);
//            System.out.println(SearchFacesResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            log.error("search faces error: {}", e.toString());
        }
        return null;
    }
}
