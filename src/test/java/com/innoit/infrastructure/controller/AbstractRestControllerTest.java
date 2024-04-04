package com.innoit.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.innoit.infrastructure.BaseTest;

abstract class AbstractRestControllerTest extends BaseTest {


    static String asJsonString(final Object obj) {
        try {
            ObjectMapper om = new ObjectMapper();
             om.registerModule(new JavaTimeModule());
            return om.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
