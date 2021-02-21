package pqdong.face.recognition.service;


import com.tencentcloudapi.iai.v20200303.models.CreatePersonRequest;
import com.tencentcloudapi.iai.v20200303.models.CreatePersonResponse;
import com.tencentcloudapi.iai.v20200303.models.DeletePersonRequest;
import com.tencentcloudapi.iai.v20200303.models.GetPersonBaseInfoRequest;
import com.tencentcloudapi.iai.v20200303.models.GetPersonBaseInfoResponse;
import com.tencentcloudapi.iai.v20200303.models.GetPersonListRequest;
import com.tencentcloudapi.iai.v20200303.models.GetPersonListResponse;
import com.tencentcloudapi.iai.v20200303.models.PersonExDescriptionInfo;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pqdong.face.recognition.config.CoreConfig;
import pqdong.face.recognition.data.dto.PersonInfo;
import pqdong.face.recognition.data.dto.PersonList;
import pqdong.face.recognition.data.entity.PersonEntity;
import pqdong.face.recognition.repository.PersonRepository;

@Slf4j
@Service
public class PersonService {

    @Resource
    private CoreConfig coreConfig;

    @Resource
    private PersonRepository personRepository;

    /**
     * 获取人员列表
     */
    public PersonList list(int size, int page) throws Exception {
        //获取腾讯云人员列表
        GetPersonListRequest req = new GetPersonListRequest();
        req.setGroupId("face");
        req.setOffset((long) (size * page));
        req.setLimit((long) size);
        GetPersonListResponse resp = coreConfig.getClient().GetPersonList(req);
        if (resp.getPersonInfos() == null || resp.getPersonInfos().length == 0){
            throw new Exception("获取人员列表为空");
        }
        Map<String, String> personFaces = personRepository.findAllByPersonIdIn(Arrays.stream(resp.getPersonInfos()).map(
            com.tencentcloudapi.iai.v20200303.models.PersonInfo::getPersonId).collect(Collectors.toList()))
            .stream().collect(Collectors.toMap(PersonEntity::getPersonId, PersonEntity::getFaceBase));
        PersonList personList = new PersonList();
        //将结果转化
        personList.setPersonNum(resp.getPersonNum());
        personList.setPersons(Arrays.stream(resp.getPersonInfos()).map(personInfo -> {
            PersonInfo person = PersonInfo.builder()
                .creationTimestamp(personInfo.getCreationTimestamp())
                .gender(personInfo.getGender() == 1 ? "男" : "女")
                .personId(personInfo.getPersonId())
                .personName(personInfo.getPersonName())
                .faceBase(personFaces.getOrDefault(personInfo.getPersonId(),""))
                .build();
            if (personInfo.getFaceIds() != null && personInfo.getFaceIds().length != 0){
                person.setFaceId(personInfo.getFaceIds()[0]);
            }
            if (personInfo.getPersonExDescriptions() != null && personInfo.getPersonExDescriptions().length != 0){
                person.setPersonExDescription(personInfo.getPersonExDescriptions()[0]);
            }
            return person;
        }).collect(Collectors.toList()));
        return personList;
    }

    /**
     * 获取人员详细信息
     */
    public PersonInfo info(String personId) throws Exception {
        GetPersonBaseInfoRequest req = new GetPersonBaseInfoRequest();
        req.setPersonId(personId);
        GetPersonBaseInfoResponse resp = coreConfig.getClient().GetPersonBaseInfo(req);
        if (StringUtils.isEmpty(resp.getPersonName())){
            throw new Exception("不存在当前人员");
        }
        PersonEntity personEntity = personRepository.findAllByPersonId(personId);
        return PersonInfo.builder()
            .personId(personId)
            .faceBase(personEntity.getFaceBase())
            .personName(resp.getPersonName())
            .gender(resp.getGender() == 2 ? "女" : "男")
            .faceId(resp.getFaceIds()[0])
            .build();
    }

    /**
     * 增加人员
     */
    public PersonInfo add(PersonInfo personInfo) throws Exception {

        CreatePersonRequest req = new CreatePersonRequest();
        PersonExDescriptionInfo[] personExDescriptionInfos1 = new PersonExDescriptionInfo[1];
        PersonExDescriptionInfo personExDescriptionInfo1 = new PersonExDescriptionInfo();
        personExDescriptionInfo1.setPersonExDescriptionIndex(0L);
        personExDescriptionInfo1.setPersonExDescription(personInfo.getPersonExDescription());
        personExDescriptionInfos1[0] = personExDescriptionInfo1;

        req.setPersonExDescriptionInfos(personExDescriptionInfos1);
        req.setGroupId("face");
        req.setPersonName(personInfo.getPersonName());
        req.setPersonId(personInfo.getPersonId());
        req.setGender(personInfo.getGender().compareTo("男")==0 ? 1L : 2L);
        req.setImage(personInfo.getFaceBase());
        CreatePersonResponse resp = coreConfig.getClient().CreatePerson(req);
        if (resp.getFaceRect() == null){
            throw new Exception("新增人员上传错误");
        }else{
            PersonEntity personEntity = new PersonEntity(null, personInfo.getPersonId(), personInfo.getFaceBase());
            personRepository.saveAndFlush(personEntity);
            personInfo.setFaceId(resp.getFaceId());
        }
        return personInfo;
    }

    /**
     * 删除人员
     */
    public String delete(String personId) throws Exception {
        DeletePersonRequest req = new DeletePersonRequest();
        req.setPersonId(personId);
        coreConfig.getClient().DeletePerson(req);
        personRepository.deleteByPersonId(personId);
        return "success";
    }


}
