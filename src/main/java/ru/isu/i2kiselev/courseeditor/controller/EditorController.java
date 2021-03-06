package ru.isu.i2kiselev.courseeditor.controller;

        import lombok.extern.log4j.Log4j2;
        import lombok.extern.slf4j.Slf4j;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.ServletRequestBindingException;
        import org.springframework.web.bind.annotation.*;
        import ru.isu.i2kiselev.courseeditor.model.Course;
        import ru.isu.i2kiselev.courseeditor.model.Section;
        import ru.isu.i2kiselev.courseeditor.service.EditorService;

        import java.util.List;



@Slf4j
@Controller
public class EditorController {

    @Autowired
    private EditorService editorService;

    /*@GetMapping("/getTestCourse")
    private Course getTestCourse() {
        return editorService.getCourseById(1);
    }

    @GetMapping("/getTestSection")
    private List<Section> getTestSection() {
        return editorService.getAllSections();
    }

    @GetMapping("/createSection")
    private String createSection() {
        Section section = new Section();
        section.setContent("Fancy markdown content 7");
        section.setName("Section test 7");
        return editorService.createSection(section);
    }

    @GetMapping("/updateSection")
    private String updateSection() {
        Section section = new Section();
        section.setId(9);
        section.setContent("Updated content");
        section.setName("Section test 1488");
        editorService.updateSection(section);
        return "";
    }*/



}