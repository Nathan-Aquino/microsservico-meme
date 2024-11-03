package org.meme.servico_meme.clients;

import org.meme.servico_meme.exceptions.CategoriaMemeNaoEncontradaException;
import org.meme.servico_meme.exceptions.UsuarioNaoEncontradoException;

import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecode implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 404) {
            if (methodKey.equals("UsuarioClient#verificarUsuarioPorId(Long)")){
                return new UsuarioNaoEncontradoException();
            } else {
                return new CategoriaMemeNaoEncontradaException();
            }
        }
        return FeignException.errorStatus(methodKey, response);
    }
}
