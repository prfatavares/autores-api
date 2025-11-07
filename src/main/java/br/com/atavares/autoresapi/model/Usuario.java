package br.com.atavares.autoresapi.model;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_usuario")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    @Column(name = "login", nullable = false, unique = true, length = 50)
    private String login;

    @Column(name = "senha", nullable = false, length = 100)
    private String senha;

    @Type(ListArrayType.class) //https://github.com/vladmihalcea/hypersistence-utils
    @Column(name = "roles", columnDefinition = "varchar[]")
    private List<String> roles = new ArrayList<>();

    public Usuario() {

    }
}
