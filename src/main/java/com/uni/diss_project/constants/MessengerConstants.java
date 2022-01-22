package com.uni.diss_project.constants;

/**
 * Common constants for Messenger
 */
public interface MessengerConstants {

    String SERVICE_NAME = "Messenger";

    String API_PREFIX = "/api";
    String API_VERSION = "/v1";
    String API_BASE = API_PREFIX + API_VERSION;

    String MESSAGES_API = API_BASE + "/messages";

    String USERS_API = API_BASE + "/users";

    String AUTHORIZATION_API = API_BASE + "/auth";

}
