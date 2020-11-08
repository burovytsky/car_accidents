package accidents.controller;

import accidents.model.Accident;
import accidents.service.AccidentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccidentController {
    private final AccidentService service;

    public AccidentController(AccidentService service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("accidentTypes", service.getAccidentTypes());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        service.createAccident(accident);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accidentTypes", service.getAccidentTypes());
        model.addAttribute("accident", service.findAccidentById(id));
        return "accident/edit";
    }
}
