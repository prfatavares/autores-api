package br.com.atavares.autoresapi.controller;

import br.com.atavares.autoresapi.dto.AutorDTO;
import br.com.atavares.autoresapi.model.Autor;
import br.com.atavares.autoresapi.service.AutorService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("autores")
public class AutorController {

    @Autowired
    AutorService autorService;

    @PostMapping
    public ResponseEntity<Void> cadastrarAutor(@RequestBody AutorDTO autorDTO, HttpServletRequest request){
        Autor autor = autorDTO.mapearAutor();
        autorService.cadastrarAutor(autor, request.getHeader("User-Agent"));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(autor.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public Autor visualizarAutorPorId(@PathVariable("id") UUID id){
        return autorService.visualizarAutorPorId(id);
    }

    @DeleteMapping("{id}")
    public void excluirAutorPorId(@PathVariable("id") UUID id){
        autorService.excluirAutorPorId(id);
    }

    @GetMapping
    public List<Autor> pesquisarAutores(){
        return autorService.pesquisarAutores();
    }

    @PutMapping("{id}")
    public void atualizarAutorPorId(@PathVariable("id") UUID id, @RequestBody AutorDTO autorDTO, HttpServletRequest request){
        Autor autor = autorDTO.mapearAutor();
        autorService.atualizarAutorPorId(id, autor, request.getHeader("User-Agent"));
    }
}
