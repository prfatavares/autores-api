package br.com.atavares.autoresapi.controller;

import br.com.atavares.autoresapi.dto.AutorDTO;
import br.com.atavares.autoresapi.dto.AutorRespostaDTO;
import br.com.atavares.autoresapi.model.Autor;
import br.com.atavares.autoresapi.service.AutorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("autores")
public class AutorController {

    @Autowired
    AutorService autorService;

    @PostMapping
    public ResponseEntity<Void> cadastrarAutor(@RequestBody @Valid AutorDTO autorDTO, HttpServletRequest request){
        Autor autor = autorDTO.toEntity();
        autorService.cadastrarAutor(autor, request.getHeader("User-Agent"));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(autor.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<AutorRespostaDTO> visualizarAutorPorId(@PathVariable("id") UUID id){
        Autor autor = autorService.visualizarAutorPorId(id);
        AutorRespostaDTO autorRespostaDTO = new AutorRespostaDTO(autor.getId(), autor.getNome(), autor.getDataNascimento(), autor.getNacionalidade());
        return ResponseEntity.ok(autorRespostaDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> excluirAutorPorId(@PathVariable("id") UUID id){
        autorService.excluirAutorPorId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AutorRespostaDTO>> pesquisarAutores(){
        List<Autor> listaAutor = autorService.pesquisarAutores();
        List<AutorRespostaDTO> listaAutorRespostaDTO = new ArrayList<>();
        for (Autor autor : listaAutor) {
            listaAutorRespostaDTO.add(new AutorRespostaDTO(autor.getId(), autor.getNome(), autor.getDataNascimento(), autor.getNacionalidade()));
        }
        return ResponseEntity.ok(listaAutorRespostaDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizarAutorPorId(@PathVariable("id") UUID id, @RequestBody @Valid AutorDTO autorDTO, HttpServletRequest request){
        Autor autor = autorService.visualizarAutorPorId(id);
        autorDTO.toEntity(autor);
        autorService.atualizarAutor(autor, request.getHeader("User-Agent"));
        return ResponseEntity.noContent().build();
    }
}
