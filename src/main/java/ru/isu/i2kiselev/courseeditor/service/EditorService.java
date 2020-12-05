package ru.isu.i2kiselev.courseeditor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.html.HTMLTableCaptionElement;
import ru.isu.i2kiselev.courseeditor.model.Course;
import ru.isu.i2kiselev.courseeditor.model.Section;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class EditorService {

    private final static String REST_API = "http://jur.csscl.ru:4003";

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    public EditorService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
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
            log.debug("Returned course with id "+course.getId());
            return course;
        } catch (JsonProcessingException e) {
            log.warn(e);
            log.debug("Returned empty course");
            return new Course();
        }
    }

    private List<Course> getCourseListFromJson(JsonNode node){
        try {
            List<Course> course = objectMapper.readValue(node.toPrettyString(), objectMapper.getTypeFactory().constructCollectionType(List.class,Course.class));
            log.debug("Returned course list");
            return course;
        } catch (JsonProcessingException e) {
            log.warn(e);
            log.debug("Returned empty course list");
            return new ArrayList<Course>();
        }
    }

    private Section getSectionFromJson(JsonNode node) {
        try {
            Section section = objectMapper.readValue(node.toPrettyString(),Section.class);
            log.debug("Returned course with id "+section.getId());
            return section;
        } catch (JsonProcessingException e) {
            log.warn(e);
            log.debug("Returned empty course");
            return new Section();
        }
    }

    private List<Section> getSectionListFromJson(JsonNode node){
        try {
            List<Section> sections = objectMapper.readValue(node.toPrettyString(), objectMapper.getTypeFactory().constructCollectionType(List.class,Section.class));
            log.debug("Returned section list");
            return sections;
        } catch (JsonProcessingException e) {
            log.warn(e);
            log.debug("Returned empty section list");
            return new ArrayList<Section>();
        }
    }

    private String writeObjectToJson(Object object) {
        try {
            String json = objectMapper.writeValueAsString(object);
            log.debug("Returned object json");
            return json;
        } catch (JsonProcessingException e) {
            log.warn(e);
            log.debug("Returned empty json string");
            return "";
        }
    }

    private HttpEntity<String> getHttpEntityWithJsonHeader(Object body){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        log.debug("Returned HttpEntity with JSON media type");
        return new HttpEntity<>(writeObjectToJson(body), headers);
    }

    public Course getCourseById(Integer id){
        ResponseEntity<String> course = restTemplate.getForEntity(REST_API +"/courses/"+id ,String.class);
        return getCourseFromJson(extractPayloadFromResponse(course));
    }

    public List<Course> getAllCourses(){
        ResponseEntity<String> courses = restTemplate.getForEntity(REST_API+"/courses",String.class);
        return getCourseListFromJson(extractPayloadFromResponse(courses));
    }

    public Section getSectionById(Integer id){
        return getAllSections().stream().filter(x-> x.getId().equals(id)).findFirst().orElse(new Section());
    }

    public List<Section> getAllSections(){
        ResponseEntity<String> sections = restTemplate.getForEntity(REST_API+"/sections",String.class);
        return getSectionListFromJson(extractPayloadFromResponse(sections));
    }

    public Course getCourseWithoutStructureById(Integer id){
        return getAllCourses().stream().filter(x-> x.getId().equals(id)).findFirst().orElse(new Course());
    }
    public String createSection(Section section){
        log.info("Section "+section.getName()+" saved");
        return restTemplate.postForObject(REST_API+"/sections", getHttpEntityWithJsonHeader(section), String.class);
    }

    public void updateSection(Section section){
        log.info("Section "+section.getName()+" updated");
        restTemplate.exchange(REST_API+"/sections/"+section.getId(), HttpMethod.PUT, getHttpEntityWithJsonHeader(section), Void.class);
    }

    public String createCourse(Course course){
        log.info("Course "+course.getName()+" saved");
        return restTemplate.postForObject(REST_API+"/courses",getHttpEntityWithJsonHeader(course), String.class);
    }

    public void updateCourse(Course course){
        log.info("Course "+course.getName()+" updated");
        restTemplate.exchange(REST_API+"/courses/"+course.getId(), HttpMethod.PUT, getHttpEntityWithJsonHeader(course), Void.class);
    }

    public String addSectionToCourse(Course course, Section section, Section parentSection){

        return "";
    }

}
