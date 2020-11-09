package accidents.controller;

import accidents.model.Accident;
import accidents.model.Rule;
import accidents.service.AccidentJdbcService;
import accidents.service.AccidentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Controller
public class AccidentController {
    private final AccidentService service;

    public AccidentController(AccidentService service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("accidentTypes", service.getAccidentTypes());
        model.addAttribute("rules", service.getRules());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest request) {
        service.createAccident(accident, request.getParameterValues("rIds"));
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accidentTypes", service.getAccidentTypes());
        model.addAttribute("accident", service.findAccidentById(id));
        model.addAttribute("rules", service.getRules());
        return "accident/edit";
    }
}
