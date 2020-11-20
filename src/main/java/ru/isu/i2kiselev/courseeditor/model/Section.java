package ru.isu.i2kiselev.courseeditor.model;

import java.util.ArrayList;

public class Section {

    private Integer id;

    private Integer owner_id;

    private Integer order_number;

    private Integer section_id;

    private String name;

    private String content;

    private String level_name;

    private ArrayList<Section> nested_sections;


}
