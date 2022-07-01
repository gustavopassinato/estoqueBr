select m.nome,
	sum(e.quantidade) as quantidade
from entrada e
join material m on (m.codigo = e.id_material)
group by m.codigo;

select * from entrada;

select d.nome,
	sum(e.quantidade),
    m.nome
from entrada e 
join material m on (m.codigo = e.id_material)
join nota_fiscal nf on (nf.id = e.id_nota)
join destino d on (nf.id_destino = d.id);


alter table nota_fiscal 
add constraint nota_fiscal_constraint unique (numero);



DELIMITER $$

CREATE TRIGGER Trg_entrada_material AFTER INSERT
ON `estoqueBr`.`entrada`
FOR EACH ROW 
BEGIN 
	SET @DESTINO_ORIGEM_ID = (SELECT `estoqueBr`.`nota_fiscal`.`id_destino` 
            FROM `estoqueBr`.`nota_fiscal`
            WHERE `estoqueBr`.`nota_fiscal`.`id` = NEW.`id_nota`);
	IF (@DESTINO_ORIGEM_ID IS NOT NULL) THEN 
		SET @MATERIAL_ID = (SELECT `estoqueBr`.`estoque`.`id` 
		FROM `estoqueBr`.`estoque` 
		WHERE `estoqueBr`.`estoque`.`id_material` = NEW.`id_material`
			AND `estoqueBr`.`estoque`.`id_destino` = @DESTINO_ORIGEM_ID );
		IF (@MATERIAL_ID IS NOT NULL) THEN
			UPDATE `estoqueBr`.`estoque` 
            SET `estoqueBr`.`estoque`.`quantidade` = `estoqueBr`.`estoque`.`quantidade`+ NEW.`quantidade`
            WHERE `estoqueBr`.`estoque`.`id`= @MATERIAL_ID;
		ELSE
			INSERT INTO `estoqueBr`.`estoque` (`estoqueBr`.`estoque`.`id_material`, `estoqueBr`.`estoque`.`id_destino`, `estoqueBr`.`estoque`.`desvio`, `estoqueBr`.`estoque`.`quantidade`)
			VALUES (NEW.`id_material`, @DESTINO_ORIGEM_ID, 0, NEW.`quantidade`);
		END IF;
	END IF;
END$$

CREATE TRIGGER Trg_saida_material AFTER INSERT 
ON `estoqueBr`.`saida`
FOR EACH ROW 
BEGIN
	SET @DESTINO_NOTA_ID = (SELECT `estoqueBr`.`nota_fiscal`.`id_destino` 
		FROM `estoqueBr`.`nota_fiscal`
		WHERE `estoqueBr`.`nota_fiscal`.`id` = NEW.`id_nota`);
    IF (@DESTINO_NOTA_ID != NEW.`id_destino`) THEN
		SET @EH_DESVIO = 1;
        INSERT INTO `estoqueBr`.`estoque` (`estoqueBr`.`estoque`.`id_material`, `estoqueBr`.`estoque`.`id_destino`, `estoqueBr`.`estoque`.`desvio`, `estoqueBr`.`estoque`.`quantidade`)
        VALUES (NEW.`id_material`, NEW.`id_destino`, @EH_DESVIO , -1*NEW.`quantidade`);
    END IF;
	
	SET @MATERIAL_ID = (SELECT `estoqueBr`.`estoque`.`id`  
		FROM `estoqueBr`.`estoque` 
		WHERE `estoqueBr`.`estoque`.`id_material` = NEW.`id_material`
			AND `estoqueBr`.`estoque`.`id_destino` = @DESTINO_NOTA_ID);
		 
	UPDATE `estoqueBr`.`estoque` 
	SET `estoqueBr`.`estoque`.`quantidade` = `estoqueBr`.`estoque`.`quantidade`- NEW.`quantidade`;
END$$

DELIMITER ;


#DROP TRIGGER Trg_entrada_material;




select m.nome,
	sum(e.quantidade) as quantidade,
    m.modelo
from entrada e
join material m on (m.codigo = e.id_material)
group by m.codigo;

select m.nome,
	m.modelo,
    sum(e.quantidade) as quantidade,
    d.nome
from entrada e 
join material m on (m.codigo = e.id_material)
join nota_fiscal nf on (e.id_nota = nf.id)
join destino d on (nf.id_destino = d.id)
where d.nome = "Estoque interno DTEL"
group by m.codigo; 

SELECT * FROM entrada;

CREATE TABLE `saida` (
   `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
   `quantidade` int(10) unsigned NOT NULL,
   `descricao` text,
   `data_registro` datetime NOT NULL,
   `id_material` bigint(20) unsigned NOT NULL,
   `id_ordem_servico` bigint(20) unsigned NOT NULL,
   `id_destino` bigint(20) unsigned NOT NULL,
   `id_nota` bigint(20) unsigned NOT NULL,
   PRIMARY KEY (`id`),
   KEY `saida_fk0_idx` (`id_ordem_servico`),
   KEY `saida_fk1_idx` (`id_material`),
   KEY `saida_fk3_idx` (`id_destino`),
   KEY `saida_fk2_idx` (`id_nota`),
   CONSTRAINT `saida_fk0` FOREIGN KEY (`id_ordem_servico`) REFERENCES `ordem_servico` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
   CONSTRAINT `saida_fk1` FOREIGN KEY (`id_material`) REFERENCES `material` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE,
   CONSTRAINT `saida_fk2` FOREIGN KEY (`id_nota`) REFERENCES `nota_fiscal` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
   CONSTRAINT `saida_fk3` FOREIGN KEY (`id_destino`) REFERENCES `destino` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
 ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8


CREATE TABLE `nota_fiscal` (
   `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
   `numero` varchar(30) NOT NULL,
   `id_destino` bigint(20) unsigned NOT NULL,
   `id_fornecedor` bigint(20) unsigned NOT NULL,
   `id_custeio` bigint(20) unsigned NOT NULL,
   PRIMARY KEY (`id`),
   KEY `nota_fiscal_fk0_idx` (`id_destino`),
   KEY `nota_fiscal_fk1_idx` (`id_fornecedor`),
   KEY `nota_fiscal_fk2_idx` (`id_custeio`),
   CONSTRAINT `nota_fiscal_fk0` FOREIGN KEY (`id_destino`) REFERENCES `destino` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
   CONSTRAINT `nota_fiscal_fk1` FOREIGN KEY (`id_fornecedor`) REFERENCES `fornecedor` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
   CONSTRAINT `nota_fiscal_fk2` FOREIGN KEY (`id_custeio`) REFERENCES `custeio` (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8