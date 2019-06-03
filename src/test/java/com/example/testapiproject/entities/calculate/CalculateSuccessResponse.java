package com.example.testapiproject.entities.calculate;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Тело ответа запроса /calculate в случае успешного кода ответа
 */
public class CalculateSuccessResponse {

    private int result;

    private CalculateSuccessResponse() {
        //empty
    }

    public CalculateSuccessResponse(int result) {
        this.result = result;
    }

    @JsonProperty("result")
    public int getResult() {
        return result;
    }

    public String toString() {
        return String.valueOf(this.getResult());
    }
}
