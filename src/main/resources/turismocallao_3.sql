/*Developed by: Oswaldo Santibanez*/
DROP DATABASE IF EXISTS TURISMOCALLAO_3;
CREATE DATABASE TURISMOCALLAO_3;
USE TURISMOCALLAO_3;

# TABLA TIPO USUARIO
CREATE TABLE ROL(
IDROL INT(6) PRIMARY KEY AUTO_INCREMENT NOT NULL,
NOMROL VARCHAR(30) NOT NULL
);

# TABLA USUARIO
CREATE TABLE USUARIO(
CODUSUARIO INT(6) PRIMARY KEY AUTO_INCREMENT NOT NULL,
NOMUSUARIO VARCHAR(100) NOT NULL,
NOMBRESUSUARIO VARCHAR(100) NOT NULL,
APELLIDOSUSUARIO VARCHAR(100) NOT NULL,
EMAILUSUARIO VARCHAR(100) NOT NULL,
TELEUSUARIO VARCHAR(100) NOT NULL,
PASSUSUARIO VARCHAR(100) NOT NULL,
ESTADOUSUARIO BOOLEAN DEFAULT 1 NOT NULL
);

CREATE TABLE USUARIO_ROL(
CODUSUARIO INT(6) NOT NULL,
IDROL INT(6) NOT NULL,
CONSTRAINT USER_ROLE_PK PRIMARY KEY (CODUSUARIO, IDROL),
CONSTRAINT FK_USUARIO FOREIGN KEY (CODUSUARIO) REFERENCES USUARIO(CODUSUARIO),
CONSTRAINT FK_TIPOUSUARIO FOREIGN KEY (IDROL) REFERENCES ROL(IDROL)
);

# TABLA ESTADO
CREATE TABLE ESTADO(
CODESTADO INT(6) PRIMARY KEY AUTO_INCREMENT NOT NULL,
NOMESTADO VARCHAR(100) NOT NULL
);

CREATE TABLE CATEGORIA(
CODCATEGORIA INT(6) PRIMARY KEY AUTO_INCREMENT NOT NULL,
NOMCATEGORIA VARCHAR(50) NOT NULL,
DESCATEGORIA VARCHAR(400) NOT NULL
);

CREATE TABLE DESTINO(
CODDESTINO INT(6) PRIMARY KEY AUTO_INCREMENT NOT NULL,
NOMDESTINO VARCHAR(50) NOT NULL,
DESDESTINO VARCHAR(400) NOT NULL,
PREDESTINO FLOAT,
CODCATEGORIA INT,
CONSTRAINT FK_CATEGORIA FOREIGN KEY (CODCATEGORIA) REFERENCES CATEGORIA(CODCATEGORIA)
);

CREATE TABLE PAGO(
CODPAGO INT(6) PRIMARY KEY AUTO_INCREMENT NOT NULL,
TIPOPAGO VARCHAR(50) NOT NULL
);

CREATE TABLE RESERVA(
CODRESERVA INT(6) PRIMARY KEY AUTO_INCREMENT NOT NULL,
FECHAREGISTRO TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
FECHARESERVA DATE NOT NULL,
HORARESERVA TIME NOT NULL,
NUMEROPERSONASRESERVA INT NOT NULL,
INFRESERVA VARCHAR(400) NOT NULL,
CODUSUARIO INT(6) NOT NULL,
CODDESTINO INT(6) NOT NULL,
CODESTADO INT(6) NOT NULL,
CODPAGO INT(6) NOT NULL,
CONSTRAINT FK_USUARIO_ FOREIGN KEY (CODUSUARIO) REFERENCES USUARIO(CODUSUARIO),
CONSTRAINT FK_DESTINO FOREIGN KEY (CODDESTINO) REFERENCES DESTINO(CODDESTINO),
CONSTRAINT FK_ESTADO FOREIGN KEY (CODESTADO) REFERENCES ESTADO(CODESTADO),
CONSTRAINT FK_PAGO FOREIGN KEY (CODPAGO) REFERENCES PAGO(CODPAGO)
);

/*VALUES PARA USUARIOS*/
INSERT INTO ROL (NOMROL) VALUES ("ROLE_ADMIN");
INSERT INTO ROL (NOMROL) VALUES ("ROLE_AGENTE");

/*VALUES PARA USUARIOS*/
INSERT INTO USUARIO VALUES  (null, "osanti", "oswaldo", "santibanez", "oswaldo@gmail.com", "998008615", "$2a$10$I0UhvpP10NYjrZbtZCZ0EONm8kSq7hUBHrMTwIFzIoAdEcDTr0DOe", true);
INSERT INTO USUARIO VALUES  (null, "spreciado", "sara", "preciado", "sara@gmail.com", "998008615", "$2a$10$uIKArLTWRXrrybZYM5NY0.EM/eDO.73f9n7c8S2sbjKV00jxCXu/q", true);

INSERT INTO USUARIO_ROL VALUES (1, 1);
INSERT INTO USUARIO_ROL VALUES (2, 2);

/*VALUES PARA PAGOS*/
INSERT INTO PAGO VALUES(1, "Efectivo");
INSERT INTO PAGO VALUES(2, "Transferencia");
INSERT INTO PAGO VALUES(3, "Pago efectivo");
INSERT INTO PAGO VALUES(4, "Otros");

/*VALUES PARA ESTADO*/
INSERT INTO ESTADO VALUES(1, "Reservado");
INSERT INTO ESTADO VALUES(2, "En ejecución");
INSERT INTO ESTADO VALUES(3, "Finalizado");
INSERT INTO ESTADO VALUES(4, "Cancelado");

/*VALUES PARA CATEGORIA*/
INSERT INTO CATEGORIA VALUES (null, "Histórico", "Historia de la provincia del callao y como nace urbanísticamente.");
INSERT INTO CATEGORIA VALUES (null, "Arqueológico", "Lugares y complejos arqueológicos de la provincia.");
INSERT INTO CATEGORIA VALUES (null, "Entretenimiento", "Lugares para visitar en familia y pasar una día hermoso.");

/*VALUES PARA DESTINO*/
INSERT INTO DESTINO VALUES(1, "Fortaleza del Real Felipe", "La Fortaleza del Real Felipe es una edificación militar construida en el siglo XVIII en la bahía del Callao para defender el puerto contra los ataques de piratas y corsarios.", 100.00, 1);
INSERT INTO DESTINO VALUES(2, "Museo Naval del Perú", "El Museo Naval del Perú es un museo naval situado en la ciudad del Callao. Es administrado por la Marina de Guerra del Perú. El museo está principalmente dedicado a la historia marítima del Perú.", 80.00, 1);
INSERT INTO DESTINO VALUES(3, "Zona Monumental del Callao", "La Zona Monumental del Callao es el casco histórico en el distrito del Callao, en el Provincia Constitucional del Callao.", 60.00, 3);
INSERT INTO DESTINO VALUES(4, "Huaca el Paraíso", "El Paraíso es el nombre moderno de un complejo arqueológico monumental del Periodo Arcaico Tardío (3500-1800 a. C.)", 30.00, 2);
INSERT INTO DESTINO VALUES(5, "Museo de Sitio Naval Submarino Abtao", "Submarino anclado tipo Sierra que se construyó en la década de 1950 y ahora funciona como museo.", 45.00, 1);
INSERT INTO DESTINO VALUES(6, "Humedales de Ventanilla", "El Área de conservación regional Humedales de Ventanilla es un área protegida en el Perú. Se encuentra en la región Callao, en la provincia de Provincia Constitucional del Callao.​", 30.00, 2);
INSERT INTO DESTINO VALUES(7, "Plaza Matriz del Callao", "Considerado patrimonio cultural de la nación, el Centro Historico del Callao fue testigo de importantes hechos históricos.", 30.00, 1);
INSERT INTO DESTINO VALUES(8, "Isla El Frontón", "El Frontón es una pequeña isla ubicada en el Océano Pacífico, cerca de la costa de la ciudad de El Callao, en el Perú. Se le conoce también como Isla Callao.", 85.00, 3);
INSERT INTO DESTINO VALUES(9, "Isla Cavinzas", "Islas Cavinzas son un conjunto de islotes ubicados en el mar del Callao, al noroeste de islas Palomino.", 85, 3);
INSERT INTO DESTINO VALUES(10, "Balneario de Chucuito", "Es un balneario pintoresco del Callao. Sus calles están pintadas con colores vivaces y las viviendas también. Muestra construcciones del siglo IXX y XX, hechas en madera y adobe", 25.00, 3);

/*VALUES PARA RESERVA*/
INSERT INTO RESERVA VALUES(null, "2023-05-08", "2023-06-15", "14:00:00", 5, "Si el tour me va bien, los recomendaré", 1, 1, 1, 1);
INSERT INTO RESERVA VALUES(null, "2023-06-03", "2023-06-18", "11:00:00", 3, "Me gustaría que la atención sea personalizada.", 1, 1, 1, 1);
INSERT INTO RESERVA VALUES(null, "2023-04-30", "2023-06-20", "10:00:00", 2, "Somos un adulto y un niño y 7 años", 1, 1, 1, 1);
INSERT INTO RESERVA VALUES(null, "2023-06-02", "2023-06-03", "14:00:00", 1, "Lo vi en internet, espero que todo salga genial", 1, 1, 1, 1);

select * from destino;
select * from categoria;
select * from estado;
select * from reserva;
select * from pago;
select * from rol;
select * from usuario;
select * from usuario_rol;

delete from usuario where codusuario = 2;
delete from rol where idrol = 1;

