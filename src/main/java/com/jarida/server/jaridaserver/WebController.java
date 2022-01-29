package com.jarida.server.jaridaserver;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/swagger/api")
@Validated
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class WebController {

    @GetMapping("")
    public ModelAndView redirectWithUsingRedirectPrefix() {
        return new ModelAndView("redirect:/swagger-ui/index.html");
    }
}
