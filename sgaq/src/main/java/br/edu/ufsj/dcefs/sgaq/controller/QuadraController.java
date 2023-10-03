package br.edu.ufsj.dcefs.sgaq.controller;

import br.edu.ufsj.dcefs.sgaq.dtos.QuadraRecordDto;
import br.edu.ufsj.dcefs.sgaq.model.QuadraModel;
import br.edu.ufsj.dcefs.sgaq.repositories.QuadraRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class QuadraController {

    final
    QuadraRepository quadraRepository;

    public QuadraController(QuadraRepository quadraRepository) {
        this.quadraRepository = quadraRepository;
    }

    /* records */
    @PostMapping("/quadra")
    public ResponseEntity<QuadraModel> salvaQuadra(@RequestBody @Valid QuadraRecordDto quadraRecordDto){
        var quadra_model = new QuadraModel();
        BeanUtils.copyProperties(quadraRecordDto,quadra_model);
        return ResponseEntity.status(HttpStatus.CREATED).body(quadraRepository.save(quadra_model));
    }

    @GetMapping("/quadra")
    public ResponseEntity<List<QuadraModel>> getAllQuadra(){
        List<QuadraModel> quadraList = quadraRepository.findAll();
        if(!quadraList.isEmpty()){
            for(QuadraModel quadra : quadraList) {
                UUID id = quadra.getId_Quadra();
                quadra.add(linkTo(methodOn(QuadraController.class).getOneQuadra(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(quadraList);
    }

    @GetMapping("/quadra/{quadraid}")
    public ResponseEntity<Object> getOneQuadra(@PathVariable(value="quadraid") UUID idQuadra){
        Optional<QuadraModel> quadraO = quadraRepository.findById(idQuadra);
        if(quadraO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("QUADRA NÃO ENCONTRADA.");
        }
        quadraO.get().add(linkTo(methodOn(QuadraController.class).getAllQuadra()).withRel("LISTA DAS QUADRAS"));
        return ResponseEntity.status(HttpStatus.OK).body(quadraO.get());
    }

    @PutMapping("/quadra/{quadraid}")
    public ResponseEntity<Object> updateQuadra(@PathVariable(value="quadraid") UUID idQuadra,
                                             @RequestBody @Valid QuadraRecordDto quadraRecordDto){
        Optional<QuadraModel> quadra0 = quadraRepository.findById(idQuadra);
        if(quadra0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("QUADRA NAO ENCONTRADA");
        }
        var quadra_model = quadra0.get();
        BeanUtils.copyProperties(quadraRecordDto,quadra_model);
        return ResponseEntity.status(HttpStatus.OK).body(quadraRepository.save(quadra_model));
    }

    @DeleteMapping("/quadra/delete/{quadraid}")
    public ResponseEntity<Object> deleteQuadra(@PathVariable(value="quadraid") UUID idQuadra){
        Optional<QuadraModel> quadra0 = quadraRepository.findById(idQuadra);
        if(quadra0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("QUADRA NAO ENCONTRADA");
        }
        quadraRepository.delete(quadra0.get());
        return ResponseEntity.status(HttpStatus.OK).body("QUADRA DELETADA COM SUCESSO");
    }


}
