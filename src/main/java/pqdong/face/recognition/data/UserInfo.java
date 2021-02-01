package pqdong.face.recognition.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserInfo
 *
 * @author pengqd
 * @since 2019/09/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "用户信息")
public class UserInfo {
    @ApiModelProperty("用户id")
    private String userId;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("头像")
    private String image;
    @ApiModelProperty("手机号")
    private String mobile;
}
