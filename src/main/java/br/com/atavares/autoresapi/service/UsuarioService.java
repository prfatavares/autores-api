package br.com.atavares.autoresapi.service;

import br.com.atavares.autoresapi.model.Autor;
import br.com.atavares.autoresapi.model.Usuario;
import br.com.atavares.autoresapi.repository.AutorRepository;
import br.com.atavares.autoresapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder encoder;

    public void cadastrarUsuario(Usuario usuario){
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        usuarioRepository.save(usuario);
    }

    public Usuario visualizarUsuarioPorId(UUID id){
        return usuarioRepository.findById(id).orElse(null);
    }

    public List<Usuario> pesquisarUsuarios(){
        return usuarioRepository.findAll();
    }
}
