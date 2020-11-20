package ru.isu.i2kiselev.courseeditor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladsch.flexmark.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.web.client.RestTemplateBuilderConfigurer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.isu.i2kiselev.courseeditor.model.Course;

@Service
public class EditorService {

    private final static String API = "http://jur.csscl.ru:4003";

    @Autowired
    private RestTemplate restTemplate ;

    private Parser parser = Parser.builder().build();

    public String testCourse(){
        ResponseEntity<String> course = restTemplate.getForEntity(API+"/courses/1",String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = null;
        try {
            root = mapper.readTree(course.getBody());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        JsonNode payload = root.path("payload");
        return payload.asText();
    }






}
