package br.edu.ufsj.dcomp.sgaq.controller;

import br.edu.ufsj.dcomp.sgaq.enums.Campus;
import br.edu.ufsj.dcomp.sgaq.enums.Status;
import br.edu.ufsj.dcomp.sgaq.model.*;
import br.edu.ufsj.dcomp.sgaq.repository.PresencaRepository;
import br.edu.ufsj.dcomp.sgaq.repository.ReservaRepository;
import br.edu.ufsj.dcomp.sgaq.repository.PunicaoRepository;
import br.edu.ufsj.dcomp.sgaq.repository.QuadraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;

import javax.validation.Valid;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/reserva") // Adicionando um mapeamento de nível de classe

public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private QuadraRepository quadraRepository;

    @Autowired
    private PunicaoRepository punicaoRepository;

    @Autowired
    private PresencaRepository presencaRepository;

    @Autowired
    private HttpSession session;

    @GetMapping("/inserirReservas")
    public ModelAndView insertReservas(Reserva reserva) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Reserva/formReserva");

        // Adiciona o objeto Reserva ao modelo
        modelAndView.addObject("reserva", new Reserva());

        // Adiciona os valores do enum Campus ao modelo
        modelAndView.addObject("campusValues", Campus.values());
        return modelAndView;
    }

    @PostMapping("InsertReservas")
    public ModelAndView inserirReserva(@Valid Reserva reserva,@RequestParam("quadra") Long quadraId, HttpSession session, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        // Obter usuário logado da sessão
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        Quadra quadra = quadraRepository.findById(quadraId).orElse(null);
        // Definir o usuário logado na reserva
        if (usuarioLogado != null) {
            reserva.setUsuario(usuarioLogado);
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("Reserva/formReserva");
            modelAndView.addObject("reserva",reserva);
        } else {
            reserva.setQuadra(quadra);
            modelAndView.setViewName("redirect:/reserva/reservas-adicionados");
            reservaRepository.save(reserva);
        }
        return modelAndView;
    }

    @GetMapping("/obterQuadrasPorCampus")
    @ResponseBody
    public ResponseEntity<List<Quadra>> obterQuadrasPorCampus(@RequestParam String campus) {

        try {
            Campus campusEnum = Campus.valueOf(campus); // Converte a String para o enum Campus
            List<Quadra> quadraPorCampusList = quadraRepository.findByCampusName(campusEnum);
            return new ResponseEntity<>(quadraPorCampusList, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            // Lida com a exceção se a conversão do enum falhar
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }    

    @GetMapping("/reservas-adicionados")
    @Transactional
    public ModelAndView listagemReservas() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Reserva/listReservas");
        List<Reserva> reservas = reservaRepository.findAll();
        for(Reserva reserva: reservas){
            LocalDateTime agora = LocalDateTime.now();

            if (agora.isBefore(reserva.getDataHoraInical())){
                reserva.setPunicao(Status.ATIVO);
                reserva.setPresenca(Status.INATIVO);
                // Adicione as punições  correspondentes ao banco de dados, se necessário
                Punicao punicao = new Punicao();
                punicao.setUsuario(reserva.getUsuario());
                punicao.setReserva(reserva);
                punicao.setDataHora(LocalDateTime.now());
                punicao.setPunicao(Status.ATIVO);
                punicaoRepository.save(punicao);

                //presenças
                Presenca presenca = new Presenca();
                presenca.setUsuario(reserva.getUsuario());
                presenca.setReserva(reserva);
                presenca.setDataHora(LocalDateTime.now());
                presenca.setPresenca(Status.INATIVO);
                presencaRepository.save(presenca);

            }
            else{
                reserva.setPunicao(Status.INATIVO);
                reserva.setPresenca(Status.ATIVO);
                // Adicione as punições  correspondentes ao banco de dados, se necessário
                Punicao punicao = new Punicao();
                punicao.setUsuario(reserva.getUsuario());
                punicao.setReserva(reserva);
                punicao.setDataHora(LocalDateTime.now());
                punicao.setPunicao(Status.INATIVO);
                punicaoRepository.save(punicao);

                //presenças
                Presenca presenca = new Presenca();
                presenca.setUsuario(reserva.getUsuario());
                presenca.setReserva(reserva);
                presenca.setDataHora(LocalDateTime.now());
                presenca.setPresenca(Status.ATIVO);
                presencaRepository.save(presenca);
            }
        }
        reservaRepository.saveAll(reservas);
        modelAndView.addObject("reservasList", reservas);
        return modelAndView;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Reserva/editar");
        Reserva reserva = reservaRepository.getById(id);
        modelAndView.addObject("reserva", reserva);
        return modelAndView;
    }
    

    @PostMapping("/editar")
    public ModelAndView editar(Reserva reserva) {
        ModelAndView modelAndView = new ModelAndView();
        // Obter usuário logado da sessão
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        // Definir o usuário logado na reserva
        if (usuarioLogado != null) {
            reserva.setUsuario(usuarioLogado);
        }
        Reserva reservaExistente = reservaRepository.getById(reserva.getId());
         // Atualizar apenas o campo dataHora
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
         LocalDateTime dataHoraInical = LocalDateTime.parse(reserva.getDataHoraStrInical(), formatter);
         reservaExistente.setDataHoraInical(reserva.getDataHoraStrInical());
         LocalDateTime dataHoraFinal = LocalDateTime.parse(reserva.getDataHoraStrFinal(), formatter);
         reservaExistente.setDataHoraFinal(reserva.getDataHoraStrFinal());
        // Salvar a reserva atualizada
        reservaRepository.save(reservaExistente);
        modelAndView.setViewName("redirect:/reserva/reservas-adicionados");
        return modelAndView;
    }

    @GetMapping("/remover/{id}")
    public String removerReserva(@PathVariable("id") Long id) {
        reservaRepository.deleteById(id);
        return "redirect:/reserva/reservas-adicionados";
    }

//    @GetMapping("reservas-punicao-ativos")
//    public ModelAndView listaReservasPunicaoAtivos() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("Reserva/reservas-ativos");
//        modelAndView.addObject("Punido", reservaRepository.findByPunicao());
//        return modelAndView;
//    }
//
//    @GetMapping("reservas-punicao-inativos")
//    public ModelAndView listaReservasPunicaoInativos() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("Reserva/reservas-inativos");
//        modelAndView.addObject("Impune", reservaRepository.findByPunicaoInativo());
//        return modelAndView;
//    }

    @PostMapping("/pesquisar-reserva")
    @Transactional
    public ModelAndView pesquisarReserva(@RequestParam(required = false) String nome) {
        ModelAndView modelAndView = new ModelAndView();
        List<Reserva> listaReservas;
        if (nome == null || nome.trim().isEmpty()) {
            listaReservas = reservaRepository.findAll();
        } else {
            listaReservas = reservaRepository.findByNomeContainingIgnoreCase(nome);
        }
        // Percorre as reservas para atualizar os status
        for (Reserva reserva : listaReservas) {
            LocalDateTime agora = LocalDateTime.now();

            if (reserva.getDataHoraInical().isBefore(agora)){
                reserva.setPunicao(Status.ATIVO);
                reserva.setPresenca(Status.INATIVO);
                // Adicione as punições  correspondentes ao banco de dados, se necessário
                Punicao punicao = new Punicao();
                punicao.setUsuario(reserva.getUsuario());
                punicao.setReserva(reserva);
                punicao.setDataHora(LocalDateTime.now());
                punicao.setPunicao(Status.ATIVO);
                punicaoRepository.save(punicao);

                //presenças
                Presenca presenca = new Presenca();
                presenca.setUsuario(reserva.getUsuario());
                presenca.setReserva(reserva);
                presenca.setDataHora(LocalDateTime.now());
                presenca.setPresenca(Status.INATIVO);
                presencaRepository.save(presenca);

            }
            else{
                reserva.setPunicao(Status.INATIVO);
                reserva.setPresenca(Status.ATIVO);
                // Adicione as punições  correspondentes ao banco de dados, se necessário
                Punicao punicao = new Punicao();
                punicao.setUsuario(reserva.getUsuario());
                punicao.setReserva(reserva);
                punicao.setDataHora(LocalDateTime.now());
                punicao.setPunicao(Status.INATIVO);
                punicaoRepository.save(punicao);

                //presenças
                Presenca presenca = new Presenca();
                presenca.setUsuario(reserva.getUsuario());
                presenca.setReserva(reserva);
                presenca.setDataHora(LocalDateTime.now());
                presenca.setPresenca(Status.ATIVO);
                presencaRepository.save(presenca);
            }
        }
        reservaRepository.saveAll(listaReservas);
        modelAndView.addObject("ListaDeReservas", listaReservas);
        modelAndView.setViewName("Reserva/pesquisa-resultado");
        return modelAndView;
    }
}
