package org.meme.servico_meme.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "servico-categoria-meme", url = "http://localhost:8081/categoria-meme")
public interface CategoriaMemeClient {
    @GetMapping("/{id}")
    ResponseEntity<Void> verificarCategoriaMemePorId(@PathVariable("id") Long id);
}
