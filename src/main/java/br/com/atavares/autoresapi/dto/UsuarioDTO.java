package br.com.atavares.autoresapi.dto;

import br.com.atavares.autoresapi.model.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record UsuarioDTO(@NotBlank(message = "Login obrigatório") @Size(max = 50, message = "Tamanho excedido") String login,
                         @NotBlank(message = "Senha obrigatória") @Size(min = 4, max = 100, message = "Tamanho entre 4 e 100") String senha,
                         List<String> roles) {

    public Usuario toEntity(){
        Usuario usuario = new Usuario();
        usuario.setLogin(this.login);
        usuario.setSenha(this.senha);
        for (String role : this.roles) {
            usuario.getRoles().add(role);
        }
        return usuario;
    }
}
