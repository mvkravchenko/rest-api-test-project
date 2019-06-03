package com.example.testapiproject.tests;

import com.example.testapiproject.base.CalculateTestWrapper;
import com.example.testapiproject.cases.CalculateNegativeDataProvider;
import com.example.testapiproject.entities.calculate.CalculateRequest;
import com.example.testapiproject.entities.calculate.Operations;
import com.example.testapiproject.entities.common.ApiEndpoints;
import com.example.testapiproject.entities.common.ErrorResponse;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;

@Feature("/calculate")
class CalculateTests extends CalculateTestWrapper {

    @ParameterizedTest(name = "{0}")
    @CsvFileSource(resources = "/calculate/calculateAdditionCases.csv", numLinesToSkip = 1, delimiter = ',')
    @Story("Сложение")
    void additionTests(String description, int leftOperand, int rightOperand, int result, int responseCode) {
        runPositiveTestScenario(leftOperand, rightOperand, Operations.PLUS, result, responseCode);
    }

    @ParameterizedTest(name = "{0}")
    @CsvFileSource(resources = "/calculate/calculateSubtractionCases.csv", numLinesToSkip = 1, delimiter = ',')
    @Story("Вычитание")
    void subtractionTests(String description, int leftOperand, int rightOperand, int result, int responseCode) {
        runPositiveTestScenario(leftOperand, rightOperand, Operations.MINUS, result, responseCode);
    }

    @ParameterizedTest(name = "{0}")
    @CsvFileSource(resources = "/calculate/calculateMultiplyCases.csv", numLinesToSkip = 1, delimiter = ',')
    @Story("Умножение")
    void multiplyTests(String description, int leftOperand, int rightOperand, int result, int responseCode) {
        runPositiveTestScenario(leftOperand, rightOperand, Operations.MULTIPLY, result, responseCode);
    }

    @ParameterizedTest(name = "{0}")
    @CsvFileSource(resources = "/calculate/calculateDivideCases.csv", numLinesToSkip = 1, delimiter = ',')
    @Story("Деление")
    void divideTests(String description, int leftOperand, int rightOperand, int result, int responseCode) {
        runPositiveTestScenario(leftOperand, rightOperand, Operations.DIVIDE, result, responseCode);
    }

    @ParameterizedTest(name = "{0}")
    @ArgumentsSource(CalculateNegativeDataProvider.class)
    @Story("Негативные кейсы")
    void negativeTests(String description, CalculateRequest requestBody, int responseCode, ErrorResponse errorResponse) {
        runNegativeTestScenario(requestBody, responseCode, errorResponse);
    }
}
