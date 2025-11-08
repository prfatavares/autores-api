package br.com.atavares.autoresapi.dto;

import java.time.LocalDate;
import java.util.UUID;

public record AutorRespostaDTO(UUID id, String nome, LocalDate dataNascimento, String nacionalidade) {

}
