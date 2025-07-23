package io.github.cfgametech;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.cfgametech.exceptions.Exceptions;

/**
 * Response 是对于 LUKSDK HTTP 请求的通用响应对象
 * @param <T> 响应的数据类型
 */
@Deprecated
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<T> {
    /**
     * 响应码
     */
    @JsonProperty("code")
    private Integer code;

    /**
     * 响应消息
     */
    @JsonProperty("msg")
    private String msg;

    /**
     * 响应数据
     */
    @JsonProperty("data")
    private T data;

    public Response() {}

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return msg;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * @return 是否成功
     */
    public boolean suc() {
        return code == 0;
    }

    public Response<T> withError(Exception err, String... msg) {
        if (err == null) {
            return this;
        }
        if (err instanceof Exceptions.SDKError) {
            Exceptions.SDKError customError = (Exceptions.SDKError) err;
            this.code = customError.getCode();
            this.msg = customError.getMessage();
        } else {
            this.msg = err.getMessage();
        }

        if (msg != null && msg.length > 0) {
            this.msg = String.join(", ", err.getMessage(), String.join(", ", msg));
        }
        if (this.code == 0) {
            this.code = -1;
        }
        return this;
    }

    public Response<T> withData(T data) {
        this.data = data;
        if (this.code == 0) {
            this.msg = "成功";
        }
        return this;
    }

}
