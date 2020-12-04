package ru.isu.i2kiselev.courseeditor.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Course {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;

    private String name;

    private String description;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ArrayList<Level> structure;

}
