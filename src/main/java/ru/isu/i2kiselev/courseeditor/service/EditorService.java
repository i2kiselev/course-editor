package ru.isu.i2kiselev.courseeditor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.isu.i2kiselev.courseeditor.model.Course;
import ru.isu.i2kiselev.courseeditor.model.Section;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class EditorService {

    private final static String REST_API = "http://jur.csscl.ru:4003";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

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

    private List<Course> getCourseListFromJson(JsonNode node){
        try {
            List<Course> course = objectMapper.readValue(node.toPrettyString(), objectMapper.getTypeFactory().constructCollectionType(List.class,Course.class));
            log.info("Returned course list");
            return course;
        } catch (JsonProcessingException e) {
            log.warn(e);
            log.info("Returned empty course list");
            return new ArrayList<Course>();
        }
    }

    private Section getSectionFromJson(JsonNode node) {
        try {
            Section section = objectMapper.readValue(node.toPrettyString(),Section.class);
            log.info("Returned course with id"+section.getId());
            return section;
        } catch (JsonProcessingException e) {
            log.warn(e);
            log.info("Returned empty course");
            return new Section();
        }
    }

    private List<Section> getSectionListFromJson(JsonNode node){
        try {
            List<Section> sections = objectMapper.readValue(node.toPrettyString(), objectMapper.getTypeFactory().constructCollectionType(List.class,Section.class));
            log.info("Returned section list");
            return sections;
        } catch (JsonProcessingException e) {
            log.warn(e);
            log.info("Returned empty section list");
            return new ArrayList<Section>();
        }
    }

    public String writeObjectToJson(Object object) {
        try {
            String json = objectMapper.writeValueAsString(object);
            log.debug("Returned object json");
            return json;
        } catch (JsonProcessingException e) {
            log.warn(e);
            log.info("Returned empty json string");
            return "";
        }
    }
    public Course getCourseById(Integer id){
        ResponseEntity<String> course = restTemplate.getForEntity(REST_API +"/courses/"+id ,String.class);
        return getCourseFromJson(extractPayloadFromResponse(course));
    }

    public List<Course> getAllCourses(){
        ResponseEntity<String> courses = restTemplate.getForEntity(REST_API+"/courses",String.class);
        return getCourseListFromJson(extractPayloadFromResponse(courses));
    }

    /*public Section getSectionById(Integer id){
        ResponseEntity<String> section = restTemplate.getForEntity(REST_API+"/sections/"+id, String.class);
        return getSectionFromJson(extractPayloadFromResponse(section));
    }*/

    public List<Section> getAllSections(){
        ResponseEntity<String> sections = restTemplate.getForEntity(REST_API+"/sections",String.class);
        return getSectionListFromJson(extractPayloadFromResponse(sections));
    }

    public String createSection(Section section){
        HttpEntity<String> newSection = new HttpEntity<>(writeObjectToJson(section));
        return restTemplate.postForObject(REST_API+"/sections", newSection, String.class);
    }
}
