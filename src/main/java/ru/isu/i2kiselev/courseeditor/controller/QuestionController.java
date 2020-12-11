package ru.isu.i2kiselev.courseeditor.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.isu.i2kiselev.courseeditor.model.Question;
import ru.isu.i2kiselev.courseeditor.service.EditorService;

@Controller
@Log4j2
@RequestMapping("/questions/")
public class QuestionController {

    @Autowired
    EditorService editorService;

    @GetMapping("/{sectionId}/questions")
    public String getAllQuestionsBySectionId(@PathVariable("sectionId") Integer sectionId, Model model) {
        model.addAttribute("sectionId", sectionId);
        model.addAttribute("questions", editorService.getAllQuestionsBySectionId(sectionId));
        return "questions";
    }

    @GetMapping("/{sectionId}/createQuestion")
    public String createQuestion(@PathVariable("sectionId") Integer sectionId, Model model) {
        model.addAttribute("question", new Question());
        model.addAttribute("sectionId", sectionId);
        log.info("Returned question creation page");
        return "add-question";
    }

    @PostMapping("/{sectionId}/createQuestion")
    public String saveQuestion(@PathVariable("sectionId") Integer sectionId, @ModelAttribute("question") Question question) {
        editorService.createQuestion(sectionId, question);
        log.info("Returned section creation page");
        return "add-question";
    }
}
