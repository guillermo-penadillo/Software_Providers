INSERT IGNORE INTO tb_categorias (nombre_cat, desc_cat) VALUES ('Antivirus', 'Software de seguridad para proteger tu computadora');
INSERT IGNORE INTO tb_categorias (nombre_cat, desc_cat) VALUES ('Diseño Gráfico', 'Herramientas de diseño para profesionales creativos');
INSERT IGNORE INTO tb_categorias (nombre_cat, desc_cat) VALUES ('Productividad', 'Software de oficina para aumentar la eficiencia laboral');
INSERT IGNORE INTO tb_categorias (nombre_cat, desc_cat) VALUES ('Desarrollo Web', 'Herramientas para crear aplicaciones web y sitios web');
INSERT IGNORE INTO tb_categorias (nombre_cat, desc_cat) VALUES ('Sistemas Operativos', 'Sistemas operativos para computadoras');

INSERT IGNORE INTO tb_tipos_empleado (nombre_tipo, desc_tipo) VALUES ('Administrador', 'Encargado de la gestión y supervisión general');
INSERT IGNORE INTO tb_tipos_empleado (nombre_tipo, desc_tipo) VALUES ('Mantenedor', 'Encargado del mantenimiento de productos y categorías');

INSERT IGNORE INTO rol (id,nombre) VALUES (1, 'ROLE_CLIENTE');
INSERT IGNORE INTO rol (id,nombre) VALUES (2, 'ROLE_ADMIN');
INSERT IGNORE INTO rol (id,nombre) VALUES (3, 'ROLE_MANTENEDOR');
