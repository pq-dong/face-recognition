package pqdong.face.recognition.controller;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pqdong.face.recognition.data.Response;
import pqdong.face.recognition.data.dto.FaceImage;
import pqdong.face.recognition.data.dto.PersonInfo;
import pqdong.face.recognition.service.SearchFaceService;

/*
* 人脸识别接口
* */

@RestController
@RequestMapping("/api/recognition")
public class RecognitionController {

    @Resource
    private SearchFaceService searchFaceService;

    @PostMapping("/distinguish")
    public Response distinguish(
        @RequestBody FaceImage faceImage
    ){
        try {
            PersonInfo searchResult = searchFaceService.searchFace(faceImage);
            return Response.success(searchResult);
        } catch (Exception e){
            return Response.fail(e);
        }
    }

}
