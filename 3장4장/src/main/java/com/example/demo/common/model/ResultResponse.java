package com.example.demo.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultResponse<T> {

	private int status;
    private T payload;
    private Paging paging;
    private BusinessException exception;

    public ResultResponse() {
    }

    @Builder
    public ResultResponse(int status, T payload, Paging paging, BusinessException exception) {
        this.status = status;
        this.payload = payload;
        this.paging = paging;
        this.exception = exception;
    }
}