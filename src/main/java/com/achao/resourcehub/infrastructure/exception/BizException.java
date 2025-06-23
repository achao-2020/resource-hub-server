package com.achao.resourcehub.infrastructure.exception;

import lombok.Data;

@Data
public class BizException extends RuntimeException {
    private Integer code;
    private String message;
    public BizException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
    public BizException(String message) {
        super(message);
        this.code = 9999;
        this.message = message;
    }
}
