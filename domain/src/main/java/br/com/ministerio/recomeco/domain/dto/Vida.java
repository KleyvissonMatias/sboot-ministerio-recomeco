package br.com.ministerio.recomeco.domain.dto;

import br.com.ministerio.recomeco.util.Utils;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Vida implements Serializable {
    private Integer id;

    @NotNull(message = "O nome completo não pode ser nulo")
    @NotBlank(message = "O nome completo não pode estar em branco")
    private String nomeCompleto;

    @Pattern(regexp = "^\\d{11}$", message = "o CPF está inválido")
    private String cpf;

    @NotNull(message = "A data de nascimento não pode ser nula")
    @Past(message = "A data de nascimento deve estar no passado")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

    @NotNull(message = "O Sexo não pode ser nulo")
    @Pattern(regexp = "^(Masculino|Feminino)$", message = "Sexo inválido. Use 'Masculino' ou 'Feminino'")
    private String sexo;

    @NotNull(message = "O Estado Civil não pode ser nulo")
    @Pattern(regexp = "^(Solteiro|Casado|Divorciado|Viúvo)$", message = "Estado civil inválido")
    private String estadoCivil;

    @NotNull(message = "O Telefone não pode ser nulo")
    @NotBlank(message = "O Telefone não pode estar em branco")
    private String telefone;

    private String telefoneOutroContato;

    @NotNull(message = "O endereço não pode ser nulo")
    @NotBlank(message = "O endereço não pode estar em branco")
    private String endereco;

    @NotNull(message = "O email não pode ser nulo")
    @NotBlank(message = "O email não pode estar em branco")
    @Email(message = "Endereço de email inválido")
    private String email;

    private String redeSocial;

    @NotNull(message = "O campo possui celula não pode ser nulo")
    @NotBlank(message = "O campo possui celula não pode estar em branco")
    @Pattern(regexp = "^[SN]$", message = "Valor inválido para 'possuiCelula'. Use 'S' Sim ou 'N' Não")
    private String possuiCelula;

    private String nomeCelula;

    @Pattern(regexp = "^(Ativo|Inativo|Triagem|Em Célula)$", message = "Status inválido. Use 'Ativo','Inativo','Triagem','Em Célula'")
    private String status;

    @NotNull(message = "O tipo conversão não pode ser nulo")
    @NotBlank(message = "O tipo conversão celula não pode estar em branco")
    @Pattern(regexp = "^(Online|Presencial)$", message = "Status inválido. Use 'Online' ou 'Presencial'")
    private String tipoConversao;

    @NotNull(message = "O campus não pode ser nulo")
    @NotBlank(message = "O campus não pode estar em branco")
    private String campus;

    @NotNull(message = "O culto não pode ser nulo")
    @NotBlank(message = "O campo culto não pode estar em branco")
    @Pattern(regexp = "^(Domingo|TPU|Connect|Quarta|Start|Connect UP|Decolar|Memorial)$", message = "culto inválido. Use 'Domingo','TPU','Connect','Quarta','Start','Connect UP','Decolar' ou 'Memorial'")
    private String culto;

    @NotNull(message = "O horario do culto não pode ser nulo")
    @NotBlank(message = "O campo horario do culto não pode estar em branco")
    @Pattern(regexp = "^\\d{2}:\\d{2}$", message = "Formato de horário inválido Use HH:mm")
    private String horarioCulto;

    @Size(max = 500, message = "A observação deve ter no máximo 500 caracteres")
    private String observacao;

    @Pattern(regexp = "^[SN]$", message = "Valor inválido para 'lider de Celula'. Use 'S' Sim ou 'N' Não")
    private String liderCelula;

    @Pattern(regexp = "^[SN]$", message = "Valor inválido para 'lider em Treinamento'. Use 'S' Sim ou 'N' Não")
    private String liderTreinamento;

    @Past(message = "A data de batismo deve estar no passado")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataBatismo;

    @Past(message = "A data do encontro deve estar no passado")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataEncontro;

    @Past(message = "A data do renovo deve estar no passado")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataRenovo;

    @Past(message = "A data das Águias deve estar no passado")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataAguias;

    @Past(message = "A data do escola de líderes 1 deve estar no passado")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataLidereUm;

    @Past(message = "A data do escola de líderes 2 deve estar no passado")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataLidereDois;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataInclusao;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataAlteracao;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataExclusao;

    public boolean isCpfValido() {
        if (cpf == null || cpf.isEmpty()) {
            return true;
        }
        return Utils.cpfIsValido(cpf);
    }
}
