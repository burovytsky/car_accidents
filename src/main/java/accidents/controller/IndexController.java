package accidents.controller;

import accidents.service.AccidentCrudService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    private final AccidentCrudService service;


    public IndexController(AccidentCrudService service) {
        this.service = service;
    }
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("accidents", service.getAccidents());
        model.addAttribute("types", service.getTypes());
        return "index";
    }
}
