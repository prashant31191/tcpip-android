-- phpMyAdmin SQL Dump
-- version 3.5.8.2
-- http://www.phpmyadmin.net
--
-- Host: sql303.byethost7.com
-- Generation Time: Dec 25, 2017 at 12:11 PM
-- Server version: 5.6.35-81.0
-- PHP Version: 5.3.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `b7_21131689_socialapp`
--

-- --------------------------------------------------------

--
-- Table structure for table `devices`
--

CREATE TABLE IF NOT EXISTS `devices` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `token` text NOT NULL,
  `datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `devices`
--

INSERT INTO `devices` (`id`, `email`, `token`, `datetime`) VALUES
(1, 'asdasd@asd.as', 'asdasdasd', '2017-12-23 09:39:12'),
(2, 'test@gmail.com', 'd1XBvZ1W0Dk:APA91bGQEG6D0tgbU86ULQlRW-hMG7IAdnWf_uCi0vIEzJgRQrkPkxui6S4wwGOQ4JnmWDjIWVAcLh8kcKfrrUOScqKQdQQRwpAdRizr3AQaH6nkwjZN7YdZkCo6JZ--8txEvgpC5TDP', '2017-12-23 09:39:53'),
(3, 'ppp@gmail.com', 'd1XBvZ1W0Dk:APA91bGQEG6D0tgbU86ULQlRW-hMG7IAdnWf_uCi0vIEzJgRQrkPkxui6S4wwGOQ4JnmWDjIWVAcLh8kcKfrrUOScqKQdQQRwpAdRizr3AQaH6nkwjZN7YdZkCo6JZ--8txEvgpC5TDP', '2017-12-23 10:42:11'),
(4, 'aaa', 'd1XBvZ1W0Dk:APA91bGQEG6D0tgbU86ULQlRW-hMG7IAdnWf_uCi0vIEzJgRQrkPkxui6S4wwGOQ4JnmWDjIWVAcLh8kcKfrrUOScqKQdQQRwpAdRizr3AQaH6nkwjZN7YdZkCo6JZ--8txEvgpC5TDP', '2017-12-23 13:09:08'),
(5, 'p111@gmail.com', 'cL8A4MTmmOk:APA91bEBk4y3Do82pgV8uOuRGOnhsqUuPSTSMdUGZ-VAHYunw9dQbVHRn6YJkTrnETiCgEAjCPdmwwO_FZV_HFO-oyz6DebMgb9cTV--VGCwdoeZPfPgw5JyVS-JhPhKp55JvS_ZyICj', '2017-12-23 17:40:49'),
(6, 'a111@gmail.com', 'fVkhUaKrLsU:APA91bG9sogsyN5PEkRRCJxpbIrRbqAs2GQV0b1wRuP3v8QWsm-H9i0gIWFBcfGyaY1uOIVw55VDDLvySaPwsoN-KfXRNOrXbj17_KEM2wn-MK8uT2igBnyPo1zLLwwTmEHeNS8FiC6N', '2017-12-23 18:01:22');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
