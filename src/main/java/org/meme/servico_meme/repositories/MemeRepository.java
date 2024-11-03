package org.meme.servico_meme.repositories;

import org.meme.servico_meme.entities.MemeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemeRepository extends JpaRepository<MemeEntity, Long> {

}
