CREATE DATABASE IF NOT EXISTS museu;

USE museu;

CREATE TABLE IF NOT EXISTS autor (
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nome varchar(100) DEFAULT NULL,
	nacionalidade varchar(100) DEFAULT NULL,
	nascimento date,
	falecimento date DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS funcionario (
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nome varchar(100) DEFAULT NULL,
	cpf varchar(50) DEFAULT NULL,
	nascimento date,
	telefone varchar(50) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS obra (
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	titulo varchar(100) DEFAULT NULL,
	descricao varchar(250) DEFAULT NULL,
	periodo varchar(50) DEFAULT NULL,
	autor_id int,
	CONSTRAINT `fk_obra_autor`
		FOREIGN KEY (autor_id) REFERENCES autor (id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT
);

CREATE TABLE IF NOT EXISTS visitante (
	cpf varchar(11) NOT NULL PRIMARY KEY,
	nome varchar(100) DEFAULT NULL,
	nascimento date
);

CREATE TABLE IF NOT EXISTS tour (
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	data date,
	funcionario_id int NOT NULL,
	CONSTRAINT `fk_visita_funcionario`
		FOREIGN KEY (funcionario_id) REFERENCES funcionario (id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT
);


CREATE TABLE IF NOT EXISTS visita (
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	visitante_id varchar(11) NOT NULL,
	CONSTRAINT `fk_visita_visitante`
		FOREIGN KEY (visitante_id) REFERENCES visitante (cpf)
		ON DELETE CASCADE
		ON UPDATE RESTRICT,
	tour_id int NOT NULL,
	CONSTRAINT `fk_visita_tour`
		FOREIGN KEY (tour_id) REFERENCES tour (id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT,
	data date
);



