package br.com.atavares.autoresapi.service;

import br.com.atavares.autoresapi.model.Autor;
import br.com.atavares.autoresapi.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AutorService {

    @Autowired
    AutorRepository autorRepository;

    public void cadastrarAutor(Autor autor, String header){
        autor.setUsuarioUltimaAtualizacao(header);
        autorRepository.save(autor);
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

    public void atualizarAutor(Autor autor, String header){
        autor.setUsuarioUltimaAtualizacao(header);
        autorRepository.save(autor);
    }
}
