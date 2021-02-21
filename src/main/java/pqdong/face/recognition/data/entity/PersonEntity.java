package pqdong.face.recognition.data.entity;

/*
 * 人员具体信息
 * @author pqdong
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "person")
@Entity
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //人员id
    @Column(name = "person_id")
    private String personId;

    //人员头像
    @Column(name = "face_base")
    private String faceBase;

}
