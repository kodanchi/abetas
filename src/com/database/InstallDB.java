package com.database;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Ibrahim Abuaqel on 1/24/2016.
 * source : http://tomcat.apache.org/tomcat-6.0-doc/jndi-datasource-examples-howto.html#MySQL_DBCP_Example
 */
public class InstallDB {
    private Connection conn;
    private Statement stmt;
    private ResultSet result;
    DataSource dataSource = null;
    private static String jdbcDriver = "com.mysql.jdbc.Driver";
    private static String dbName = "abetasdb";
    public void connect() throws ClassNotFoundException, SQLException {

        try {
            /*
             *  Using JDNI lookup get the DataSource.
             */

            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/abetasDB");

        } catch (Exception exe) {
            exe.printStackTrace();
        }

    }



    public void init(String Uname, String Cname, String logo, String Fname, String Mname, String Lname, String Username, String password, String email) throws ClassNotFoundException, SQLException {

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int rs = 0;
        try {

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            String query = " insert into university (Uni_name, College_name, Uni_logo)" + " values (?, ?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, Uname);
            preparedStatement.setString(2, Cname);
            preparedStatement.setString(3, logo);

            rs = preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            /*
             * finally block used to close resources
             */
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }

        }

        connect();

        connection = null;
        preparedStatement = null;

        rs = 0;
        try {

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            String query = " insert into superuser (Super_Username, Super_Password, Super_Email, Super_Fname, Super_Mname, Super_Lname, Adm_ID)" + " values (?, ?, ?, ?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, Username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, Fname);
            preparedStatement.setString(5, Mname);
            preparedStatement.setString(6, Lname);
            preparedStatement.setInt(7, 1);
            rs = preparedStatement.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            /*
             * finally block used to close resources
             */
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }

        }

    }

    public void installdb() throws ClassNotFoundException, SQLException {

        connect();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rs = 0;


        Connection conn = null;
        Statement s = null;
        int Result = 0;


        try {

            /*
             *  Get connection from the DataSource
             */
            /*connection = dataSource.getConnection();*/

            Class.forName(jdbcDriver);
            conn = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=abetas");
            s = conn.createStatement();
            Result = s.executeUpdate("CREATE DATABASE "+dbName);


            /*
             * Execute the query
             *//*
            String query = " CREATE DATABASE IF NOT EXISTS 'abetasdb'";

            preparedStatement = connection.prepareStatement(query);

            rs = preparedStatement.executeUpdate();*/

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            /*
             * finally block used to close resources
             */
            try {
                if (s != null) {
                    s.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }

        }


            this.createTable( "CREATE TABLE `university` (\n" +
                    "  `Uni_name` varchar(255) NOT NULL,\n" +
                    "  `College_name` varchar(100) DEFAULT NULL,\n" +
                    "  `Uni_logo` varchar(255) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`Uni_name`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n");


            this.createTable( "CREATE TABLE `superuser` (\n" +
                    "  `Super_ID` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `Super_Username` varchar(255) DEFAULT NULL,\n" +
                    "  `Super_Password` varchar(255) DEFAULT NULL,\n" +
                    "  `Super_Email` varchar(255) DEFAULT NULL,\n" +
                    "  `Super_Fname` varchar(50) DEFAULT NULL,\n" +
                    "  `Super_Mname` varchar(50) DEFAULT NULL,\n" +
                    "  `Super_Lname` varchar(50) DEFAULT NULL,\n" +
                    "  `Adm_ID` tinyint(1) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`Super_ID`),\n" +
                    "  UNIQUE KEY `Super_Username_UNIQUE` (`Super_Username`),\n" +
                    "  UNIQUE KEY `Super_Email_UNIQUE` (`Super_Email`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;\n");


            this.createTable( "CREATE TABLE `cycle` (\n" +
                    "  `Cycle_ID` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  PRIMARY KEY (`Cycle_ID`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;\n");


            this.createTable( "CREATE TABLE `evaluator` (\n" +
                    "  `E_ID` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `E_Username` varchar(13) DEFAULT NULL,\n" +
                    "  `E_Password` varchar(255) DEFAULT NULL,\n" +
                    "  `E_Fname` varchar(50) DEFAULT NULL,\n" +
                    "  `E_Mname` varchar(50) DEFAULT NULL,\n" +
                    "  `E_Lname` varchar(50) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`E_ID`),\n" +
                    "  UNIQUE KEY `E_Username_UNIQUE` (`E_Username`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n");


            this.createTable( "CREATE TABLE `program` (\n" +
                    "  `P_ID` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `P_name` varchar(255) NOT NULL,\n" +
                    "  `P_mission` varchar(3800) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`P_ID`),\n" +
                    "  UNIQUE KEY `P_name_UNIQUE` (`P_name`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;\n");


            this.createTable( "CREATE TABLE `formative` (\n" +
                    "  `Formative_ID` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `F_submitted` tinyint(1) DEFAULT NULL,\n" +
                    "  `F_written_rubic` varchar(255) DEFAULT NULL,\n" +
                    "  `F_instructor_feedback_comment` varchar(255) DEFAULT NULL,\n" +
                    "  `F_instructor_feedback_obstacle` varchar(255) DEFAULT NULL,\n" +
                    "  `F_instructor_feedback_improvement` varchar(255) DEFAULT NULL,\n" +
                    "  `F_evidence` varchar(255) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`Formative_ID`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n");


            this.createTable("CREATE TABLE `performance_indicator` (\n" +
                    "  `PI_ID` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `PI_name` varchar(255) DEFAULT NULL,\n" +
                    "  `PI_passed` tinyint(1) DEFAULT NULL,\n" +
                    "  `PI_label` varchar(5) DEFAULT NULL,\n" +
                    "  `FK_P_ID` int(11) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`PI_ID`),\n" +
                    "  UNIQUE KEY `PI_label_UNIQUE` (`PI_label`),\n" +
                    "  UNIQUE KEY `PI_name_UNIQUE` (`PI_name`),\n" +
                    "  KEY `Performance_indicator_FK_idx` (`FK_P_ID`),\n" +
                    "  CONSTRAINT `Performance_indicator_FK` FOREIGN KEY (`FK_P_ID`) REFERENCES `program` (`P_ID`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n");


            this.createTable("CREATE TABLE `term` (\n" +
                    "  `T_ID` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `T_name` varchar(255) DEFAULT NULL,\n" +
                    "  `T_year` varchar(255) DEFAULT NULL,\n" +
                    "  `FK_Cycle_ID` int(11) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`T_ID`),\n" +
                    "  KEY `Term_FK_idx` (`FK_Cycle_ID`),\n" +
                    "  CONSTRAINT `Term_FK` FOREIGN KEY (`FK_Cycle_ID`) REFERENCES `cycle` (`Cycle_ID`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;\n");




            this.createTable("CREATE TABLE `course` (\n" +
                    "  `C_code` varchar(15) NOT NULL,\n" +
                    "  `C_level` varchar(15) DEFAULT NULL,\n" +
                    "  `C_name` varchar(50) DEFAULT NULL,\n" +
                    "  `FK_T_ID` int(11) DEFAULT NULL,\n" +
                    "  `C_include` tinyint(1) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`C_code`),\n" +
                    "  KEY `Course_FK_idx` (`FK_T_ID`),\n" +
                    "  CONSTRAINT `Course_FK` FOREIGN KEY (`FK_T_ID`) REFERENCES `term` (`T_ID`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n");


            this.createTable("CREATE TABLE `faculty_member` (\n" +
                    "  `Faculty_ID` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `Faculty_Username` varchar(255) DEFAULT NULL,\n" +
                    "  `Faculty_Password` varchar(255) DEFAULT NULL,\n" +
                    "  `Faculty_Email` varchar(255) DEFAULT NULL,\n" +
                    "  `Faculty_Fname` varchar(50) DEFAULT NULL,\n" +
                    "  `Faculty_Mname` varchar(50) DEFAULT NULL,\n" +
                    "  `Faculty_Lname` varchar(50) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`Faculty_ID`),\n" +
                    "  UNIQUE KEY `Faculty_Email_UNIQUE` (`Faculty_Email`),\n" +
                    "  UNIQUE KEY `Faculty_Username_UNIQUE` (`Faculty_Username`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;\n");



            this.createTable("CREATE TABLE `summative` (\n" +
                    "  `Summative_ID` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `Sum_threshold` int(11) DEFAULT NULL,\n" +
                    "  `Sum_submitted` tinyint(1) DEFAULT NULL,\n" +
                    "  `Sum_evidence` varchar(255) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`Summative_ID`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n");



            this.createTable("CREATE TABLE `summative_rubric` (\n" +
                    "  `FK_Summative_ID` int(11) NOT NULL,\n" +
                    "  `student_rubric` varchar(255) NOT NULL,\n" +
                    "  PRIMARY KEY (`FK_Summative_ID`,`student_rubric`),\n" +
                    "  CONSTRAINT `Summative_rubric_FK` FOREIGN KEY (`FK_Summative_ID`) REFERENCES `summative` (`Summative_ID`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n");



            this.createTable("CREATE TABLE `update_summative` (\n" +
                    "  `FK_Summative_ID` int(11) NOT NULL,\n" +
                    "  `FK_Faculty_ID` int(11) NOT NULL,\n" +
                    "  `Date` date DEFAULT NULL,\n" +
                    "  `Submit` tinyint(1) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`FK_Summative_ID`,`FK_Faculty_ID`),\n" +
                    "  KEY `Update_summative_FK_idx` (`FK_Faculty_ID`),\n" +
                    "  CONSTRAINT `Update_summative_FK_faculty` FOREIGN KEY (`FK_Faculty_ID`) REFERENCES `faculty_member` (`Faculty_ID`) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "  CONSTRAINT `Update_summative_FK_summative` FOREIGN KEY (`FK_Summative_ID`) REFERENCES `summative` (`Summative_ID`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n");



            this.createTable("CREATE TABLE `update_formative` (\n" +
                    "  `FK_Formative_ID` int(11) NOT NULL,\n" +
                    "  `FK_Faculty_ID` int(11) NOT NULL,\n" +
                    "  `Date` date DEFAULT NULL,\n" +
                    "  `Submit` tinyint(1) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`FK_Formative_ID`,`FK_Faculty_ID`),\n" +
                    "  KEY `Update_formative_FK_faculty_idx` (`FK_Faculty_ID`),\n" +
                    "  CONSTRAINT `Update_formative_FK_faculty` FOREIGN KEY (`FK_Faculty_ID`) REFERENCES `faculty_member` (`Faculty_ID`) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "  CONSTRAINT `Update_formative_FK_formative` FOREIGN KEY (`FK_Formative_ID`) REFERENCES `formative` (`Formative_ID`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n");




            this.createTable("CREATE TABLE `pi_rubric` (\n" +
                    "  `PI_rubric_ID` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `FK_PI_ID` int(11) DEFAULT NULL,\n" +
                    "  `PI_rubric_name` varchar(255) DEFAULT NULL,\n" +
                    "  `PI_rubric_description` varchar(255) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`PI_rubric_ID`),\n" +
                    "  UNIQUE KEY `PI_rubric_name_UNIQUE` (`PI_rubric_name`),\n" +
                    "  KEY `pi_rubric_FK` (`FK_PI_ID`),\n" +
                    "  CONSTRAINT `pi_rubric_FK` FOREIGN KEY (`FK_PI_ID`) REFERENCES `performance_indicator` (`PI_ID`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n");



            this.createTable("CREATE TABLE `pi_measure_course` (\n" +
                    "  `FK_PI_ID` int(11) NOT NULL,\n" +
                    "  `FK_C_code` varchar(15) NOT NULL,\n" +
                    "  `Relation` varchar(2) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`FK_PI_ID`,`FK_C_code`),\n" +
                    "  KEY `PI_measure_course_FK_course_idx` (`FK_C_code`),\n" +
                    "  CONSTRAINT `PI_measure_course_FK_course` FOREIGN KEY (`FK_C_code`) REFERENCES `course` (`C_code`) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "  CONSTRAINT `PI_measure_course_FK_performanc` FOREIGN KEY (`FK_PI_ID`) REFERENCES `performance_indicator` (`PI_ID`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n");



            this.createTable("CREATE TABLE `faculty_member_teach_course` (\n" +
                    "  `FK_C_code` varchar(15) NOT NULL,\n" +
                    "  `FK_Faculty_ID` int(11) NOT NULL,\n" +
                    "  `Group_ID` int(11) DEFAULT NULL,\n" +
                    "  `Group_name` varchar(30) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`FK_C_code`,`FK_Faculty_ID`),\n" +
                    "  KEY `faculty_member_teach_course_FK_faculity_idx` (`FK_Faculty_ID`),\n" +
                    "  CONSTRAINT `faculty_member_teach_course_FK_course` FOREIGN KEY (`FK_C_code`) REFERENCES `course` (`C_code`) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "  CONSTRAINT `faculty_member_teach_course_FK_faculity` FOREIGN KEY (`FK_Faculty_ID`) REFERENCES `faculty_member` (`Faculty_ID`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n");


            this.createTable("CREATE TABLE `pi_type_summative` (\n" +
                    "  `FK_Summative_ID` int(11) NOT NULL,\n" +
                    "  `FK_PI_ID` int(11) NOT NULL,\n" +
                    "  PRIMARY KEY (`FK_Summative_ID`,`FK_PI_ID`),\n" +
                    "  KEY `PI_type_summative_FK_PI_idx` (`FK_PI_ID`),\n" +
                    "  CONSTRAINT `PI_type_summative_FK_PI` FOREIGN KEY (`FK_PI_ID`) REFERENCES `performance_indicator` (`PI_ID`) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "  CONSTRAINT `PI_type_summative_FK_summative` FOREIGN KEY (`FK_Summative_ID`) REFERENCES `summative` (`Summative_ID`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n");




            this.createTable("CREATE TABLE `pi_type_formative` (\n" +
                    "  `FK_Formative_ID` int(11) NOT NULL,\n" +
                    "  `FK_PI_ID` int(11) NOT NULL,\n" +
                    "  PRIMARY KEY (`FK_Formative_ID`,`FK_PI_ID`),\n" +
                    "  KEY `PI_type_formative_FK_idx` (`FK_PI_ID`),\n" +
                    "  CONSTRAINT `PI_type_formative_FK_PI` FOREIGN KEY (`FK_PI_ID`) REFERENCES `performance_indicator` (`PI_ID`) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "  CONSTRAINT `PI_type_formative_FK_formative` FOREIGN KEY (`FK_Formative_ID`) REFERENCES `formative` (`Formative_ID`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n");



            this.createTable("CREATE TABLE `evaluator_evaluate_pi` (\n" +
                    "  `FK_E_ID` int(11) NOT NULL,\n" +
                    "  `FK_PI_ID` int(11) NOT NULL,\n" +
                    "  PRIMARY KEY (`FK_E_ID`,`FK_PI_ID`),\n" +
                    "  KEY `Evaluator_evaluate_PI__idx` (`FK_PI_ID`),\n" +
                    "  CONSTRAINT `Evaluator_evaluate_PI_PI` FOREIGN KEY (`FK_PI_ID`) REFERENCES `performance_indicator` (`PI_ID`) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "  CONSTRAINT `Evaluator_evaluate_PI_evaluator` FOREIGN KEY (`FK_E_ID`) REFERENCES `evaluator` (`E_ID`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n");



            this.createTable("CREATE TABLE `students` (\n" +
                    "  `FK_C_code` varchar(35) NOT NULL,\n" +
                    "  `FK_Faculty_ID` int(11) NOT NULL,\n" +
                    "  `Student_ID` int(11) DEFAULT NULL,\n" +
                    "  `Student_FName` varchar(50) DEFAULT NULL,\n" +
                    "  `Student_MName` varchar(50) DEFAULT NULL,\n" +
                    "  `Student_LName` varchar(50) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`FK_Faculty_ID`,`FK_C_code`),\n" +
                    "  UNIQUE KEY `Student_ID_UNIQUE` (`Student_ID`),\n" +
                    "  KEY `Students_idx` (`FK_C_code`),\n" +
                    "  CONSTRAINT `Students_course` FOREIGN KEY (`FK_C_code`) REFERENCES `course` (`C_code`) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "  CONSTRAINT `Students_faculty_member` FOREIGN KEY (`FK_Faculty_ID`) REFERENCES `faculty_member` (`Faculty_ID`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n");




    }

    public void createTable(String sql) throws ClassNotFoundException, SQLException {

        connect();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rs = 0;


        try {

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            String query = sql;

            preparedStatement = connection.prepareStatement(query);

            rs = preparedStatement.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            /*
             * finally block used to close resources
             */
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }

        }
    }

    public boolean setUpChk() throws SQLException, ClassNotFoundException {

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        boolean rsr = false;
        try {

            String query = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = 'abetasdb';";

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            //preparedStatement.setInt(1, 10);

            rs = preparedStatement.executeQuery();
            if(rs.next()){
                rsr = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
                /*
                 * finally block used to close resources
                 */
            //rs.close();
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }

            return rsr;

        }

    }

}
