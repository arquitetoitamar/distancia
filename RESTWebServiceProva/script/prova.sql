USE prova;

CREATE TABLE endereco (
  id int NOT NULL AUTO_INCREMENT,
  codigo_cidade varchar(45) DEFAULT NULL,
  longitude varchar(45) DEFAULT NULL,
  latitude varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- Insere 10 cidades
INSERT INTO endereco(codigo_cidade, longitude, latitude)
  VALUES('1', '-46.62913200000003', '-23.5471634');
INSERT INTO endereco(codigo_cidade, longitude, latitude)
  VALUES('2', '-46.63797820000002', '-23.5464833');
INSERT INTO endereco(codigo_cidade, longitude, latitude)
  VALUES('3', '-46.63892820000001', '-23.548348');
INSERT INTO endereco(codigo_cidade, longitude, latitude)
  VALUES('4', '-46.64323139999999', '-23.5426444');
INSERT INTO endereco(codigo_cidade, longitude, latitude)
  VALUES('5', '-46.63431400000002', '-23.5503342');

-- Select 
DELIMITER //
CREATE PROCEDURE listarCidadesPelaDistancia(IN lat varchar(45), IN longi varchar(45), IN dist int (12))
BEGIN
	SELECT *, 
		((3956 * 2 * ASIN(SQRT(POWER(SIN((abs(lat) - abs(dest.latitude)) * pi()/180 / 2),2) + 
			COS(abs(lat) * pi()/180 ) * 
			COS(abs(dest.latitude) * pi()/180) * 
			POWER(SIN((abs(longi) - abs(dest.longitude)) * pi()/180 / 2), 2))) ) * 1.609344) as distancia
	FROM endereco dest
	HAVING distancia < dist
	ORDER BY distancia 
	LIMIT 2;
END //
DELIMITER ;
DELIMITER //
CREATE PROCEDURE calcularDistancia(IN lat1 varchar(45), 
								   IN longi1 varchar(45), 
								   IN lat2 varchar(45), 
								   IN longi2 varchar(45))
BEGIN
	SELECT 
		((3956 * 2 * ASIN(SQRT(POWER(SIN((abs(lat1) - abs(lat2)) * pi()/180 / 2),2) + 
			COS(abs(lat1) * pi()/180 ) * 
			COS(abs(lat2) * pi()/180) * 
			POWER(SIN((abs(longi1) - abs(longi2)) * pi()/180 / 2), 2))) ) * 1.609344) as distancia
	FROM endereco dest
	LIMIT 1;
END //
DELIMITER ;

CALL calcularDistancia('-23.5454668', '-46.633525399999996', '-23.5464833' , '-46.63797820000002');


CALL listarCidadesPelaDistancia('-23.5454668', '-46.633525399999996', 10)