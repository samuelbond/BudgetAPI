-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 23, 2014 at 10:04 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `budget_app`
--

-- --------------------------------------------------------

--
-- Table structure for table `account_balance`
--

CREATE TABLE IF NOT EXISTS `account_balance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` int(11) NOT NULL,
  `balance` bigint(20) DEFAULT NULL,
  `last_balance` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sau1qckyt3tw98tgwe3rkipif` (`account`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `account_balance`
--

INSERT INTO `account_balance` (`id`, `account`, `balance`, `last_balance`) VALUES
(3, 3, -13000, 0),
(4, 4, 4835, 8000),
(5, 5, 480000, 500000);

-- --------------------------------------------------------

--
-- Table structure for table `account_transactions`
--

CREATE TABLE IF NOT EXISTS `account_transactions` (
  `transaction_id` varchar(255) NOT NULL,
  `transaction_amount` varchar(255) DEFAULT NULL,
  `transaction_date` varchar(255) DEFAULT NULL,
  `transaction_name` varchar(255) DEFAULT NULL,
  `account` int(11) DEFAULT NULL,
  `transaction_type` int(11) DEFAULT NULL,
  `budget` int(11) DEFAULT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `FK_934b8proyh01bcvi841360c6p` (`account`),
  KEY `FK_t4vmdq49vwkb7p0fykv6e2yd3` (`transaction_type`),
  KEY `budget` (`budget`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `apps`
--

CREATE TABLE IF NOT EXISTS `apps` (
  `appId` varchar(255) NOT NULL,
  `app_key` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`appId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `bank_account`
--

CREATE TABLE IF NOT EXISTS `bank_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_name` varchar(255) DEFAULT NULL,
  `account_number` bigint(20) DEFAULT NULL,
  `currency` int(11) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m88qac9h6qewqpajho429juyu` (`currency`),
  KEY `FK_ss4uej5gx2a07srb540l15s21` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `bank_account`
--

INSERT INTO `bank_account` (`id`, `account_name`, `account_number`, `currency`, `user_id`) VALUES
(3, 'Samuel Personal', 4578690, 1, '3JD78355'),
(4, 'PlatiTech Limited', 30009000, 1, '3JD78355'),
(5, 'Samuel Personal', 453009000, 2, '3JD78355');

-- --------------------------------------------------------

--
-- Table structure for table `budgets`
--

CREATE TABLE IF NOT EXISTS `budgets` (
  `budget_id` int(11) NOT NULL AUTO_INCREMENT,
  `budget_name` varchar(45) NOT NULL,
  `budget_description` varchar(255) NOT NULL,
  `budget_max_amount` bigint(20) NOT NULL,
  `bank_account` int(11) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  PRIMARY KEY (`budget_id`),
  KEY `bank_account` (`bank_account`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `currencies`
--

CREATE TABLE IF NOT EXISTS `currencies` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(3) NOT NULL,
  `country` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_5r2dfxl1m7vus47ma0y05sflt` (`code`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=50 ;

--
-- Dumping data for table `currencies`
--

INSERT INTO `currencies` (`id`, `code`, `country`) VALUES
(1, 'GBP', 'UK'),
(2, 'HUF', 'HU'),
(3, 'USD', 'US'),
(4, 'EUR', 'EU'),
(6, 'AED', 'United Arab Emirates Dirhams'),
(7, 'AFN', 'Afghanistan Afghanis'),
(8, 'ALL', 'Albania Leke'),
(9, 'AMD', 'Armenia Drams'),
(10, 'ANG', 'Netherlands Antilles Guilders'),
(11, 'AOA', 'Angola Kwanza'),
(12, 'ARS', 'Argentina Pesos'),
(13, 'AUD', 'Australia Dollars'),
(14, 'AWG', 'Aruba Guilders'),
(15, 'AZN', 'Azerbaijan New Manats'),
(16, 'BAM', 'Bosnia and Herzegovina Convertible Marka'),
(17, 'BBD', 'Barbados Dollars'),
(18, 'BDT', 'Bangladesh Taka'),
(19, 'BGN', 'Bulgaria Leva'),
(20, 'BHD', 'Bahrain Dinars'),
(21, 'BIF', 'Burundi Francs'),
(22, 'BMD', 'Bermuda Dollars'),
(23, 'BND', 'Brunei Dollars'),
(24, 'BOB', 'Bolivia Bolivianos'),
(25, 'BRL', 'Brazil Reais'),
(26, 'BSD', 'Bahamas Dollars'),
(27, 'BTN', 'Bhutan Ngultrum'),
(28, 'BWP', 'Botswana Pulas'),
(29, 'BYR', 'Belarus Rubles'),
(30, 'BZD', 'Belize Dollars'),
(31, 'CAD', 'Canada Dollars'),
(32, 'CDF', 'Congo/Kinshasa Francs'),
(33, 'CHF', 'Switzerland Francs'),
(34, 'CLP', 'Chile Pesos'),
(35, 'CNY', 'China Yuan Renminbi'),
(36, 'COP', 'Colombia Pesos'),
(37, 'CRC', 'Costa Rica Colones'),
(38, 'CUC', 'Cuba Convertible Pesos'),
(39, 'CUP', 'Cuba Pesos'),
(40, 'CVE', 'Cape Verde Escudos'),
(41, 'CZK', 'Czech Republic Koruny'),
(42, 'DJF', 'Djibouti Francs'),
(43, 'DKK', 'Denmark Kroner'),
(44, 'DOP', 'Dominican Republic Pesos'),
(45, 'DZD', 'Algeria Dinars'),
(46, 'EEK', 'Estonia Krooni'),
(47, 'EGP', 'Egypt Pounds'),
(48, 'ERN', 'Eritrea Nakfa'),
(49, 'ETB', 'Ethiopia Birr');

-- --------------------------------------------------------

--
-- Table structure for table `transaction_category`
--

CREATE TABLE IF NOT EXISTS `transaction_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `transaction_category`
--

INSERT INTO `transaction_category` (`id`, `category_name`) VALUES
(1, 'CREDIT'),
(2, 'DEBIT');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `user_id` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `email`, `fullname`, `password`) VALUES
('3JD78355', 'samuelizuchi@gmail.com', 'Samuel A', '07ad967150aa5d60ea469be9ac0e6ad07c798b4a8c19bc9beab01a02d444ea661ea56542a9258d689db72c8f6bdbccff2b53253f0b7b6bfd242c2780867fdd43');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `account_balance`
--
ALTER TABLE `account_balance`
  ADD CONSTRAINT `account_balance_ibfk_2` FOREIGN KEY (`account`) REFERENCES `bank_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `account_transactions`
--
ALTER TABLE `account_transactions`
  ADD CONSTRAINT `account_transactions_ibfk_1` FOREIGN KEY (`account`) REFERENCES `bank_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `account_transactions_ibfk_2` FOREIGN KEY (`budget`) REFERENCES `budgets` (`budget_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_t4vmdq49vwkb7p0fykv6e2yd3` FOREIGN KEY (`transaction_type`) REFERENCES `transaction_category` (`id`);

--
-- Constraints for table `bank_account`
--
ALTER TABLE `bank_account`
  ADD CONSTRAINT `FK_m88qac9h6qewqpajho429juyu` FOREIGN KEY (`currency`) REFERENCES `currencies` (`id`),
  ADD CONSTRAINT `FK_ss4uej5gx2a07srb540l15s21` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `budgets`
--
ALTER TABLE `budgets`
  ADD CONSTRAINT `budgets_ibfk_1` FOREIGN KEY (`bank_account`) REFERENCES `bank_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `budgets_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
