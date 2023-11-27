-- Banco de dados: segdb
--
CREATE DATABASE segdb;
USE segdb;

CREATE TABLE hemoglobina (
  id int(11) PRIMARY KEY AUTO_INCREMENT,
  resultado varchar(80) NOT NULL
);


CREATE TABLE medicos (
  id int(11) Primary key AUTO_INCREMENT,
  nome varchar(255) NOT NULL,
  cpf varchar(11) NOT NULL,
  especialidade varchar(255) DEFAULT NULL
);

CREATE TABLE pacientes (
  id int(11) Primary key AUTO_INCREMENT,
  nome varchar(255) NOT NULL,
  cpf varchar(11) NOT NULL
);


CREATE TABLE senhas (
  id int(11) Primary key AUTO_INCREMENT,
  chave_secreta varchar(255) NOT NULL
);


CREATE TABLE usuarios (
  login varchar(80) NOT NULL,
  senha varchar(255) NOT NULL
);


CREATE TABLE valorespadroes (
  id int(11) NOT NULL,
  estado varchar(255) DEFAULT NULL,
  limite_max int(11) NOT NULL,
  limite_min int(11) NOT NULL,
  unidade varchar(50) NOT NULL
);

INSERT INTO valorespadroes (id,estado, limite_max, limite_min, unidade)
VALUES (1,'Hemoglobina normal', 18, 14, 'g/dL');


