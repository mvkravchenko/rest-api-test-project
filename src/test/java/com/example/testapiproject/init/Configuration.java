package com.example.testapiproject.init;

/**
 * Конфигурация запуска тестов
 */
public class Configuration {

    static final public String SERVER_HOST = "server.host";
    static final public String SERVER_PORT = "server.port";
    static final public String BASE_PATH = "server.base";

    private final String serverHost;
    private final String serverPort;
    private final String basePath;

    public Configuration(String serverHost, String serverPort, String basePath){
        this.serverHost = serverHost;
        this.serverPort = serverPort;
        this.basePath = basePath;
    }

    public String getServerHost() {
        return serverHost;
    }

    public String getServerPort() {
        return serverPort;
    }

    public String getBasePath() {
        return basePath;
    }

    public String toString() {
        return "server.host = " + this.serverHost +
               "\nserver.port = "+ this.serverPort +
               "\nbasePath = " + this.basePath;
    }
}
