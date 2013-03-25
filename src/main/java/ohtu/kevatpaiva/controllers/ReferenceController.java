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
@RequestMapping("/reference")
public class ReferenceController {
 
    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {

        model.addAttribute("message", "(I am spring)");
        return "form-article";
    }
 
}