package org.meme.servico_meme.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemeDTO {
    private String id;
    private String nome;
    private String urlMeme;
    private String descricao;
    private String dataCadastro;
    private String categoriaMemeId;
    private String usuarioId;
}
