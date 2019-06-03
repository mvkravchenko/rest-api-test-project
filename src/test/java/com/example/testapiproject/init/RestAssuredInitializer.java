package com.example.testapiproject.init;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Инициализация RestAssured
 */
public class RestAssuredInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestAssuredInitializer.class);

    /**
     * Инициализация основных параметров RestAssured параметрами из конфига или VM-опций:
     * <p> baseURI
     * <p> port
     * <p> base
     * <p> Включаем логирование запрооов/ответов в случае неудачи
     * <p> Добавляем @{@link AllureRestAssured} фильтр, чтобы в Allure-отчет приаттачились логи запросов/ответов
     */
    public static void init() {

        Configuration configuration = ConfigInitializer.loadConfiguration();

        LOGGER.debug("Начало инициализации RestAssured");
        RestAssured.baseURI = configuration.getServerHost();
        RestAssured.port = Integer.valueOf(configuration.getServerPort());
        RestAssured.basePath = configuration.getBasePath();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        //добавляем фильтр, чтобы в отчетах Allure были аттачменты с http запросами/ответами
        RestAssured.filters(new AllureRestAssured());
        LOGGER.debug("Инициализация RestAssured выполнена успешно");
    }
}
