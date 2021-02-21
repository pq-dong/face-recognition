package pqdong.face.recognition.controller;


import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pqdong.face.recognition.data.Response;
import pqdong.face.recognition.data.dto.PersonInfo;
import pqdong.face.recognition.service.PersonService;

/*
* 人员库管理
* 包括人员列表，人员上传，删除，修改
* */

@RestController
@RequestMapping("/api/person")
public class PersonController {
    @Resource
    private PersonService personService;

    @GetMapping("/list")
    public Response list(
        @RequestParam int size,
        @RequestParam int page
    ){
        try {
            return Response.success(personService.list(size, page));
        } catch (Exception e){
            return Response.fail(e);
        }
    }

    @GetMapping("/info")
    public Response info(
        @RequestParam String personId
    ){
        try {
            return Response.success(personService.info(personId));
        } catch (Exception e){
            return Response.fail(e);
        }
    }

    @PostMapping("/add")
    public Response add(
        @RequestBody PersonInfo personInfo
    ){
        try {
            return Response.success(personService.add(personInfo));
        } catch (Exception e){
            return Response.fail(e);
        }
    }

    @GetMapping("/delete")
    public Response delete(
        @RequestParam String personId
    ){
        try {
            return Response.success(personService.delete(personId));
        } catch (Exception e){
            return Response.fail(e);
        }
    }
}
