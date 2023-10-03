package br.edu.ufsj.dcefs.sgaq.controller;

import br.edu.ufsj.dcefs.sgaq.dtos.UserRecordDto;
import br.edu.ufsj.dcefs.sgaq.model.UserModel;
import br.edu.ufsj.dcefs.sgaq.repositories.UserRepository;
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
public class UserController {

    final
    UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /* salva */
    @PostMapping("/user")
    public ResponseEntity<UserModel> salvaUser(@RequestBody @Valid UserRecordDto userRecordDto){
        var user_model = new UserModel();
        BeanUtils.copyProperties(userRecordDto,user_model);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user_model));
    }
    /* busca todos user */
    @GetMapping("/user")
    public ResponseEntity<List<UserModel>> getAllUsers(){
        List<UserModel> userList = userRepository.findAll();
        if(!userList.isEmpty()){
            for(UserModel user : userList) {
                UUID id = user.getId_User();
                user.add(linkTo(methodOn(UserController.class).getOneUser(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

    /*busca somente um user por id*/
    @GetMapping("/user/{userid}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value="userid") UUID idUser){
        Optional<UserModel> userO = userRepository.findById(idUser);
        if(userO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("USUARIO NÃO ENCONTRADA.");
        }
        userO.get().add(linkTo(methodOn(UserController.class).getAllUsers()).withRel("LISTA DE USUARIOS"));
        return ResponseEntity.status(HttpStatus.OK).body(userO.get());
    }

    /*update*/
    @PutMapping("/user/update/{userid}")
    public ResponseEntity<Object> updateUser(@PathVariable(value="userid") UUID idUser,
                                             @RequestBody @Valid UserRecordDto userRecordDto){
        Optional<UserModel> user0 = userRepository.findById(idUser);
        if(user0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("USUARIO NAO ENCONTRADA");
        }
        var user_model = user0.get();
        BeanUtils.copyProperties(userRecordDto,user_model);
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(user_model));
    }

    /* delete usuario */
    @DeleteMapping("/user/delete/{userid}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value="userid") UUID idUser){
        Optional<UserModel> user0 = userRepository.findById(idUser);
        if(user0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("USUARIO NAO ENCONTRADA");
        }
        userRepository.delete(user0.get());
        return ResponseEntity.status(HttpStatus.OK).body("USUARIO DELETADO COM SUCESSO!");
    }
}
