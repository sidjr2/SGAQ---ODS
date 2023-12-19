package br.edu.ufsj.dcomp.sgaq.service;


import br.edu.ufsj.dcomp.sgaq.exception.CriptoExistsException;
import br.edu.ufsj.dcomp.sgaq.exception.EmailExistsException;
import br.edu.ufsj.dcomp.sgaq.exception.ServiceExc;
import br.edu.ufsj.dcomp.sgaq.model.Usuario;
import br.edu.ufsj.dcomp.sgaq.repository.UsuarioRepository;
import br.edu.ufsj.dcomp.sgaq.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.NoSuchAlgorithmException;

@Service
public class ServiceUsuario {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void salvarUsuario(Usuario user) throws Exception {

        try {

            if(usuarioRepository.findByEmail(user.getEmail()) !=null) {
                throw new EmailExistsException("Este email já esta cadastrado: " + user.getEmail());
            }

            user.setSenha(Util.md5(user.getSenha()));

        } catch (NoSuchAlgorithmException e) {
            throw new CriptoExistsException("Error na criptografia da senha");
        }
        usuarioRepository.save(user);
    }

    public Usuario loginUser(String user, String senha) throws ServiceExc {

        return usuarioRepository.buscarLogin(user, senha);
    }
}
