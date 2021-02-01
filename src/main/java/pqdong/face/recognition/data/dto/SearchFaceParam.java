package pqdong.face.recognition.data.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchFaceParam {

    String groupIds;

    String image;

    long maxFaceNum;

    long minFaceSize;

    long maxPersonNum;
}
