package ohtu.kevatpaiva.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author danielli
 */

@Controller
@RequestMapping("/")
public class HelloController {
 
    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {

        model.addAttribute("message", "(I am spring)");
        return "hello";
    }
 
}