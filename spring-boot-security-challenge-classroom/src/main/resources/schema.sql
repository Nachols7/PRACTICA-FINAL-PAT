
DROP TABLE IF EXISTS `user_details`;

CREATE TABLE `user_details` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` tinyint(10) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ;

--
-- Dumping data for table `user_details`
--

INSERT INTO `user_details` (`user_id`, `username`, `first_name`, `last_name`, `gender`, `password`, `status`) VALUES
(1, 'rogers63', 'david', 'john', 'Female', '$2a$10$r6lgRfX6VEj.Mz0LJRaEjuGcK3g/Prjv3u.IhTJLzYNAuHQMf8uxa', 1),
(2, 'mike28', 'rogers', 'paul', 'Male', '$2a$10$r6lgRfX6VEj.Mz0LJRaEjuGcK3g/Prjv3u.IhTJLzYNAuHQMf8uxa', 1),
(3, 'rivera92', 'david', 'john', 'Male', '1c3a8e03f448d211904161a6f5849b68', 1),
(4, 'ross95', 'maria', 'sanders', 'Male', '62f0a68a4179c5cdd997189760cbcf18', 1),
(5, 'paul85', 'morris', 'miller', 'Female', '61bd060b07bddfecccea56a82b850ecf', 1),
(6, 'smith34', 'daniel', 'michael', 'Female', '7055b3d9f5cb2829c26cd7e0e601cde5', 1),
(7, 'james84', 'sanders', 'paul', 'Female', 'b7f72d6eb92b45458020748c8d1a3573', 1),
(8, 'daniel53', 'mark', 'mike', 'Male', '299cbf7171ad1b2967408ed200b4e26c', 1),
(9, 'brooks80', 'morgan', 'maria', 'Female', 'aa736a35dc15934d67c0a999dccff8f6', 1),
(10, 'morgan65', 'paul', 'miller', 'Female', 'a28dca31f5aa5792e1cefd1dfd098569', 1),
(11, 'sanders84', 'david', 'miller', 'Female', '0629e4f9f0e01e6f20bc2066175e09f7', 1),
(12, 'maria40', 'chrishaydon', 'bell', 'Female', '17f286a78c74db7ee24374c608a2f20c', 1),
(13, 'brown71', 'michael', 'brown', 'Male', 'fa0c46cc4339a8a51a7da1b33e9d2831', 1),
(14, 'james63', 'morgan', 'james', 'Male', 'b945416fa907fac533d94efe1974ec07', 1),
(15, 'jenny0993', 'rogers', 'chrishaydon', 'Female', '388823cb9249d4cebc9d677a99e1d79d', 1),
(16, 'john96', 'morgan', 'wright', 'Male', 'd0bb977705c3cdad1e346c898f32a1b7', 1),
(17, 'miller64', 'morgan', 'wright', 'Male', '58b207ee33794b046511203967c8e0d7', 1),
(18, 'mark46', 'david', 'ross', 'Female', '21cdcb68a932871524e16680fac72e18', 1),
(19, 'jenny0988', 'maria', 'morgan', 'Female', 'ec9ed18ae2a13fef709964af24bb60e6', 1),
(20, 'mark80', 'mike', 'bell', 'Male', '084489b355edd349bca1c798788de19a', 1);

-- Update password for all users

DROP TABLE IF EXISTS `reservas`;

CREATE TABLE `reservas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `start_hour` varchar(50) DEFAULT NULL,
  `number_of_people` tinyint(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;

INSERT INTO `reservas` (`id`, `username` ,`start_date` ,`start_hour`,`number_of_people`) VALUES
(1, 'rogers63', '2021-05-20', '23:00', 2),
(2, 'rogers63', '2021-05-28', '22:00', 3),
(3, 'mike28', '2021-07-15', '19:00', 1),
(4, 'rogers63', '2021-06-20', '21:00', 5);