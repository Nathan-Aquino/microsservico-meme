package org.meme.servico_meme.controllers;

import java.text.ParseException;
import java.util.List;

import org.meme.servico_meme.dtos.MemeDTO;
import org.meme.servico_meme.entities.MemeEntity;
import org.meme.servico_meme.exceptions.CategoriaMemeNaoEncontradaException;
import org.meme.servico_meme.exceptions.UsuarioNaoEncontradoException;
import org.meme.servico_meme.services.MemeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/meme")
public class MemeController {

    private Logger logger = LoggerFactory.getLogger(MemeService.class);

    @Autowired
    private MemeService service;

    @GetMapping
    public List<MemeEntity> getAllMemes () {
        logger.info("Requisitando todos os memes");
        List<MemeEntity> response = service.getAllMemes();
        logger.info("Total de memes encontrados: {}", response.size());
        return response;
    }

    @PostMapping
    public ResponseEntity<?> postMeme(@RequestBody MemeDTO memeDTO) throws ParseException {
        try {
            MemeEntity memeEntity = service.saveMeme(memeDTO);
            logger.info("Meme de id {} salvo com sucesso", memeEntity.getId());
            return ResponseEntity.status(HttpStatus.OK).body(memeEntity);
        } catch (UsuarioNaoEncontradoException|CategoriaMemeNaoEncontradaException e) {
            if (e.getClass().equals(UsuarioNaoEncontradoException.class)) {
                logger.warn("Erro, usuario nao existe!");
            } else {
                logger.warn("Erro, categoria nao existe!");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
}
