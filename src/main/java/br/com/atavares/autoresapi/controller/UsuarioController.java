package br.com.atavares.autoresapi.controller;

import br.com.atavares.autoresapi.dto.UsuarioDTO;
import br.com.atavares.autoresapi.model.Usuario;
import br.com.atavares.autoresapi.service.AutorService;
import br.com.atavares.autoresapi.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AutorService autorService;

    @PostMapping
    public ResponseEntity<Void> cadastrarUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioDTO.toEntity();
        usuarioService.cadastrarUsuario(usuario);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Usuario> visualizarUsuarioPorId(@PathVariable("id") UUID id){
        return ResponseEntity.ok(usuarioService.visualizarUsuarioPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> pesquisarUsuarios(){
        return ResponseEntity.ok(usuarioService.pesquisarUsuarios());
    }
}
