package br.com.atavares.autoresapi.service;

import br.com.atavares.autoresapi.model.Autor;
import br.com.atavares.autoresapi.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
public class AutorService {

    @Autowired
    AutorRepository autorRepository;

    public Autor cadastrarAutor(Autor autor){
        return autorRepository.save(autor);
    }

    public Autor visualizarAutorPorId(UUID id){
        return autorRepository.findById(id).orElse(null);
    }

    public void excluirAutorPorId(UUID id){
        autorRepository.deleteById(id);
    }

    public List<Autor> pesquisarAutores() {
        return autorRepository.findAll();
    }

    public void atualizarAutorPorId(UUID id, @RequestBody Autor autor){
        autor.setId(id);
        autorRepository.save(autor);
    }
}
