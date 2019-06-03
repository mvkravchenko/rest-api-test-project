package com.example.testapiproject.entities.common;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Тело ответа в случае ошибочного кода ответа
 */
public class ErrorResponse {

    /**
     * Сообщение об ошибке
     */
    private String errorMessage;

    private ErrorResponse() {
        //empty
    }

    public ErrorResponse(String error) {
        this.errorMessage = error;
    }

    @JsonProperty("error")
    public String getErrorMessage() {
        return errorMessage;
    }

    public String toString() {
        return this.getErrorMessage();
    }
}
