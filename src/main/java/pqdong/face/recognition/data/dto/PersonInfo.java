package pqdong.face.recognition.data.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonInfo {

    /**
     * 人员名称
     */
    private String personName;

    /**
     * 人员Id
     */
    private String personId;

    /**
     * 人员性别
     */
    private String gender;

    /**
     * 人员描述字段内容
     */
    private String personExDescription;

    /**
     * 包含的人脸照片列表
     */
    private String faceId;

    private String faceUrl;

    private String faceBase;

    private Long creationTimestamp;

    private Float score;

}
