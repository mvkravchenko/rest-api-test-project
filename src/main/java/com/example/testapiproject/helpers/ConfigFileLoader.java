package com.example.testapiproject.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Класс предоставляет статические методы для загрузки конфигурации запуска тестов из конфиг файла
 */
public class ConfigFileLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigFileLoader.class);

    private static final String STAND_NAME = "stand";
    private static final String CONFIG_FILE = "config";

    private static Map<String, String> configProps;

    private static final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    private ConfigFileLoader() {
        //empty
    }

    /**
     * Загрузка конфига из файла
     * Ищется конфиг файл с именем config[STAND_NAME].properties
     *
     * @return объект {@link Properties}
     */
    private static Properties loadPropertiesFromFile() {

        LOGGER.debug("Считываем опцию \"" + STAND_NAME + "\"");
        String standName = System.getProperty(STAND_NAME, "");
        LOGGER.debug(STAND_NAME + " = " + standName);

        String configFileName = CONFIG_FILE + standName + ".properties";
        LOGGER.debug("Ищем конфигурационный файл " + configFileName);
        InputStream input = classLoader.getResourceAsStream(configFileName);

        Properties properties = new Properties();
        try {

            LOGGER.debug("Сохраняем конфигурацию из файла");
            properties.load(input);
            LOGGER.debug("Загружена конфигурация из файла:\n" + printConfig(properties));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

    /**
     * Преобразование пропертис из объекта {@link Properties} пары key-value
     *
     * @param properties пропертис для преобразования
     * @return мапа ключей-значений пропертис
     */
    private static Map<String, String> propsToMap(Properties properties) {
        Map<String, String> propsMap = new HashMap<>();
        properties.stringPropertyNames().forEach(prop -> propsMap.put(prop, properties.getProperty(prop)));
        return propsMap;
    }

    /**
     * Статический метод для получения настроек запуска тестов с учетом приориетов.
     *
     * @return пропертис в виде мапы ключей-значений
     */
    public static Map<String, String> getConfigProps() {
        if (configProps == null) {
            Properties properties = loadPropertiesFromFile();
            configProps = propsToMap(properties);
        }
        return configProps;
    }

    /**
     * Печать файла конфигурации в строку
     * @param properties
     * @return
     */
    private static String printConfig(Properties properties) {
        Map<String, String> configMap = propsToMap(properties);
        StringBuilder builder = new StringBuilder();
        configMap.keySet().forEach(p -> builder.append(p + "=" + configMap.get(p) + "\n"));
        return builder.toString();
    }


}
