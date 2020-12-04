package ru.isu.i2kiselev.courseeditor.controller;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.isu.i2kiselev.courseeditor.model.Course;
import ru.isu.i2kiselev.courseeditor.model.Section;
import ru.isu.i2kiselev.courseeditor.service.EditorService;

@Controller
@Slf4j
@RequestMapping("/courses/")
public class CourseController {

    @Autowired
    private EditorService editorService;

    @GetMapping("/createCourse")
    public String createCourseForm(Model model){
        model.addAttribute("course", new Course());
        log.info("Returned course creation page");
        return "add-course";
    }

    @PostMapping("/createCourse")
    public String saveSection(@ModelAttribute("course") Course course){
        editorService.createCourse(course);
        log.info("Returned section creation page");
        return "add-course";
    }

    @GetMapping("/editCourse")
    public String editCourse(Model model){
        model.addAttribute("course", new Course());
        log.info("Returned course creation page");
        return "add-course";
    }

    @PostMapping("/editCourse")
    public String editCourse(@ModelAttribute("course") Course course){
        editorService.updateCourse(course);
        log.info("Returned section creation page");
        return "add-course";
    }
}
