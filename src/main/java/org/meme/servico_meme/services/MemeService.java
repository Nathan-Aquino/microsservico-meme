package org.meme.servico_meme.services;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.meme.servico_meme.clients.CategoriaMemeClient;
import org.meme.servico_meme.clients.UsuarioClient;
import org.meme.servico_meme.dtos.MemeDTO;
import org.meme.servico_meme.entities.MemeEntity;
import org.meme.servico_meme.exceptions.CategoriaMemeNaoEncontradaException;
import org.meme.servico_meme.exceptions.UsuarioNaoEncontradoException;
import org.meme.servico_meme.repositories.MemeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class MemeService {

    private Logger logger = LoggerFactory.getLogger(MemeService.class);
    
    @Autowired
    private MemeRepository repository;

    @Autowired
    private UsuarioClient usuarioClient;

    @Autowired
    private CategoriaMemeClient categoriaMemeClient;

    public List<MemeEntity> getAllMemes () {
        return repository.findAll();
    }

    public MemeEntity saveMeme (MemeDTO meme) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");

        Long usuarioId = Long.parseLong(meme.getUsuarioId());
        Long categoriaMemeId = Long.parseLong(meme.getCategoriaMemeId());

        logger.info("Conferindo usuario de id {}",usuarioId);
        
        ResponseEntity<Void> responseUsuario = usuarioClient.verificarUsuarioPorId(usuarioId);

        if (responseUsuario.getStatusCode() == HttpStatus.NOT_FOUND){
            logger.warn("Usuario de id {} nao encontrado!");
            throw new UsuarioNaoEncontradoException();
        }

        ResponseEntity<Void> responseCatgoriaMeme = categoriaMemeClient.verificarCategoriaMemePorId(categoriaMemeId);
        
        logger.info("Conferindo categoria de meme de id {}",categoriaMemeId);

        if (responseCatgoriaMeme.getStatusCode() == HttpStatus.NOT_FOUND) {
            logger.warn("Categoria de id {} nao encontrada!");
            throw new CategoriaMemeNaoEncontradaException();
        }

        Date dataSql = new Date (formato.parse(meme.getDataCadastro()).getTime());

        MemeEntity memeEntity = new MemeEntity();

        memeEntity.setNome(meme.getNome());
        memeEntity.setDescricao(meme.getDescricao());
        memeEntity.setUrlMeme(meme.getUrlMeme());
        memeEntity.setDataCadastro(dataSql);
        memeEntity.setUsuarioId(usuarioId);
        memeEntity.setCategoriaMemeId(categoriaMemeId);

        return repository.save(memeEntity);
    }
}
