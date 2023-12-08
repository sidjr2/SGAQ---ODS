package br.edu.ufsj.dcomp.sgaq.controller;


import br.edu.ufsj.dcomp.sgaq.exception.ServiceExc;
import br.edu.ufsj.dcomp.sgaq.model.Aluno;
import br.edu.ufsj.dcomp.sgaq.enums.TipoUsuario;
import br.edu.ufsj.dcomp.sgaq.model.Usuario;
import br.edu.ufsj.dcomp.sgaq.service.ServiceUsuario;
import br.edu.ufsj.dcomp.sgaq.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@Controller
public class UsuarioController {

    @Autowired
    private ServiceUsuario serviceUsuario;

    @GetMapping("/")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login/login");
        modelAndView.addObject("usuario", new Usuario());
        return modelAndView;
    }

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home/index");
        modelAndView.addObject("aluno", new Aluno());
        return modelAndView;
    }

    @GetMapping("/cadastro")
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("usuario", new Usuario());
         modelAndView.addObject("tipoUsuario", TipoUsuario.values());
        modelAndView.setViewName("login/cadastro");
        return modelAndView;
    }

    @PostMapping("/salvarUsuario")
    public ModelAndView cadastrar(Usuario usuario) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        serviceUsuario.salvarUsuario(usuario);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid Usuario usuario, BindingResult br,
                              HttpSession session) throws NoSuchAlgorithmException, ServiceExc {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("usuario", new Usuario());
        if(br.hasErrors()) {
            modelAndView.setViewName("login/login");
        }

        Usuario userLogin = serviceUsuario.loginUser(usuario.getUser(), Util.md5(usuario.getSenha()));
        if(userLogin == null) {
            modelAndView.addObject("msg","Usuario não encontrado. Tente novamente");
        } else {
            session.setAttribute("usuarioLogado", userLogin);
            return index();
        }

        return modelAndView;
    }

    @PostMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        return login();
    }
}
