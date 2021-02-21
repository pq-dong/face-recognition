package pqdong.face.recognition.data.dto;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonList {

    private Long personNum;

    private List<PersonInfo> persons;
}
