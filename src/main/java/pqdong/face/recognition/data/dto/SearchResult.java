package pqdong.face.recognition.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchResult {

    private String personId;

    private String faceId;

    private Float score;

    private String personName;


}
