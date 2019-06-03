package com.example.testapiproject.init;

import com.example.testapiproject.helpers.ConfigFileLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static com.example.testapiproject.init.Configuration.*;

/**
 * Инициализация конфигурации запуска
 */
public class ConfigInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigInitializer.class);

    private ConfigInitializer() {
        //empty
    }

    /**
     * Загрузка параметров конфигурации запуска тестов
     * Считываем параметры из системных настроек. Если они не заданы, то из конфиг файла
     *  <p> Приориеты:
     *  <p> 1. через VM-опции
     *  <p> 2. из конфиг файла
     *
     * @return инициализированная конфигураия в объекте @{@link Configuration}
     */
    public static Configuration loadConfiguration(){
        String ERR_MSG = "В конфигурации запуска должен быть задан параметр '%s'";

        //загружаем конфиги из файла
        Map<String, String> properties = ConfigFileLoader.getConfigProps();

        //считываем конфиги из строки запуска и выбираем, какой конфиг используем в соответствии с приоритетом
        String host = System.getProperty(SERVER_HOST) == null ? properties.get(SERVER_HOST) : System.getProperty(SERVER_HOST);
        String port = System.getProperty(SERVER_PORT) == null ? properties.get(SERVER_PORT) : System.getProperty(SERVER_PORT);
        String basePath = System.getProperty(BASE_PATH) == null ? properties.get(BASE_PATH) : System.getProperty(BASE_PATH);

        if (host == null) {
            throw new IllegalStateException(String.format(ERR_MSG, SERVER_HOST));
        }
        if (port == null) {
            throw new IllegalStateException(String.format(ERR_MSG, SERVER_PORT));
        }
        if (basePath == null) {
            throw new IllegalStateException(String.format(ERR_MSG, BASE_PATH));
        }

        Configuration configuration = new Configuration(host, port, basePath);
        LOGGER.debug("Итоговая конфигурация для тестов:\n" + configuration.toString() );
        return configuration;
    }
}
