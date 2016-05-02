-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Erstellungszeit: 06. Mrz 2016 um 16:11
-- Server-Version: 10.1.10-MariaDB
-- PHP-Version: 7.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `hackathon`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `bank_account`
--

CREATE TABLE `bank_account` (
  `__account_id` int(11) NOT NULL,
  `pre_name` text,
  `last_name` text,
  `login_name` text,
  `balance` decimal(60,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `bank_account`
  MODIFY `__account_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2,
  ADD PRIMARY KEY (`__account_id`),
  ADD KEY `account_id` (`__account_id`);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `transaction`
--

CREATE TABLE `transaction` (
  `__transaction_id` int(11) NOT NULL,
  `source` int(11) DEFAULT NULL,
  `target` int(11) DEFAULT NULL,
  `value` int(11) DEFAULT NULL,
  `purpose` text,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `_executive_account_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `bank_account`
--ALTER TABLE `bank_account`

--
-- Indizes für die Tabelle `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`__transaction_id`),
  ADD KEY `transaction_id` (`__transaction_id`),
  ADD KEY `executive_account_id` (`_executive_account_id`),
  MODIFY `__transaction_id` int(11) NOT NULL AUTO_INCREMENT,
  ADD CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`_executive_account_id`) REFERENCES `bank_account` (`__account_id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `bank_account`
--

--
-- AUTO_INCREMENT für Tabelle `transaction`
--
-- Constraints der exportierten Tabellen
--

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
