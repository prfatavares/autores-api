package br.com.atavares.autoresapi.controller;

import br.com.atavares.autoresapi.model.Autor;
import br.com.atavares.autoresapi.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "autores")
public class AutorController {

    @Autowired
    AutorService autorService;

    @PostMapping
    public Autor cadastrarAutor(@RequestBody Autor autor){
        return autorService.cadastrarAutor(autor);
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
    public void atualizarAutorPorId(@PathVariable("id") UUID id, @RequestBody Autor autor){
        autorService.atualizarAutorPorId(id, autor);
    }
}
