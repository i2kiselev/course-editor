package ru.isu.i2kiselev.courseeditor.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Level {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;

    private Integer sectionId;

    private Integer ownerCourseSectionId;

    private Integer orderNumber;

    private String levelName;

    private Section section;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ArrayList<Level> nestedCourseSections;


}
