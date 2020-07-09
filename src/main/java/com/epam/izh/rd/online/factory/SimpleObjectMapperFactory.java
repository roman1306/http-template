package com.epam.izh.rd.online.factory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SimpleObjectMapperFactory implements ObjectMapperFactory {

    @Override
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }
}
