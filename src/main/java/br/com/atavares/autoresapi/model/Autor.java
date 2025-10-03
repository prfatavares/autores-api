package br.com.atavares.autoresapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_autor")
@Data
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true, nullable = false, name = "id")
    private UUID id;

    @Column(nullable = false, name = "nome", length = 100)
    private String nome;

    @Column(nullable = false, name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(nullable = false, name = "nacionalidade", length = 50)
    private String nacionalidade;

    public Autor() {

    }

    public Autor(UUID id, String nome, LocalDate dataNascimento, String nacionalidade) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.nacionalidade = nacionalidade;
    }
}
