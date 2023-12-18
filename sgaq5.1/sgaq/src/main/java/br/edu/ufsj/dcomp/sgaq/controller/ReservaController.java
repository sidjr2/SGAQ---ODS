package br.edu.ufsj.dcomp.sgaq.controller;

import br.edu.ufsj.dcomp.sgaq.enums.Campus;
import br.edu.ufsj.dcomp.sgaq.enums.Status;
import br.edu.ufsj.dcomp.sgaq.model.*;
import br.edu.ufsj.dcomp.sgaq.repository.PresencaRepository;
import br.edu.ufsj.dcomp.sgaq.repository.ReservaRepository;
import br.edu.ufsj.dcomp.sgaq.repository.PunicaoRepository;
import br.edu.ufsj.dcomp.sgaq.repository.QuadraRepository;
import br.edu.ufsj.dcomp.sgaq.repository.EquipamentoRepository;
import br.edu.ufsj.dcomp.sgaq.repository.ReservaEquipamentoRepository;
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
import java.util.Optional;

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
    private EquipamentoRepository equipamentoRepository;

    @Autowired
    private ReservaEquipamentoRepository reservaEquipamentoRepository;

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
    public ModelAndView inserirReserva(@Valid Reserva reserva,@RequestParam("quadra") Long quadraId, @RequestParam("equipamentoId") Long equipamentoId, HttpSession session, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        // Obter usuário logado da sessão
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        Quadra quadra = quadraRepository.findById(quadraId).orElse(null);
        Equipamento equipamento = equipamentoRepository.findById(equipamentoId).orElse(null);
        // Definir o usuário logado na reserva
        if (usuarioLogado != null) {
            reserva.setUsuario(usuarioLogado);
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("Reserva/formReserva");
            modelAndView.addObject("reserva",reserva);
        } else {
            reserva.setQuadra(quadra);

            reservaRepository.save(reserva);
            ReservaEquipamento reservaEquipamento = new ReservaEquipamento();
            reservaEquipamento.setReserva(reserva);
            reservaEquipamento.setEquipamento(equipamento);
            reservaEquipamento.setDataHora( LocalDateTime.now());
            reservaEquipamentoRepository.save(reservaEquipamento);
            modelAndView.setViewName("redirect:/reserva/reservas-adicionados");
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
    @GetMapping("/registrarpresenca/{id}")
    @Transactional
    public String registrarPresenca(@PathVariable("id") Long id) {
            // Recuperar a reserva com base no índice fornecido
            Reserva reserva = reservaRepository.findById(id).orElse(null);

            // Atualizar o status da reserva para REALIZADO
            reservaRepository.atualizarStatusPorId(reserva.getId(), Status.REALIZADO);

            // Redirecionar para a página de reservas adicionadas
            return "redirect:/reserva/reservas-adicionados";
    }

    @GetMapping("/remover/{id}")
    public String removerReserva(@PathVariable("id") Long id) {
        reservaRepository.deleteById(id);
        return "redirect:/reserva/reservas-adicionados";
    }



    @GetMapping("/reservas-adicionados")
    @Transactional
    public ModelAndView listagemReservas() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Reserva/listReservas");


        List<Reserva> reservas = reservaRepository.findAll();

        LocalDateTime agora = LocalDateTime.now();

        for (Reserva reserva : reservas) {

                // Verifica se estamos dentro do intervalo da reserva
                if (agora.isAfter(reserva.getDataHoraInical()) && agora.isBefore(reserva.getDataHoraFinal())) {
                    // Verifica se a presença foi realizada dentro do horário da reserva
                    if (agora.isAfter(reserva.getDataHoraInical()) && agora.isBefore(reserva.getDataHoraFinal())) {
                        reserva.setPresenca(Status.REALIZADO);
                        reserva.setPunicao(Status.INATIVO);
                    } else {
                        reserva.setPresenca(Status.NAOREALIZADO);
                        reserva.setPunicao(Status.ATIVO);

                        // Adiciona punição ao banco de dados, se necessário
                        Punicao punicao = new Punicao();
                        punicao.setUsuario(reserva.getUsuario());
                        punicao.setReserva(reserva);
                        punicao.setDataHora(agora);
                        punicao.setPunicao(Status.REALIZADO);
                        punicaoRepository.save(punicao);
                    }
                } else {
                    reserva.setPresenca(Status.NAOREALIZADO);
                    reserva.setPunicao(Status.ATIVO);
                }
            }


        reservaRepository.saveAll(reservas);
        List<Object[]> reservasequipamento = reservaRepository.findByEquipamento();
        modelAndView.addObject("reservasList", reservasequipamento);
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

            // Verifica se o horário atual está fora do intervalo da reserva
            if (agora.isBefore(reserva.getDataHoraInical()) || agora.isAfter(reserva.getDataHoraFinal())) {
                reserva.setPunicao(Status.ATIVO);
                reserva.setPresenca(Status.NAOREALIZADO);

                // Adiciona punição ao banco de dados, se necessário
                Punicao punicao = new Punicao();
                punicao.setUsuario(reserva.getUsuario());
                punicao.setReserva(reserva);
                punicao.setDataHora(agora);
                punicao.setPunicao(Status.REALIZADO);
                punicaoRepository.save(punicao);

                // Adiciona registro de presença ao banco de dados
                Presenca presenca = new Presenca();
                presenca.setUsuario(reserva.getUsuario());
                presenca.setReserva(reserva);
                presenca.setDataHora(agora);
                presenca.setPresenca(Status.NAOREALIZADO);
                presencaRepository.save(presenca);
            } else {
                // Verifica se a presença foi registrada fora do horário da reserva
                if (agora.isBefore(reserva.getDataHoraInical()) || agora.isAfter(reserva.getDataHoraFinal())) {
                    reserva.setPunicao(Status.ATIVO);
                    reserva.setPresenca(Status.NAOREALIZADO);

                    // Adiciona punição ao banco de dados, se necessário
                    Punicao punicao = new Punicao();
                    punicao.setUsuario(reserva.getUsuario());
                    punicao.setReserva(reserva);
                    punicao.setDataHora(agora);
                    punicao.setPunicao(Status.REALIZADO);
                    punicaoRepository.save(punicao);

                    // Atualiza o status da presença para punido
                    Presenca presenca = presencaRepository.findByUsuarioAndReserva(reserva.getUsuario(), reserva);
                    presenca.setPresenca(Status.REALIZADO);
                    presencaRepository.save(presenca);
                } else {
                    // Caso contrário, a presença é considerada válida
                    reserva.setPunicao(Status.INATIVO);
                    reserva.setPresenca(Status.REALIZADO);
                }
            }
        }
        reservaRepository.saveAll(listaReservas);
        List<Object[]> reservasequipamento = reservaRepository.findByEquipamento();
        modelAndView.addObject("ListaDeReservas", reservasequipamento);
        modelAndView.setViewName("Reserva/pesquisa-resultado");
        return modelAndView;
    }

    @GetMapping("/obterEquipamentosPorQuadra")
    @ResponseBody
    public ResponseEntity<List<Equipamento>> obterEquipamentosPorQuadra( @RequestParam("quadra") Long quadraId) {
        try {
             System.out.println(quadraId);
            Quadra quadra = quadraRepository.findById(quadraId).orElse(null);
            System.out.println(quadra);
            List<Equipamento> equipamentoPorQuadraList = equipamentoRepository.findEquipamentoByQuadraId(quadra);
            return new ResponseEntity<>(equipamentoPorQuadraList, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            // Lida com a exceção se a conversão do enum falhar
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
