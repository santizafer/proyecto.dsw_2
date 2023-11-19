package proyecto.daw_1.proyecto.daw_1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/admin")
    public String index(Model model) {
        return "admin";
    }
}
