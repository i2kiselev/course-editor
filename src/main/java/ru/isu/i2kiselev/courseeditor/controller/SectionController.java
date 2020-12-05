package ru.isu.i2kiselev.courseeditor.controller;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.isu.i2kiselev.courseeditor.model.Section;
import ru.isu.i2kiselev.courseeditor.service.EditorService;

@Controller
@Slf4j
@RequestMapping("/sections/")
public class SectionController {

    private final EditorService editorService;

    public SectionController(EditorService editorService) {
        this.editorService = editorService;
    }

    @GetMapping("/createSection")
    public String createSectionForm(Model model){
        model.addAttribute("section", new Section());
        log.info("Returned section creation page");
        return "add-section";
    }

    @PostMapping("/createSection")
    public String saveSection(@ModelAttribute("section") Section section){
        editorService.createSection(section);
        log.info("Returned section creation page");
        return "add-section";
    }

    @GetMapping("/{sectionId}")
    public String createSectionForm(@PathVariable("sectionId") Integer sectionId, Model model){
        model.addAttribute("section", editorService.getSectionById(sectionId));
        log.info("Returned editing page for section with id "+sectionId);
        return "edit-section";
    }

    @PostMapping("/{sectionId}")
    public String finishSectionEditing(@ModelAttribute("section") Section section, @PathVariable("sectionId") Integer sectionId, Model model){
        editorService.updateSection(section);
        return "redirect:/sections/allSections";
    }

    @GetMapping("/allSections")
    public String getAllSections(Model model){
        model.addAttribute("sections", editorService.getAllSections());
        log.info("Returned sections page");
        return "sections";
    }
}
