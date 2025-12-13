package br.com.atavares.autoresapi.dto;

import br.com.atavares.autoresapi.model.Autor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

//Opção: utilizar no projeto MapStruct
@Schema(name = "DTO Autor") //Customização do swagger
public record AutorDTO(@NotBlank(message = "Nome obrigatório") @Size(max = 100, message = "Tamanho excedido") String nome,
                       @NotNull @Past LocalDate dataNascimento,
                       @NotBlank String nacionalidade) {

    public Autor toEntity(){
        Autor autor = new Autor();
        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);
        return autor;
    }

    public void toEntity(Autor autor){
        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);
    }
}
