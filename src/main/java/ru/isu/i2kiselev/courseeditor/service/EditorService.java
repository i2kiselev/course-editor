package ru.isu.i2kiselev.courseeditor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.isu.i2kiselev.courseeditor.model.Course;

@Service
@Log4j2
public class EditorService {

    private final static String REST_API = "http://jur.csscl.ru:4003";

    @Autowired
    private RestTemplate restTemplate ;

    @Autowired
    private ObjectMapper objectMapper;

    public Course getCourseById(Integer id){
        ResponseEntity<String> course = restTemplate.getForEntity(REST_API +"/courses/"+id ,String.class);
        JsonNode payload = extractPayloadFromResponse(course);
        return getCourseFromJson(payload);
    }

    private JsonNode extractPayloadFromResponse(ResponseEntity<String> responseEntity) {
        try {
            return objectMapper.readTree(responseEntity.getBody()).path("payload");
        } catch (JsonProcessingException e) {
            log.warn(e);
            return objectMapper.createObjectNode();
        }
    }

    private Course getCourseFromJson(JsonNode node){
        try {
            Course course = objectMapper.readValue(node.toPrettyString(),Course.class);
            log.info("Returned course with id"+course.getId());
            return course;
        } catch (JsonProcessingException e) {
            log.warn(e);
            log.info("Returned empty course");
            return new Course();
        }
    }

}
