package br.com.atavares.autoresapi.controller;

import br.com.atavares.autoresapi.dto.AutorDTO;
import br.com.atavares.autoresapi.dto.AutorRespostaDTO;
import br.com.atavares.autoresapi.model.Autor;
import br.com.atavares.autoresapi.service.AutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("autores")
@Tag(name = "Autores") //Customização do swagger
public class AutorController {

    @Autowired
    AutorService autorService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Cadastrar", description = "Cadastrar novo autor") //Customização do swagger
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de cadastro")
    })
    public ResponseEntity<Void> cadastrarAutor(@RequestBody @Valid AutorDTO autorDTO, HttpServletRequest request, Authentication authentication){
        Autor autor = autorDTO.toEntity();
        autorService.cadastrarAutor(autor, request.getHeader("User-Agent"), authentication);
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
