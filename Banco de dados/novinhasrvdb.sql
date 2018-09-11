-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: 11-Set-2018 às 01:15
-- Versão do servidor: 5.7.19
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `novinhasrvdb`
--
CREATE DATABASE IF NOT EXISTS `novinhasrvdb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `novinhasrvdb`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `aux_conversa`
--

DROP TABLE IF EXISTS `aux_conversa`;
CREATE TABLE IF NOT EXISTS `aux_conversa` (
  `codigoConversa` int(11) NOT NULL,
  `mensagem` varchar(500) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `conversa`
--

DROP TABLE IF EXISTS `conversa`;
CREATE TABLE IF NOT EXISTS `conversa` (
  `codigoConversa` int(11) NOT NULL AUTO_INCREMENT,
  `codigoCliente1` int(11) NOT NULL,
  `codigoCliente2` int(11) NOT NULL,
  PRIMARY KEY (`codigoConversa`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `solicitacao`
--

DROP TABLE IF EXISTS `solicitacao`;
CREATE TABLE IF NOT EXISTS `solicitacao` (
  `codigoSolicitacao` int(11) NOT NULL AUTO_INCREMENT,
  `codigoCliente1` int(11) NOT NULL,
  `codigoCliente2` int(11) NOT NULL,
  `aceitar` varchar(5) NOT NULL,
  PRIMARY KEY (`codigoSolicitacao`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `idade` int(11) NOT NULL,
  `email` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `descricao` varchar(45) DEFAULT NULL,
  `prefSexo` tinyint(4) NOT NULL,
  `prefEsporte` tinyint(4) NOT NULL,
  `prefReligioso` tinyint(4) NOT NULL,
  `prefMusica` tinyint(4) NOT NULL,
  `prefGames` tinyint(4) NOT NULL,
  `prefIdade` tinyint(4) NOT NULL,
  `caminhoImagem` varchar(150) NOT NULL,
  PRIMARY KEY (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`idusuario`, `login`, `nome`, `idade`, `email`, `senha`, `descricao`, `prefSexo`, `prefEsporte`, `prefReligioso`, `prefMusica`, `prefGames`, `prefIdade`, `caminhoImagem`) VALUES
(2, 'a', 'teste2', 1, 'a', 'a', 'Vai PQP', 0, 1, 1, 1, 1, 1, 'C:\\Users\\adeja\\Desktop\\NovinhasRV\\Fotos Clientes\\teste2.jpg'),
(3, 'b', 'b', 1, 'b', 'b', 'b', 0, 1, 1, 1, 1, 1, ''),
(4, 'FELIPE', 'FELIPE  22 CAM', 35, 'FELIPE', 'FELIPE', 'UE', 0, 1, 1, 1, 1, 1, ''),
(5, 'huehue', 'teste', 1, 'huehuehu', 'senha', 'teste', 0, 1, 1, 1, 1, 1, ''),
(6, 'ab', 'a', 1, 'a', '698DC19D489C4E4DB73E28A713EAB07B', 'a', 0, 1, 1, 1, 1, 1, ''),
(7, 'teste2', 'teste3', 30, 'teste2', 'teste2', 'Esta atualizando?, sim', 0, 1, 1, 1, 1, 1, ''),
(8, 'teste4', 'teste4', 22, 'teste4', '73BF3127FB3C9791E88A4D308171FD85', 'teste4', 0, 1, 1, 1, 1, 1, ''),
(9, 'teste5', 'teste5', 11, 'teste5', '6EE7A7F22C4024CEF59D25BE2365A5A7', 'teste5', 0, 1, 1, 1, 1, 1, ''),
(10, 'teste6', 'teste6', 11, 'teste6', '72EF529ADE0AB6DE1E7952E965052DEF', 'teste6', 0, 1, 1, 1, 1, 1, ''),
(11, 'teste7', 'teste7', 11, 'teste7', '3907E5CF2BB6D06790A6DB3F03C554FF', 'teste7', 0, 1, 1, 1, 1, 1, 'C:\\Users\\adeja\\Desktop\\NovinhasRV\\Fotos Clientes\\teste7.jpg'),
(12, 'teste8', 'teste8', 55, 'teste8', 'CC1DAE793882F1F06EFC3EE1AD5CA754', 'teste8', 0, 1, 1, 1, 1, 1, 'C:\\Users\\adeja\\Desktop\\NovinhasRV\\Fotos Clientes\\teste8.jpg'),
(13, 'teste9', 'teste9', 11, 'teste9', '31E8CC66900FEC4C72276F649A009FB8', 'teste9', 0, 1, 1, 1, 1, 1, 'C:\\Users\\adeja\\Desktop\\NovinhasRV\\Fotos Clientes\\teste9.jpg');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
