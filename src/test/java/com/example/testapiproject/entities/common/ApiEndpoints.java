package com.example.testapiproject.entities.common;

/**
 * API эндпойнты относительно basePath
 */
public enum ApiEndpoints {

    CALCULATE("/calculate");

    private String endpoint;

    public String getEndpoint() {
        return endpoint;
    }

    ApiEndpoints(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public String toString() {
        return "API name = '" + endpoint + "\'";
    }
}
