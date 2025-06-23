package com.achao.resourcehub.infrastructure.model.res;

import lombok.Data;

/**
 * 响应结果
 */
@Data
public class ResResult<T> {
    private Integer code;
    private String message;
    private T data;
    public static <T> ResResult<T> success(T data) {
        ResResult<T> result = new ResResult<>();
        result.setCode(200);
        result.setMessage("success");
        result.setData(data);
        return result;
    }

    public static <T> ResResult<T> error(Integer code, String message) {
        ResResult<T> result = new ResResult<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}
