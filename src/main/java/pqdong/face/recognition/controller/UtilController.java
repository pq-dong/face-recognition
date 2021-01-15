package pqdong.face.recognition.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pqdong.face.recognition.data.vo.ResponseMessage;


@RestController
@RequestMapping("/util")
public class UtilController {

    @GetMapping("/ping/system")
    public ResponseMessage<String> pingSystem() {
        return ResponseMessage.successMessage("system health");
    }

}
