package com.dtreel.sanctuary_shop_manager.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author DtreeL
 * 携带任意对象和状态给予前端数据的对象
 */
@Data
@NoArgsConstructor
public class ResponseVO {
    private StatusCode statusCode;
    private String message;
    private Object object;

    ResponseVO(StatusCode statusCode, String message) {
        this.message = message;
        this.statusCode = statusCode;
    }

    ResponseVO(StatusCode statusCode, String message, Object object) {
        this.message = message;
        this.object = object;
        this.statusCode = statusCode;
    }

    public static ResponseVO success(StatusCode statusCode, String message, Object object) {
        return new ResponseVO(statusCode, message, object);
    }

    public static ResponseVO success(StatusCode statusCode, String message) {
        return new ResponseVO(statusCode, message);
    }

    public static ResponseVO error(StatusCode statusCode, String message) {
        return new ResponseVO(statusCode, message);
    }
}
