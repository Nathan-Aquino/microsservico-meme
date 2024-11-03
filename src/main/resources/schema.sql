CREATE SEQUENCE IF NOT EXISTS sequence_meme START WITH 1 INCREMENT BY 50;

CREATE TABLE IF NOT EXISTS meme (
  id BIGINT NOT NULL,
  nome VARCHAR(255) NOT NULL,
  url_meme VARCHAR(255) NOT NULL,
  descricao VARCHAR(255) NOT NULL,
  data_cadastro DATE NOT NULL,
  categoria_meme_id BIGINT,
  usuario_id BIGINT,
  CONSTRAINT pk_meme PRIMARY KEY (id),
  CONSTRAINT FK_MEME_ON_CATEGORIA_MEME FOREIGN KEY (categoria_meme_id) REFERENCES categoria_meme (id),
  CONSTRAINT FK_MEME_ON_USUARIO FOREIGN KEY (usuario_id) REFERENCES usuario (id)
);