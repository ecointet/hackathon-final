-- HPE SILCA HACKATHON 2016			--
-- MY OWN PRIVATE BANK				--
-- SUPPORT: etienne.cointet@hpe.com --

-- phpMyAdmin SQL Dump
-- version 4.5.0.2
-- http://www.phpmyadmin.net
--
-- Host: 10.0.0.4:3306
-- Generation Time: May 02, 2016 at 04:46 PM
-- Server version: 5.5.43-0ubuntu0.12.04.1-log
-- PHP Version: 5.5.23-1+deb.sury.org~precise+2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `d9d0a933534f0489bb81dbaa9da916c19`
--

-- --------------------------------------------------------

--
-- Table structure for table `bank_account`
--

CREATE TABLE `bank_account` (
  `id` int(11) NOT NULL  AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `pwd` varchar(15) NOT NULL,
  `balance` float NOT NULL DEFAULT '0',
	PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Information regarding the users and credentials';

--
-- Dumping data for table `bank_account`
--

INSERT INTO `bank_account` (`id`, `name`, `email`, `pwd`, `balance`) VALUES
(1, 'Lara Croft', 'lara.croft@wanadoo.fr', 'lara', 55000),
(2, 'Mario Bross', 'mario.bross@voila.fr', 'mario', 2500);

-- --------------------------------------------------------

--
-- Table structure for table `bank_transac`
--

CREATE TABLE `bank_transac` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id transac',
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Autofill Timestamp',
  `amount` int(11) NOT NULL,
  `from_id` int(11) NOT NULL COMMENT 'ID account from',
  `to_id` int(11) NOT NULL COMMENT 'ID account to',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='All bank transactions';

--
-- Dumping data for table `bank_transac`
--

INSERT INTO `bank_transac` ( `timestamp`, `amount`, `from_id`, `to_id`) VALUES
('2016-05-02 14:45:53', 500, 1, 2),
('2016-05-02 14:46:05', 150, 1, 2);
