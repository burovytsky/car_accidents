package accidents.controller;

import accidents.model.Accident;
import accidents.repository.AccidentRepository;
import accidents.service.AccidentCrudService;
import accidents.service.AccidentHibernateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccidentController {
    private final AccidentCrudService service;

    public AccidentController(AccidentCrudService service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("accidentTypes", service.getTypes());
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
        model.addAttribute("accidentTypes", service.getTypes());
        model.addAttribute("accident", service.findAccidentById(id));
        model.addAttribute("rules", service.getRules());
        return "accident/edit";
    }
}
