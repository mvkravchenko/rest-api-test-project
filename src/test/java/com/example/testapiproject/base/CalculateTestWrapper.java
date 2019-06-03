package com.example.testapiproject.base;

import com.example.testapiproject.entities.calculate.CalculateRequest;
import com.example.testapiproject.entities.calculate.CalculateSuccessResponse;
import com.example.testapiproject.entities.common.ApiEndpoints;
import com.example.testapiproject.entities.common.ErrorResponse;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CalculateTestWrapper extends BaseTestWrapper {

    private static final Logger logger = LoggerFactory.getLogger(CalculateTestWrapper.class);

    /**
     * Метод, реализующий общий сценарий отправки запроса и проверки результата вычисления
     * @param leftOperand
     * @param rightOperand
     * @param operation
     * @param result
     * @param responseCode
     */
    protected void runPositiveTestScenario(int leftOperand, int rightOperand, String operation, int result, int responseCode) {

        logger.debug("Формируем тело запроса");
        CalculateRequest requestBody = new CalculateRequest(leftOperand, operation, rightOperand);
        runPositiveTestScenario(requestBody, result, responseCode);
    }

    /**
     * Метод, реализующий общий сценарий отправки запроса и проверки результата вычисления
     * @param requestBody
     * @param result
     * @param responseCode
     */
    protected void runPositiveTestScenario(CalculateRequest requestBody, int result, int responseCode) {

        logger.debug("Делаем запрос");
        Response response = doPostRequest(ApiEndpoints.CALCULATE, requestBody);

        logger.debug("Получаем ответ, проверяем код ответа и сохраняем тело ответа");
        CalculateSuccessResponse calcResponse = response.then().assertThat()
                .statusCode(responseCode)
                .and()
                .extract().body().as(CalculateSuccessResponse.class);

        //проверяем содержание ответа
        assertThat("Запрос вернул неверный результат вычисления", calcResponse.getResult(), is(equalTo(result)));
    }

    protected void runNegativeTestScenario(CalculateRequest requestBody, int responseCode, ErrorResponse errorResponse) {

        logger.debug("Делаем запрос");
        Response response = doPostRequest(ApiEndpoints.CALCULATE, requestBody);

        logger.debug("Получаем ответ, проверяем код ответа и сохраняем тело ответа");
        ErrorResponse actualResponse = response.then().assertThat()
                .statusCode(responseCode)
                .and()
                .extract().body().as(ErrorResponse.class);

        //проверяем содержание ответа
        assertThat("Запрос вернул неверное сообщение об ошибке", actualResponse.getErrorMessage(), is(equalTo(errorResponse.getErrorMessage())));
    }
}
