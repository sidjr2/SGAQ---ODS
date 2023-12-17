package br.edu.ufsj.dcomp.sgaq.controller;


import br.edu.ufsj.dcomp.sgaq.enums.Campus;
import br.edu.ufsj.dcomp.sgaq.model.Equipamento;
import br.edu.ufsj.dcomp.sgaq.model.Quadra;
import br.edu.ufsj.dcomp.sgaq.repository.EquipamentoRepository;
import br.edu.ufsj.dcomp.sgaq.repository.QuadraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/equipamento") // Adicionando um mapeamento de nível de classe
public class EquipamentoController {

    @Autowired
    private EquipamentoRepository equipamentoRepository;
    private QuadraRepository quadraRepository;


    @GetMapping("/inserirEquipamentos")
    public ModelAndView insertEquipamentos(Equipamento equipamento) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Equipamento/formEquipamento");
        modelAndView.addObject("equipamento", new Equipamento());
        modelAndView.addObject("campusValues", Campus.values());
        return modelAndView;
    }

    @PostMapping("InsertEquipamentos")
    public ModelAndView inserirEquipamento(@Valid Equipamento equipamento, @RequestParam("quadra") Long quadraId, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Quadra quadra = quadraRepository.findById(quadraId).orElse(null);
        if(bindingResult.hasErrors()) {
            modelAndView.setViewName("Equipamento/formEquipamento");
            modelAndView.addObject("equipamento", equipamento);
        } else {
            equipamento.setQuadra(quadra);
            modelAndView.setViewName("redirect:/equipamento/equipamentos-adicionados");
            equipamentoRepository.save(equipamento);
        }
        return modelAndView;
    }

    @GetMapping("/equipamentos-adicionados")
    public ModelAndView listagemEquipamentos() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Equipamento/listEquipamentos");
        modelAndView.addObject("equipamentosList", equipamentoRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id")Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Equipamento/editar");
        Equipamento equipamento = equipamentoRepository.getById(id);
        modelAndView.addObject("equipamento", equipamento);
        return modelAndView;
    }

    @PostMapping("/editar")
    public ModelAndView editar(Equipamento equipamento) {
        ModelAndView modelAndView = new ModelAndView();
        equipamentoRepository.save(equipamento);
        modelAndView.setViewName("redirect:/equipamento/equipamentos-adicionados");
        return modelAndView;
    }

    @GetMapping("/remover/{id}")
    public String removerEquipamento(@PathVariable("id") Long id) {
        equipamentoRepository.deleteById(id);
        return "redirect:/equipamento/equipamentos-adicionados";
    }

    @GetMapping("/filtro-equipamentos")
    public ModelAndView filtroEquipamento() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Equipamento/filtroEquipamento");
        return modelAndView;
    }

    @GetMapping("/equipamentos-ativos")
    public ModelAndView listaEquipamentosAtivos() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Equipamento/equipamentos-ativos");
        modelAndView.addObject("equipamentosAtivos", equipamentoRepository.findByStatusAtivo());
        return modelAndView;
    }

    @GetMapping("/equipamentos-inativos")
    public ModelAndView listaEquipamentosInativos() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Equipamento/equipamentos-inativos");
        modelAndView.addObject("equipamentosInativos", equipamentoRepository.findByStatusInativo());
        return modelAndView;
    }

    @PostMapping("/pesquisar-equipamento")
    public ModelAndView pesquisarEquipamento(@RequestParam(required = false) String nome) {
        ModelAndView modelAndView = new ModelAndView();
        List<Equipamento> listaEquipamentos;
        if(nome == null || nome.trim().isEmpty()) {
            listaEquipamentos = equipamentoRepository.findAll();
        } else {
            listaEquipamentos = equipamentoRepository.findByNomeContainingIgnoreCase(nome);
        }
        modelAndView.addObject("ListaDeEquipamentos", listaEquipamentos);
        modelAndView.setViewName("Equipamento/pesquisa-resultado");
        return modelAndView;
    }

    @GetMapping("/pesquisar-equipamento-reserva/{id}")
    public ModelAndView pesquisarEquipamentoReserva(@PathVariable("id") String quadra) {
        ModelAndView modelAndViewReserva = new ModelAndView();
        List<Equipamento> listaEquipamentos;

        if (quadra != null && !quadra.trim().isEmpty()) {
            //listaEquipamentos = equipamentoRepository.findByReserva(quadra);
        } else {
            // Lógica adequada se quadra for nulo ou vazio
            listaEquipamentos = new ArrayList<>(); // ou qualquer outra lógica
        }

        //modelAndViewReserva.addObject("ListaDeEquipamentos", listaEquipamentos);
        modelAndViewReserva.setViewName("Equipamento/pesquisa-resultado");

        return modelAndViewReserva;
    }

}
