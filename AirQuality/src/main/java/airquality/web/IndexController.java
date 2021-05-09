package airquality.web;

import airquality.model.City;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/", "/index"})
public class IndexController {
    @GetMapping
    public String getIndex(Model model) {
        model.addAttribute("city", new City());
        return "index";
    }
}
