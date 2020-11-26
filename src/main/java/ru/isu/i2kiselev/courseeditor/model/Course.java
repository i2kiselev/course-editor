package ru.isu.i2kiselev.courseeditor.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Course {

    private Integer id;

    private String name;

    private String description;

    private ArrayList<Level> structure;

}
