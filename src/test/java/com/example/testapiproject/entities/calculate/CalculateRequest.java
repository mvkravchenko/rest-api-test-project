package com.example.testapiproject.entities.calculate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Тело запроса /calculate
 */
public class CalculateRequest {

    private final Object leftOperand;
    private final String operation;
    private final Object rightOperand;

    /**
     *
     *
     * @param leftOperand  левый операнд. Тип @{@link Object}, чтобы использовать и в негативных тестах
     * @param operation    операция
     * @param rightOperand правый операнд.  Тип @{@link Object}, чтобы использовать и в негативных тестах
     */
    public CalculateRequest(Object leftOperand, String  operation, Object rightOperand) {
        this.leftOperand = leftOperand;
        this.operation = operation;
        this.rightOperand = rightOperand;

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("left_operand")
    private Object getLeftOperand() {
        return leftOperand;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("operation")
    private String  getOperation() {
        return operation;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("right_operand")
    private Object getRightOperand() {
        return rightOperand;
    }

    public String toString() {
        return this.getLeftOperand() + " " + this.getOperation() + " " + this.getRightOperand();
    }
}
