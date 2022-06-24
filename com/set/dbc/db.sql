-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema estoqueBr
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema estoqueBr
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `estoqueBr` DEFAULT CHARACTER SET utf8 ;
USE `estoqueBr` ;

-- -----------------------------------------------------
-- Table `estoqueBr`.`fabricante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estoqueBr`.`fabricante` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `estoqueBr`.`fornecedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estoqueBr`.`fornecedor` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `cnpj` CHAR(14) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `cnpj_UNIQUE` (`cnpj` ASC),
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `estoqueBr`.`custeio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estoqueBr`.`custeio` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `estoqueBr`.`destino`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estoqueBr`.`destino` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `estoqueBr`.`ordem_servico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estoqueBr`.`ordem_servico` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `numero` VARCHAR(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `numero_UNIQUE` (`numero` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `estoqueBr`.`unidade_medida`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estoqueBr`.`unidade_medida` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(30) NOT NULL,
  `abreviacao` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `estoqueBr`.`material`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estoqueBr`.`material` (
  `codigo` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(60) NOT NULL,
  `modelo` VARCHAR(45) NOT NULL,
  `descricao` TEXT NULL,
  `id_fabricante` INT UNSIGNED NOT NULL,
  `id_unid_med` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `material_fk0_idx` (`id_fabricante` ASC),
  INDEX `material_fk2_idx` (`id_unid_med` ASC),
  CONSTRAINT `material_fk0`
    FOREIGN KEY (`id_fabricante`)
    REFERENCES `estoqueBr`.`fabricante` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `material_fk2`
    FOREIGN KEY (`id_unid_med`)
    REFERENCES `estoqueBr`.`unidade_medida` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `estoqueBr`.`nota_fiscal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estoqueBr`.`nota_fiscal` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `numero` VARCHAR(30) NOT NULL,
  `id_destino` INT UNSIGNED NOT NULL,
  `id_fornecedor` INT UNSIGNED NOT NULL,
  `id_custeio` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `nota_fiscal_fk0_idx` (`id_destino` ASC),
  INDEX `nota_fiscal_fk1_idx` (`id_fornecedor` ASC),
  INDEX `nota_fiscal_fk2_idx` (`id_custeio` ASC),
  CONSTRAINT `nota_fiscal_fk0`
    FOREIGN KEY (`id_destino`)
    REFERENCES `estoqueBr`.`destino` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `nota_fiscal_fk1`
    FOREIGN KEY (`id_fornecedor`)
    REFERENCES `estoqueBr`.`fornecedor` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `nota_fiscal_fk2`
    FOREIGN KEY (`id_custeio`)
    REFERENCES `estoqueBr`.`custeio` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `estoqueBr`.`saida`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estoqueBr`.`saida` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `quantidade` INT NOT NULL,
  `descricao` TEXT NULL,
  `data_registro` DATETIME NOT NULL,
  `id_material` INT UNSIGNED NOT NULL,
  `id_ordem_servico` INT UNSIGNED NOT NULL,
  `id_destino` INT UNSIGNED NOT NULL,
  `id_nota` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `saida_fk0_idx` (`id_ordem_servico` ASC),
  INDEX `saida_fk1_idx` (`id_material` ASC),
  INDEX `saida_fk3_idx` (`id_destino` ASC),
  INDEX `saida_fk2_idx` (`id_nota` ASC),
  CONSTRAINT `saida_fk0`
    FOREIGN KEY (`id_ordem_servico`)
    REFERENCES `estoqueBr`.`ordem_servico` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `saida_fk1`
    FOREIGN KEY (`id_material`)
    REFERENCES `estoqueBr`.`material` (`codigo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `saida_fk3`
    FOREIGN KEY (`id_destino`)
    REFERENCES `estoqueBr`.`destino` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `saida_fk2`
    FOREIGN KEY (`id_nota`)
    REFERENCES `estoqueBr`.`nota_fiscal` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `estoqueBr`.`entrada`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estoqueBr`.`entrada` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `quantidade` INT NOT NULL,
  `data_registro` DATETIME NOT NULL,
  `id_material` INT UNSIGNED NOT NULL,
  `id_nota` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `entrada_fk1_idx` (`id_material` ASC),
  INDEX `entrada_fk0_idx` (`id_nota` ASC),
  CONSTRAINT `entrada_fk1`
    FOREIGN KEY (`id_material`)
    REFERENCES `estoqueBr`.`material` (`codigo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `entrada_fk0`
    FOREIGN KEY (`id_nota`)
    REFERENCES `estoqueBr`.`nota_fiscal` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
