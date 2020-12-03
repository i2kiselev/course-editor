package ru.isu.i2kiselev.courseeditor.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class Section {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;

    private String name;

    private String content;

}
