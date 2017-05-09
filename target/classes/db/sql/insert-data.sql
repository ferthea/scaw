INSERT INTO usuario(NOMBRE,USERNAME,APELLIDO,CONTRASENA,TIPO,ACTIVO) VALUES  ('user1','user1','molaro','123','usuarioNormal',true);
INSERT INTO usuario(NOMBRE,USERNAME,APELLIDO,CONTRASENA,TIPO,ACTIVO) VALUES  ('user2','user2','molaro','123','usuarioNormal',true);
INSERT INTO usuario(NOMBRE,USERNAME,APELLIDO,CONTRASENA,TIPO,ACTIVO) VALUES  ('denegado2','user3','molaro','123','usuarioNormal',false);
INSERT INTO usuario(NOMBRE,USERNAME,APELLIDO,CONTRASENA,TIPO,ACTIVO) VALUES  ('axel','user4','molaro','axel123','usuarioAdministrador',true);
INSERT INTO usuario(NOMBRE,USERNAME,APELLIDO,CONTRASENA,TIPO,ACTIVO) VALUES  ('axel2','user5','molaro2','axel1232','usuarioAdministrador',true);

INSERT INTO tarea(NOMBRE,CREADOR_USERNAME,DESCRIPCION,ACCESO,ESTADO) VALUES ('Limpieza','user4','barrer la cocina','privada','pendiente');
INSERT INTO tarea(NOMBRE,CREADOR_USERNAME,DESCRIPCION,ACCESO,ESTADO) VALUES ('tareaNoPendiente','user2','barrer la cocina','privada','completa');
INSERT INTO tarea(NOMBRE,CREADOR_USERNAME,DESCRIPCION,ACCESO,ESTADO) VALUES ('Tarea 2 publica','user1','limpiar el baño','publica','pendiente');

INSERT INTO accedetarea(MODO,IDTAREA,IDUSUARIO) VALUES ('lectura','1','1');
INSERT INTO accedetarea(MODO,IDTAREA,IDUSUARIO) VALUES ('lectura','2','1');
INSERT INTO accedetarea(MODO,IDTAREA,IDUSUARIO) VALUES ('lectura','3','2');



--COMMIT;

