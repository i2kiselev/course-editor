package ru.isu.i2kiselev.courseeditor.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class Question {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;

    private Integer ownerSectionId;

    private String text;

    private Integer orderNumber;

    private Integer points;

    private Boolean isRight;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String questionType;
}
