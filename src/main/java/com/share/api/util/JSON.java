package com.share.api.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author：weiqiming
 * @Description：JSON 转换工具
 * @Date：2020/5/7 14:09
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class JSON {

    private final ObjectMapper objectMapper;

    public String toJSON(Object jsonObj) {
        try {
            return objectMapper.writeValueAsString(jsonObj);
        } catch (JsonProcessingException e) {
            log.error("JSON 转换异常 转换异常的数据类型: " + jsonObj.getClass().getName(), e);
        }
        return jsonObj.toString();
    }


    public Map toMap(String json) {
        try {
            return objectMapper.readValue(json, Map.class);
        } catch (IOException e) {
            log.error("JSON 转换异常", e);
        }
        return null;
    }

    public List<Map<String, String>> toListMapStr(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<List<Map<String, String>>>() {
            });
        } catch (IOException e) {
            log.error("JSON 转换异常", e);
        }
        return null;
    }


    public List<String> toListString(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<List<String>>() {
            });
        } catch (IOException e) {
            log.error("JSON 转换异常", e);
        }
        return null;
    }
}
