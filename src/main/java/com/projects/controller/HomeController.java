package com.projects.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Oleg Cherniak
 * @version 1.0
 * @since 19.09.2016
 */
@RequestMapping(value = "/")
@Controller
public class HomeController {
    @RequestMapping(method = RequestMethod.GET)

    public String showIndex() {
        return "index";
    }
}
