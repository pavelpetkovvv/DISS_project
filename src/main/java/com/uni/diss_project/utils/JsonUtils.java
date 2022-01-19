package com.uni.diss_project.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    /**
     * This method converts a Java object to a string
     * @param object The object that needs to be converted to string
     * @param <T> The class type
     * @return {@link String}
     */
    public static <T> String convertObjectToString(T object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

}
