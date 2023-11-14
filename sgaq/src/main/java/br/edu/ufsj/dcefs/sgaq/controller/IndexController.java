package br.edu.ufsj.dcefs.sgaq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @GetMapping("/")
    public ModelAndView getListIndex2(){

        ModelAndView mv = new ModelAndView("index2");

        return mv;
    }
    @GetMapping("/login")
    public ModelAndView getListLogin(){

        ModelAndView mv = new ModelAndView("login");

        return mv;
    }
    @GetMapping("/profile")
    public ModelAndView getListProfile(){

        ModelAndView mv = new ModelAndView("profile");

        return mv;
    }
    @GetMapping("/index")
    public ModelAndView getListIndex(){

        ModelAndView mv = new ModelAndView("index");

        return mv;
    }
    @GetMapping("/cadastrousuario")
    public ModelAndView getListCadastroUser(){

        ModelAndView mv = new ModelAndView("cadastroUser");

        return mv;
    }
    @GetMapping("/job")
    public ModelAndView getListJob(){

        ModelAndView mv = new ModelAndView("job");

        return mv;
    }
    @GetMapping("/jobedit")
    public ModelAndView getListJobEdit(){

        ModelAndView mv = new ModelAndView("job-edit");

        return mv;
    }
}
