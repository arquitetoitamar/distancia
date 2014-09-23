Rest Para Pesquisar distancia entre cidades
=================

API para exemplificar uso da arquitetura RestFul 
Objetivo: calcular distancia entre cidades por código.

Testando Serviços
--------

* URLS para teste
    - http://javatechbrasil.com.br/RESTWebServiceProva/json/distancia/listarTodas
    - http://javatechbrasil.com.br/RESTWebServiceProva/json/distancia/calcularDistancia/{cod1}/{cod2}
    - http://javatechbrasil.com.br/RESTWebServiceProva/json/distancia/obterCidadesMaisPerto
    
Scripts Banco
----------------

O script do banco de dados está na pasta script dentro do projeto


A fórmula de Haversine e distância entre dois pontos
----------------

O calculo é feito no Banco de dados 
    
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


Desenvolvedor 
----------------

Itamar Rocha
isantos.ads@gmail.com
    