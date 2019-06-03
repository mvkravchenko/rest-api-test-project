package com.example.testapiproject.base;


import com.example.testapiproject.entities.common.ApiEndpoints;
import com.example.testapiproject.init.RestAssuredInitializer;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.with;

/**
 *
 */
public class BaseTestWrapper {

    private static final Logger logger = LoggerFactory.getLogger(BaseTestWrapper.class);

    @BeforeAll
    public static void setup() {
        logger.debug("Начало инициализации параметров запуска тестов");
        RestAssuredInitializer.init();
        logger.debug("Завершена инициализация параметров запуска тестов");
    }

    @BeforeEach
    public void logBefore(TestInfo info){
        logger.debug("Начало выполнения теста: \"" + info.getDisplayName() + "\"");
    }

    @AfterEach
    public void logAfter(){
        logger.debug("Завершено выполнения теста");
    }

    /**
     * Метод, формирующий POST-запрос через RestAssured
     * @param apiName api endpoint, см @{@link ApiEndpoints}
     * @param body тело запроса
     * @return @{@link Response}
     */
    protected static Response doPostRequest(ApiEndpoints apiName, Object body) {
        logger.debug("Отправляем POST-запрос на endpoint " + apiName.getEndpoint());
        return  with()
                .contentType("application/json")
                .body(body)
                .post(apiName.getEndpoint());

    }

    /**
     * Метод, формирующий GET-запрос через RestAssured
     * @param path
     * @return
     */
    protected static Response doGetRequest(ApiEndpoints path) {
        return get(path.getEndpoint());
//        throw new UnsupportedOperationException("Не реализовано");
    }
}
