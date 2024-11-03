package org.meme.servico_meme.exceptions;

public class CategoriaMemeNaoEncontradaException extends RuntimeException {
    public CategoriaMemeNaoEncontradaException() {
        super("Categoria não encontrada.");
    }
}
