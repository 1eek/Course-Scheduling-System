package com.example.course2.util;

import com.example.course2.pojo.Course;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileUtil {
    public static String filePath = "src/main/resources/test2.json";
    public static void save(List<Course> data){

        ObjectMapper objectMapper = new ObjectMapper();


        try {
            objectMapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, false);
            objectMapper.writeValue(new File(filePath), data);

            System.out.println("数据已成功保存到JSON文件: " + filePath);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
//从json字符串中读取数据
    public static List<Course> read() {

        ObjectMapper objectMapper = new ObjectMapper();
        List<Course> data;
        try {
            data = objectMapper.readValue(new File(filePath), new TypeReference<List<Course>>(){});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}
