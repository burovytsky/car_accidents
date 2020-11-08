package accidents.controller;


import accidents.service.AccidentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    private final AccidentService service;

    public IndexController(AccidentService service) {
        this.service = service;
    }
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", service.getAccidents());
        model.addAttribute("types", service.getAccidentTypes());
        return "index";
    }
}
