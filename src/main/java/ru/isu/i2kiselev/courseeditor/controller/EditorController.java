package ru.isu.i2kiselev.courseeditor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.isu.i2kiselev.courseeditor.model.Course;
import ru.isu.i2kiselev.courseeditor.service.EditorService;


@RestController
public class EditorController {

    @Autowired
    private EditorService editorService;

    @GetMapping("/test")
    private Course getTestCourse() {
            return editorService.getCourseById(1);
    }

}
