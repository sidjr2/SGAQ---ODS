package br.edu.ufsj.dcomp.sgaq.controller;


import br.edu.ufsj.dcomp.sgaq.model.Quadra;
import br.edu.ufsj.dcomp.sgaq.repository.QuadraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/quadra") // Adicionando um mapeamento de nível de classe
public class QuadraController {

    @Autowired
    private QuadraRepository quadraRepository;

    @GetMapping("/inserirQuadras")
    public ModelAndView insertQuadras(Quadra quadra) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Quadra/formQuadra");
        modelAndView.addObject("quadra", new Quadra());
        return modelAndView;
    }

    @PostMapping("InsertQuadras")
    public ModelAndView inserirQuadra(@Valid Quadra quadra, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if(bindingResult.hasErrors()) {
            modelAndView.setViewName("Quadra/formQuadra");
            modelAndView.addObject("quadra");
        } else {
            modelAndView.setViewName("redirect:/quadra/quadras-adicionados");
            quadraRepository.save(quadra);
        }
        return modelAndView;
    }

    @GetMapping("/quadras-adicionados")
    public ModelAndView listagemQuadra() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Quadra/listQuadras");
        modelAndView.addObject("quadrasList", quadraRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id")Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Quadra/editar");
        Quadra quadra = quadraRepository.getById(id);
        modelAndView.addObject("quadra", quadra);
        return modelAndView;
    }

    @PostMapping("/editar")
    public ModelAndView editar(Quadra quadra) {
        ModelAndView modelAndView = new ModelAndView();
        quadraRepository.save(quadra);
        modelAndView.setViewName("redirect:/quadra/quadras-adicionados");
        return modelAndView;
    }

    @GetMapping("/remover/{id}")
    public String removerQuadra(@PathVariable("id") Long id) {
        quadraRepository.deleteById(id);
        return "redirect:/quadra/quadras-adicionados";
    }

    @GetMapping("/filtro-quadras")
    public ModelAndView filtroQuadras() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Quadra/filtroQuadras");
        return modelAndView;
    }

    @GetMapping("/quadras-ativos")
    public ModelAndView listaQuadrasAtivos() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Quadra/quadras-ativas");
        modelAndView.addObject("quadrasAtivos", quadraRepository.findByStatusAtivo());
        return modelAndView;
    }

    @GetMapping("/quadras-inativos")
    public ModelAndView listaQuadrasInativos() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Quadra/quadras-inativas");
        modelAndView.addObject("quadrasInativos", quadraRepository.findByStatusInativo());
        return modelAndView;
    }

    @PostMapping("/pesquisar-quadra")
    public ModelAndView pesquisarQuadra(@RequestParam(required = false) String nome) {
        ModelAndView modelAndView = new ModelAndView();
        List<Quadra> listaQuadras;
        if(nome == null || nome.trim().isEmpty()) {
            listaQuadras = quadraRepository.findAll();
        } else {
            listaQuadras = quadraRepository.findByNomeContainingIgnoreCase(nome);
        }
        modelAndView.addObject("ListaDeQuadras", listaQuadras);
        modelAndView.setViewName("Quadra/pesquisa-resultado");
        return modelAndView;
    }

}
