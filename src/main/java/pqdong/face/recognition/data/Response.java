package pqdong.face.recognition.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response
 *
 * @author pengqd
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("接口响应")
public class Response<T> {


    @ApiModelProperty("成功状态码")
    public static final int CODE_SUCCESS = 0;

    @ApiModelProperty("错误状态码")
    public static final int NEED_LOGIN = 1;

    @ApiModelProperty("错误状态码")
    public static final int CODE_ERROR = -1;

    @ApiModelProperty("错误信息")
    public static final String MESSAGE_SERVER_ERROR = "error";

    @ApiModelProperty("错误信息")
    public static final String MESSAGE_NEED_LOGIN = "needLogin";

    @ApiModelProperty("成功信息")
    public static final String MESSAGE_SERVER_SUCCESS = "success";

    @ApiModelProperty("响应码")
    public int code;

    @ApiModelProperty("简要信息")
    public String msg;

    @ApiModelProperty("简要信息")
    public String description;

    @ApiModelProperty("数据体")
    public T data;

    public static <T> Response<T> success(T data) {
        return new Response<>(CODE_SUCCESS, MESSAGE_SERVER_SUCCESS, null, data);
    }


    public static Response<Void> fail(Exception e) {
        return new Response<>(CODE_ERROR, MESSAGE_SERVER_ERROR, e.toString(), null);
    }

    public static Response<Void> fail(String description) {
        return new Response<>(CODE_ERROR, MESSAGE_SERVER_ERROR, description, null);
    }

    public static Response<Void> needLogin() {
        return new Response<>(NEED_LOGIN, MESSAGE_NEED_LOGIN , MESSAGE_NEED_LOGIN, null);
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel("响应list")
    public static class ResponseList<U> {

        @ApiModelProperty("记录数")
        public long total;

        @ApiModelProperty("数据list")
        public List<U> dataList;
    }
}