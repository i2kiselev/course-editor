package ru.isu.i2kiselev.courseeditor.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Section {

    private Integer id;

    private Integer owner_course_section_id;

    private Integer order_number;

    private Integer section_id;

    private String name;

    private String content;

    private String level_name;

    private ArrayList<Section> nested_sections;


}
