package pqdong.face.recognition.controller;

import java.util.Arrays;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pqdong.face.recognition.data.Response;
import pqdong.face.recognition.data.dto.FaceImage;
import pqdong.face.recognition.data.dto.SearchResult;
import pqdong.face.recognition.service.SearchFaceService;
import sun.misc.BASE64Encoder;

@RestController
@RequestMapping("/api/recognition")
public class RecognitionController {

    @Resource
    private SearchFaceService searchFaceService;

    @PostMapping("/test")
    public Response test(
        @RequestBody FaceImage faceImage
    ){
        try {
            SearchResult searchResult = searchFaceService.searchFace(faceImage);
            return Response.success(searchResult);
        } catch (Exception e){
            return Response.fail(e);
        }
    }

    @PostMapping("/test2")
    public Response test2(
        @RequestParam MultipartFile file
    ){
        try {
            BASE64Encoder encoder = new BASE64Encoder();
            SearchResult searchResult = searchFaceService.searchFace(new FaceImage(encoder.encode(file.getBytes())));
            return Response.success(searchResult);
        } catch (Exception e){
            return Response.fail(e);
        }
    }
}
