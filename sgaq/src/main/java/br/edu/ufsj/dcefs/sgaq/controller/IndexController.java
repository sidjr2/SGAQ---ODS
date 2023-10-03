package br.edu.ufsj.dcefs.sgaq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @GetMapping("/")
    public ModelAndView getListIdex(){

        ModelAndView mv = new ModelAndView("index");

        return mv;
    }
    @GetMapping("/login")
    public ModelAndView getListLogin(){

        ModelAndView mv = new ModelAndView("login");

        return mv;
    }
    @GetMapping("/cadastroreserva")
    public ModelAndView getListCadastroReserva(){

        ModelAndView mv = new ModelAndView("cadastroReserva");

        return mv;
    }
    @GetMapping("/cadastrousuario")
    public ModelAndView getListCadastroUser(){

        ModelAndView mv = new ModelAndView("cadastroUser");

        return mv;
    }
}
