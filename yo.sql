-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: abetasdb
-- ------------------------------------------------------
-- Server version	5.7.10-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `C_code` varchar(15) NOT NULL,
  `C_level` varchar(15) DEFAULT NULL,
  `C_name` varchar(50) DEFAULT NULL,
  `FK_T_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`C_code`),
  KEY `Course_FK_idx` (`FK_T_ID`),
  CONSTRAINT `Course_FK` FOREIGN KEY (`FK_T_ID`) REFERENCES `term` (`T_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cycle`
--

DROP TABLE IF EXISTS `cycle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cycle` (
  `Cycle_ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`Cycle_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cycle`
--

LOCK TABLES `cycle` WRITE;
/*!40000 ALTER TABLE `cycle` DISABLE KEYS */;
/*!40000 ALTER TABLE `cycle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evaluator`
--

DROP TABLE IF EXISTS `evaluator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `evaluator` (
  `E_ID` int(11) NOT NULL AUTO_INCREMENT,
  `E_Username` varchar(13) DEFAULT NULL,
  `E_Password` varchar(255) DEFAULT NULL,
  `E_Fname` varchar(50) DEFAULT NULL,
  `E_Mname` varchar(50) DEFAULT NULL,
  `E_Lname` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`E_ID`),
  UNIQUE KEY `E_Username_UNIQUE` (`E_Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluator`
--

LOCK TABLES `evaluator` WRITE;
/*!40000 ALTER TABLE `evaluator` DISABLE KEYS */;
/*!40000 ALTER TABLE `evaluator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evaluator_evaluate_pi`
--

DROP TABLE IF EXISTS `evaluator_evaluate_pi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `evaluator_evaluate_pi` (
  `FK_E_ID` int(11) NOT NULL,
  `FK_PI_ID` int(11) NOT NULL,
  PRIMARY KEY (`FK_E_ID`,`FK_PI_ID`),
  KEY `Evaluator_evaluate_PI__idx` (`FK_PI_ID`),
  CONSTRAINT `Evaluator_evaluate_PI_PI` FOREIGN KEY (`FK_PI_ID`) REFERENCES `performance_indicator` (`PI_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Evaluator_evaluate_PI_evaluator` FOREIGN KEY (`FK_E_ID`) REFERENCES `evaluator` (`E_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluator_evaluate_pi`
--

LOCK TABLES `evaluator_evaluate_pi` WRITE;
/*!40000 ALTER TABLE `evaluator_evaluate_pi` DISABLE KEYS */;
/*!40000 ALTER TABLE `evaluator_evaluate_pi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty_member`
--

DROP TABLE IF EXISTS `faculty_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `faculty_member` (
  `Faculty_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Faculty_Username` varchar(255) DEFAULT NULL,
  `Faculty_Password` varchar(255) DEFAULT NULL,
  `Faculty_Email` varchar(255) DEFAULT NULL,
  `Faculty_Fname` varchar(50) DEFAULT NULL,
  `Faculty_Mname` varchar(50) DEFAULT NULL,
  `Faculty_Lname` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Faculty_ID`),
  UNIQUE KEY `Faculty_Email_UNIQUE` (`Faculty_Email`),
  UNIQUE KEY `Faculty_Username_UNIQUE` (`Faculty_Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty_member`
--

LOCK TABLES `faculty_member` WRITE;
/*!40000 ALTER TABLE `faculty_member` DISABLE KEYS */;
/*!40000 ALTER TABLE `faculty_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty_member_teach_course`
--

DROP TABLE IF EXISTS `faculty_member_teach_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `faculty_member_teach_course` (
  `FK_C_code` varchar(15) NOT NULL,
  `FK_Faculty_ID` int(11) NOT NULL,
  `Group_ID` int(11) DEFAULT NULL,
  `Group_name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`FK_C_code`,`FK_Faculty_ID`),
  KEY `faculty_member_teach_course_FK_faculity_idx` (`FK_Faculty_ID`),
  CONSTRAINT `faculty_member_teach_course_FK_course` FOREIGN KEY (`FK_C_code`) REFERENCES `course` (`C_code`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `faculty_member_teach_course_FK_faculity` FOREIGN KEY (`FK_Faculty_ID`) REFERENCES `faculty_member` (`Faculty_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty_member_teach_course`
--

LOCK TABLES `faculty_member_teach_course` WRITE;
/*!40000 ALTER TABLE `faculty_member_teach_course` DISABLE KEYS */;
/*!40000 ALTER TABLE `faculty_member_teach_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `formative`
--

DROP TABLE IF EXISTS `formative`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `formative` (
  `Formative_ID` int(11) NOT NULL AUTO_INCREMENT,
  `F_submitted` tinyint(1) DEFAULT NULL,
  `F_written_rubic` varchar(255) DEFAULT NULL,
  `F_instructor_feedback_comment` varchar(255) DEFAULT NULL,
  `F_instructor_feedback_obstacle` varchar(255) DEFAULT NULL,
  `F_instructor_feedback_improvement` varchar(255) DEFAULT NULL,
  `F_evidence` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Formative_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `formative`
--

LOCK TABLES `formative` WRITE;
/*!40000 ALTER TABLE `formative` DISABLE KEYS */;
/*!40000 ALTER TABLE `formative` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `performance_indicator`
--

DROP TABLE IF EXISTS `performance_indicator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `performance_indicator` (
  `PI_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PI_name` varchar(255) DEFAULT NULL,
  `PI_passed` tinyint(1) DEFAULT NULL,
  `PI_label` varchar(5) DEFAULT NULL,
  `FK_P_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`PI_ID`),
  UNIQUE KEY `PI_label_UNIQUE` (`PI_label`),
  UNIQUE KEY `PI_name_UNIQUE` (`PI_name`),
  KEY `Performance_indicator_FK_idx` (`FK_P_ID`),
  CONSTRAINT `Performance_indicator_FK` FOREIGN KEY (`FK_P_ID`) REFERENCES `program` (`P_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `performance_indicator`
--

LOCK TABLES `performance_indicator` WRITE;
/*!40000 ALTER TABLE `performance_indicator` DISABLE KEYS */;
/*!40000 ALTER TABLE `performance_indicator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pi_measure_course`
--

DROP TABLE IF EXISTS `pi_measure_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pi_measure_course` (
  `FK_PI_ID` int(11) NOT NULL,
  `FK_C_code` varchar(15) NOT NULL,
  PRIMARY KEY (`FK_PI_ID`,`FK_C_code`),
  KEY `PI_measure_course_FK_course_idx` (`FK_C_code`),
  CONSTRAINT `PI_measure_course_FK_course` FOREIGN KEY (`FK_C_code`) REFERENCES `course` (`C_code`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `PI_measure_course_FK_performanc` FOREIGN KEY (`FK_PI_ID`) REFERENCES `performance_indicator` (`PI_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pi_measure_course`
--

LOCK TABLES `pi_measure_course` WRITE;
/*!40000 ALTER TABLE `pi_measure_course` DISABLE KEYS */;
/*!40000 ALTER TABLE `pi_measure_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pi_rubric`
--

DROP TABLE IF EXISTS `pi_rubric`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pi_rubric` (
  `PI_rubric` int(11) NOT NULL,
  `FK_PI_ID` int(11) NOT NULL,
  `PI_rubric_name` varchar(255) DEFAULT NULL,
  `PI_rubric_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`FK_PI_ID`,`PI_rubric`),
  UNIQUE KEY `PI_rubric_name_UNIQUE` (`PI_rubric_name`),
  CONSTRAINT `pi_rubric_FK` FOREIGN KEY (`FK_PI_ID`) REFERENCES `performance_indicator` (`PI_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pi_rubric`
--

LOCK TABLES `pi_rubric` WRITE;
/*!40000 ALTER TABLE `pi_rubric` DISABLE KEYS */;
/*!40000 ALTER TABLE `pi_rubric` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pi_type_formative`
--

DROP TABLE IF EXISTS `pi_type_formative`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pi_type_formative` (
  `FK_Formative_ID` int(11) NOT NULL,
  `FK_PI_ID` int(11) NOT NULL,
  PRIMARY KEY (`FK_Formative_ID`,`FK_PI_ID`),
  KEY `PI_type_formative_FK_idx` (`FK_PI_ID`),
  CONSTRAINT `PI_type_formative_FK_PI` FOREIGN KEY (`FK_PI_ID`) REFERENCES `performance_indicator` (`PI_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `PI_type_formative_FK_formative` FOREIGN KEY (`FK_Formative_ID`) REFERENCES `formative` (`Formative_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pi_type_formative`
--

LOCK TABLES `pi_type_formative` WRITE;
/*!40000 ALTER TABLE `pi_type_formative` DISABLE KEYS */;
/*!40000 ALTER TABLE `pi_type_formative` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pi_type_summative`
--

DROP TABLE IF EXISTS `pi_type_summative`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pi_type_summative` (
  `FK_Summative_ID` int(11) NOT NULL,
  `FK_PI_ID` int(11) NOT NULL,
  PRIMARY KEY (`FK_Summative_ID`,`FK_PI_ID`),
  KEY `PI_type_summative_FK_PI_idx` (`FK_PI_ID`),
  CONSTRAINT `PI_type_summative_FK_PI` FOREIGN KEY (`FK_PI_ID`) REFERENCES `performance_indicator` (`PI_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `PI_type_summative_FK_summative` FOREIGN KEY (`FK_Summative_ID`) REFERENCES `summative` (`Summative_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pi_type_summative`
--

LOCK TABLES `pi_type_summative` WRITE;
/*!40000 ALTER TABLE `pi_type_summative` DISABLE KEYS */;
/*!40000 ALTER TABLE `pi_type_summative` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `program`
--

DROP TABLE IF EXISTS `program`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `program` (
  `P_ID` int(11) NOT NULL AUTO_INCREMENT,
  `P_name` varchar(255) NOT NULL,
  `P_mission` varchar(3800) DEFAULT NULL,
  `P_student_outcome` varchar(390) DEFAULT NULL,
  `P_objective` varchar(390) DEFAULT NULL,
  `P_student_outcome_label` varchar(5) DEFAULT NULL,
  `P_objective_label` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`P_ID`),
  UNIQUE KEY `P_name_UNIQUE` (`P_name`),
  UNIQUE KEY `P_objective_label_UNIQUE` (`P_objective_label`),
  UNIQUE KEY `P_student_outcome_label_UNIQUE` (`P_student_outcome_label`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `program`
--

LOCK TABLES `program` WRITE;
/*!40000 ALTER TABLE `program` DISABLE KEYS */;
/*!40000 ALTER TABLE `program` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `students` (
  `FK_C_code` varchar(35) NOT NULL,
  `FK_Faculty_ID` int(11) NOT NULL,
  `Student_ID` int(11) DEFAULT NULL,
  `Student_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`FK_Faculty_ID`,`FK_C_code`),
  KEY `Students_idx` (`FK_C_code`),
  CONSTRAINT `Students_course` FOREIGN KEY (`FK_C_code`) REFERENCES `course` (`C_code`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Students_faculty_member` FOREIGN KEY (`FK_Faculty_ID`) REFERENCES `faculty_member` (`Faculty_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `summative`
--

DROP TABLE IF EXISTS `summative`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `summative` (
  `Summative_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Sum_threshold` int(11) DEFAULT NULL,
  `Sum_submitted` tinyint(1) DEFAULT NULL,
  `Sum_evidence` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Summative_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `summative`
--

LOCK TABLES `summative` WRITE;
/*!40000 ALTER TABLE `summative` DISABLE KEYS */;
/*!40000 ALTER TABLE `summative` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `summative_rubric`
--

DROP TABLE IF EXISTS `summative_rubric`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `summative_rubric` (
  `FK_Summative_ID` int(11) NOT NULL,
  `student_rubric` varchar(255) NOT NULL,
  PRIMARY KEY (`FK_Summative_ID`,`student_rubric`),
  CONSTRAINT `Summative_rubric_FK` FOREIGN KEY (`FK_Summative_ID`) REFERENCES `summative` (`Summative_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `summative_rubric`
--

LOCK TABLES `summative_rubric` WRITE;
/*!40000 ALTER TABLE `summative_rubric` DISABLE KEYS */;
/*!40000 ALTER TABLE `summative_rubric` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `superuser`
--

DROP TABLE IF EXISTS `superuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `superuser` (
  `Super_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Super_Username` varchar(255) DEFAULT NULL,
  `Super_Password` varchar(255) DEFAULT NULL,
  `Super_Email` varchar(255) DEFAULT NULL,
  `Super_Fname` varchar(50) DEFAULT NULL,
  `Super_Mname` varchar(50) DEFAULT NULL,
  `Super_Lname` varchar(50) DEFAULT NULL,
  `Adm_ID` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`Super_ID`),
  UNIQUE KEY `Super_Username_UNIQUE` (`Super_Username`),
  UNIQUE KEY `Super_Email_UNIQUE` (`Super_Email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `superuser`
--

LOCK TABLES `superuser` WRITE;
/*!40000 ALTER TABLE `superuser` DISABLE KEYS */;
/*!40000 ALTER TABLE `superuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `term`
--

DROP TABLE IF EXISTS `term`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `term` (
  `T_ID` int(11) NOT NULL AUTO_INCREMENT,
  `T_name` varchar(255) DEFAULT NULL,
  `T_year` varchar(255) DEFAULT NULL,
  `FK_Cycle_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`T_ID`),
  KEY `Term_FK_idx` (`FK_Cycle_ID`),
  CONSTRAINT `Term_FK` FOREIGN KEY (`FK_Cycle_ID`) REFERENCES `cycle` (`Cycle_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `term`
--

LOCK TABLES `term` WRITE;
/*!40000 ALTER TABLE `term` DISABLE KEYS */;
/*!40000 ALTER TABLE `term` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `university`
--

DROP TABLE IF EXISTS `university`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `university` (
  `Uni_name` varchar(255) NOT NULL,
  `Uni_logo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Uni_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `university`
--

LOCK TABLES `university` WRITE;
/*!40000 ALTER TABLE `university` DISABLE KEYS */;
/*!40000 ALTER TABLE `university` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `update_formative`
--

DROP TABLE IF EXISTS `update_formative`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `update_formative` (
  `FK_Formative_ID` int(11) NOT NULL,
  `FK_Faculty_ID` int(11) NOT NULL,
  `Date` date DEFAULT NULL,
  `Submit` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`FK_Formative_ID`,`FK_Faculty_ID`),
  KEY `Update_formative_FK_faculty_idx` (`FK_Faculty_ID`),
  CONSTRAINT `Update_formative_FK_faculty` FOREIGN KEY (`FK_Faculty_ID`) REFERENCES `faculty_member` (`Faculty_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Update_formative_FK_formative` FOREIGN KEY (`FK_Formative_ID`) REFERENCES `formative` (`Formative_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `update_formative`
--

LOCK TABLES `update_formative` WRITE;
/*!40000 ALTER TABLE `update_formative` DISABLE KEYS */;
/*!40000 ALTER TABLE `update_formative` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `update_summative`
--

DROP TABLE IF EXISTS `update_summative`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `update_summative` (
  `FK_Summative_ID` int(11) NOT NULL,
  `FK_Faculty_ID` int(11) NOT NULL,
  `Date` date DEFAULT NULL,
  `Submit` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`FK_Summative_ID`,`FK_Faculty_ID`),
  KEY `Update_summative_FK_idx` (`FK_Faculty_ID`),
  CONSTRAINT `Update_summative_FK_faculty` FOREIGN KEY (`FK_Faculty_ID`) REFERENCES `faculty_member` (`Faculty_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Update_summative_FK_summative` FOREIGN KEY (`FK_Summative_ID`) REFERENCES `summative` (`Summative_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `update_summative`
--

LOCK TABLES `update_summative` WRITE;
/*!40000 ALTER TABLE `update_summative` DISABLE KEYS */;
/*!40000 ALTER TABLE `update_summative` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-01-19 18:16:17
