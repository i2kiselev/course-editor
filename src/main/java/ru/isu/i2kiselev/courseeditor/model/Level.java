package ru.isu.i2kiselev.courseeditor.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Level {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;

    private Integer section_id;

    private Integer owner_course_section_id;

    private Integer order_number;

    private String level_name;

    private Section section;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ArrayList<Level> nested_course_sections;


}
