package pqdong.face.recognition.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pqdong.face.recognition.data.Response;


@RestController
@RequestMapping("/api/util")
public class UtilController {

    @GetMapping("/system")
    public Response<String> pingSystem() {
        return Response.success("system health");
    }

    @GetMapping("/test")
    public Response<String> test(){
        return Response.success("success");
    }

}
