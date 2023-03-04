package com.mylaboratory.spring.validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mylaboratory.spring.validation.common.BaseCode;
import com.mylaboratory.spring.validation.common.controller.request.CustomConstraintRequest;
import com.mylaboratory.spring.validation.common.controller.ValidationController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ValidationController.class)
class CustomConstraintRequestTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("리스트에 공백문자가 포함되면 예외가 발생")
    @Test
    public void custom_validation_exception_test1() throws Exception {
        String content = objectMapper.writeValueAsString(new CustomConstraintRequest(List.of("홍 길동", "김춘향"), "김철수"));
        mockMvc.perform(post("/custom-constraint")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("returnMessage").value(BaseCode.ERR_BASIC_ARG_IS_WRONG.message()))
                .andExpect(jsonPath("context").value("nameList[0] : 공백이 포함되어 있습니다."))
                .andDo(print());
    }

    @DisplayName("문자열에 공백문자가 포함되면 예외 발생")
    @Test
    public void custom_validation_exception_test2() throws Exception {
        String content = objectMapper.writeValueAsString(new CustomConstraintRequest(List.of("홍길동", "김춘향"), "김 철수"));
        mockMvc.perform(post("/custom-constraint")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("returnMessage").value(BaseCode.ERR_BASIC_ARG_IS_WRONG.message()))
                .andExpect(jsonPath("context").value("name : 공백이 포함되어 있습니다."))
                .andDo(print());
    }

    @DisplayName("리스트와 문자열 모두 공백문자가 포함되면 예외 발생")
    @Test
    public void custom_validation_exception_test3() throws Exception {
        String content = objectMapper.writeValueAsString(new CustomConstraintRequest(List.of("홍 길동", "김춘향"), "김 철수"));
        mockMvc.perform(post("/custom-constraint")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("returnMessage").value(BaseCode.ERR_BASIC_ARG_IS_WRONG.message()))
                .andDo(print());
    }

    @DisplayName("리스트와 문자열 모두 공백문자가 포함되면 예외 발생")
    @Test
    public void custom_validation_success_test() throws Exception {
        String content = objectMapper.writeValueAsString(new CustomConstraintRequest(List.of("홍길동", "김춘향"), "김철수"));
        mockMvc.perform(post("/custom-constraint")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("returnMessage").value(BaseCode.SUCCESS.message()))
                .andDo(print());
    }

}