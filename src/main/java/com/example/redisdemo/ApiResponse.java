package com.example.redisdemo;

import lombok.Data;

/**
 * Response
 *
 * @author Chenxing Li
 * @date 2017/11/22 21:17
 */
@Data
public class ApiResponse<T> {

    public static final String OK = "OK";
    public static final String FAIL = "FAIL";

    private String status = OK;
    private T body;

    public ApiResponse() {

    }

    public ApiResponse(T body) {
        this.body = body;
    }

    public ApiResponse(T body, String status) {
        this.body = body;
        this.status = status;
    }
}
