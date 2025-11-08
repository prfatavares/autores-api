package br.com.atavares.autoresapi.config;

import br.com.atavares.autoresapi.model.Usuario;
import br.com.atavares.autoresapi.service.UsuarioService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioService usuarioService;

    public CustomUserDetailsService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario user = usuarioService.pesquisarPorLogin(login);
        if(user == null){
            var msg = "Usuário "+ login + " não encontrado.";
            System.out.println(msg);
            throw new UsernameNotFoundException(msg);
        }
        return User.builder()
                .username(user.getLogin())
                .password(user.getSenha())
                .roles(user.getRoles().toArray(new String[user.getRoles().size()]))
                .build();
    }
}
