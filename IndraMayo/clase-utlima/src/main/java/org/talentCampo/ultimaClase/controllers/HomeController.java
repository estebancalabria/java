package org.talentCampo.ultimaClase.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {



    //Esta es la forma que a mi me gusta
    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView result = new ModelAndView();
        result.addObject("titulo", "Bienvenidos mi aplicacion");
        result.addObject("autor", "Esteban Calabria");
        result.setViewName("index");
        return result;
    }

    //La otra es devolver directamente un string con el nombre de la vista
    @GetMapping("/saludo")
    public String saludo(){
        return "saludo";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
