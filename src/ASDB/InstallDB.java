package ASDB;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.PrintWriter;
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
    private PrintWriter pout;
    DataSource dataSource = null;
    private static String jdbcDriver = "com.mysql.jdbc.Driver";
    private static String dbName = "abetasdb";

    public InstallDB(PrintWriter out) {
        pout = out;
    }

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

    public void installDB() throws ClassNotFoundException, SQLException {

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
            pout.println("<h4>Database Created!</h4>");

        }

            this.createTable("USE "+dbName+";");




































            this.createTable("CREATE TABLE `auditing` (\n" +
                    "  `audit_ID` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `userName` varchar(255) DEFAULT NULL,\n" +
                    "  `action` varchar(255) DEFAULT NULL,\n" +
                    "  `time` varchar(255) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`audit_ID`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;");




            this.createTable("CREATE TABLE `course` (\n" +
                    "  `C_ID` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `C_code` varchar(15) NOT NULL,\n" +
                    "  `C_name` varchar(50) DEFAULT NULL,\n" +
                    "  `C_level` int(11) DEFAULT NULL,\n" +
                    "  `C_include` tinyint(1) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`C_code`),\n" +
                    "  UNIQUE KEY `C_ID_UNIQUE` (`C_ID`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=341 DEFAULT CHARSET=utf8;");


            this.createTable("CREATE TABLE `cycle` (\n" +
                    "  `Cycle_ID` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `PI_rubric_name_1` varchar(255) DEFAULT NULL,\n" +
                    "  `PI_rubric_name_2` varchar(255) DEFAULT NULL,\n" +
                    "  `PI_rubric_name_3` varchar(255) DEFAULT NULL,\n" +
                    "  `PI_rubric_name_4` varchar(255) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`Cycle_ID`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;");


            this.createTable("CREATE TABLE `evaluator` (\n" +
                    "  `E_ID` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `E_Username` varchar(13) DEFAULT NULL,\n" +
                    "  `E_Password` varchar(255) DEFAULT NULL,\n" +
                    "  `E_Fname` varchar(50) DEFAULT NULL,\n" +
                    "  `E_Mname` varchar(50) DEFAULT NULL,\n" +
                    "  `E_Lname` varchar(50) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`E_ID`),\n" +
                    "  UNIQUE KEY `E_Username_UNIQUE` (`E_Username`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;");


            this.createTable("CREATE TABLE `evaluator_evaluate_pi` (\n" +
                    "  `FK_E_ID` int(11) NOT NULL,\n" +
                    "  `FK_PI_ID` int(11) NOT NULL,\n" +
                    "  PRIMARY KEY (`FK_E_ID`,`FK_PI_ID`),\n" +
                    "  KEY `Evaluator_evaluate_PI__idx` (`FK_PI_ID`),\n" +
                    "  CONSTRAINT `Evaluator_evaluate_PI_PI` FOREIGN KEY (`FK_PI_ID`) REFERENCES `performance_indicator` (`PI_Label`) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "  CONSTRAINT `Evaluator_evaluate_PI_evaluator` FOREIGN KEY (`FK_E_ID`) REFERENCES `evaluator` (`E_ID`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
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
                    ") ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;");


            this.createTable("CREATE TABLE `faculty_member_teach_course` (\n" +
                    "  `FK_C_code` varchar(15) NOT NULL,\n" +
                    "  `FK_Faculty_ID` int(11) NOT NULL,\n" +
                    "  PRIMARY KEY (`FK_C_code`,`FK_Faculty_ID`),\n" +
                    "  KEY `faculty_member_teach_course_FK_faculity_idx` (`FK_Faculty_ID`),\n" +
                    "  CONSTRAINT `faculty_member_teach_course_FK_course` FOREIGN KEY (`FK_C_code`) REFERENCES `course` (`C_code`) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "  CONSTRAINT `faculty_member_teach_course_FK_faculity` FOREIGN KEY (`FK_Faculty_ID`) REFERENCES `faculty_member` (`Faculty_ID`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;");


            this.createTable("CREATE TABLE `formative` (\n" +
                    "  `Formative_ID` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `F_submitted` tinyint(1) DEFAULT NULL,\n" +
                    "  `F_written_rubic` varchar(512) DEFAULT NULL,\n" +
                    "  `F_instructor_feedback_comment` varchar(255) DEFAULT NULL,\n" +
                    "  `F_instructor_feedback_obstacle` varchar(255) DEFAULT NULL,\n" +
                    "  `F_instructor_feedback_improvement` varchar(255) DEFAULT NULL,\n" +
                    "  `F_evidence` varchar(255) DEFAULT NULL,\n" +
                    "  `FK_Link_ID` int(11) DEFAULT NULL,\n" +
                    "  `FK_Section_ID` int(11) DEFAULT NULL,\n" +
                    "  `F_date` varchar(25) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`Formative_ID`),\n" +
                    "  KEY `formative_FK_L_idx` (`FK_Link_ID`),\n" +
                    "  KEY `FK_Section_ID_idx` (`FK_Section_ID`),\n" +
                    "  CONSTRAINT `FFK_Section_ID` FOREIGN KEY (`FK_Section_ID`) REFERENCES `section` (`Section_ID`) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "  CONSTRAINT `formative_FK_L` FOREIGN KEY (`FK_Link_ID`) REFERENCES `link_out_pi` (`Link_ID`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;");




            this.createTable("CREATE TABLE `link_out_obj` (\n" +
                    "  `Link_ID` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `FK_obj` int(11) NOT NULL,\n" +
                    "  `FK_out` int(11) NOT NULL,\n" +
                    "  `FK_P_ID` int(11) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`FK_obj`,`FK_out`),\n" +
                    "  UNIQUE KEY `Link_ID_UNIQUE` (`Link_ID`),\n" +
                    "  KEY `dd_idx` (`FK_out`),\n" +
                    "  KEY `link_out_obj_FK_P_idx` (`FK_P_ID`),\n" +
                    "  CONSTRAINT `link_out_obj_FK_B` FOREIGN KEY (`FK_obj`) REFERENCES `p_objective` (`Objective_label`) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "  CONSTRAINT `link_out_obj_FK_O` FOREIGN KEY (`FK_out`) REFERENCES `p_student_outcome` (`Outcome_label`) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "  CONSTRAINT `link_out_obj_FK_P` FOREIGN KEY (`FK_P_ID`) REFERENCES `program` (`P_ID`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8;");


            this.createTable("CREATE TABLE `link_out_pi` (\n" +
                    "  `Link_ID` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `FK_out` int(11) DEFAULT NULL,\n" +
                    "  `FK_pi_ID` int(11) DEFAULT NULL,\n" +
                    "  `FK_P_ID` int(11) DEFAULT NULL,\n" +
                    "  `FK_R_ID` int(11) DEFAULT NULL,\n" +
                    "  `FK_C_ID` varchar(15) DEFAULT NULL,\n" +
                    "  `FK_T_ID` int(11) DEFAULT NULL,\n" +
                    "  `LinkType` varchar(50) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`Link_ID`),\n" +
                    "  UNIQUE KEY `Link_ID_UNIQUE` (`Link_ID`),\n" +
                    "  KEY `link_out_pi_R_idx` (`FK_R_ID`),\n" +
                    "  KEY `link_out_pi_PI_idx` (`FK_pi_ID`),\n" +
                    "  KEY `link_out_pi_P_idx` (`FK_P_ID`),\n" +
                    "  KEY `link_out_pi_T_idx` (`FK_T_ID`),\n" +
                    "  KEY `link_out_pi_C_idx` (`FK_C_ID`),\n" +
                    "  KEY `link_out_pi_O` (`FK_out`),\n" +
                    "  CONSTRAINT `link_out_pi_C` FOREIGN KEY (`FK_C_ID`) REFERENCES `course` (`C_code`) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "  CONSTRAINT `link_out_pi_O` FOREIGN KEY (`FK_out`) REFERENCES `p_student_outcome` (`Outcome_label`) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "  CONSTRAINT `link_out_pi_P` FOREIGN KEY (`FK_P_ID`) REFERENCES `program` (`P_ID`) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "  CONSTRAINT `link_out_pi_PI` FOREIGN KEY (`FK_pi_ID`) REFERENCES `performance_indicator` (`PI_Label`) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "  CONSTRAINT `link_out_pi_R` FOREIGN KEY (`FK_R_ID`) REFERENCES `pi_rubric` (`PI_rubric_ID`) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "  CONSTRAINT `link_out_pi_T` FOREIGN KEY (`FK_T_ID`) REFERENCES `term` (`T_ID`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;");



            this.createTable("CREATE TABLE `p_objective` (\n" +
                    "  `Objective_label` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `Objective` varchar(255) DEFAULT NULL,\n" +
                    "  `FK_P_ID` int(11) NOT NULL,\n" +
                    "  PRIMARY KEY (`Objective_label`,`FK_P_ID`),\n" +
                    "  KEY `FK_P_objective_idx` (`FK_P_ID`),\n" +
                    "  CONSTRAINT `FK_P_objective` FOREIGN KEY (`FK_P_ID`) REFERENCES `program` (`P_ID`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=280 DEFAULT CHARSET=utf8;\n");



            this.createTable("CREATE TABLE `p_student_outcome` (\n" +
                    "  `Outcome_label` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `Student_outcome` varchar(255) DEFAULT NULL,\n" +
                    "  `FK_P_ID` int(11) NOT NULL,\n" +
                    "  PRIMARY KEY (`Outcome_label`,`FK_P_ID`),\n" +
                    "  KEY `FK_P_student_outcome_idx` (`FK_P_ID`),\n" +
                    "  CONSTRAINT `FK_P_student_outcome` FOREIGN KEY (`FK_P_ID`) REFERENCES `program` (`P_ID`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=194 DEFAULT CHARSET=utf8;");



            this.createTable("CREATE TABLE `performance_indicator` (\n" +
                    "  `PI_Label` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `PI_name` varchar(255) DEFAULT NULL,\n" +
                    "  `FK_P_ID` int(11) DEFAULT NULL,\n" +
                    "  `FK_C_ID` int(11) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`PI_Label`),\n" +
                    "  KEY `Performance_indicator_FK_idx` (`FK_P_ID`),\n" +
                    "  KEY `Performance_indicator_FK_C_idx` (`FK_C_ID`),\n" +
                    "  CONSTRAINT `Performance_indicator_FK` FOREIGN KEY (`FK_P_ID`) REFERENCES `program` (`P_ID`) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "  CONSTRAINT `Performance_indicator_FK_C` FOREIGN KEY (`FK_C_ID`) REFERENCES `cycle` (`Cycle_ID`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=147 DEFAULT CHARSET=utf8;");



            this.createTable("CREATE TABLE `pi_measure_course` (\n" +
                    "  `FK_PI_ID` int(11) NOT NULL,\n" +
                    "  `FK_C_code` varchar(15) NOT NULL,\n" +
                    "  `Relation` varchar(2) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`FK_PI_ID`,`FK_C_code`),\n" +
                    "  KEY `PI_measure_course_FK_course_idx` (`FK_C_code`),\n" +
                    "  CONSTRAINT `PI_measure_course_FK_course` FOREIGN KEY (`FK_C_code`) REFERENCES `course` (`C_code`) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "  CONSTRAINT `PI_measure_course_FK_performanc` FOREIGN KEY (`FK_PI_ID`) REFERENCES `performance_indicator` (`PI_Label`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;");




            this.createTable("CREATE TABLE `pi_rubric` (\n" +
                    "  `PI_rubric_ID` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `PI_rubric_description_1` varchar(255) DEFAULT NULL,\n" +
                    "  `PI_rubric_description_2` varchar(255) DEFAULT NULL,\n" +
                    "  `PI_rubric_description_3` varchar(255) DEFAULT NULL,\n" +
                    "  `PI_rubric_description_4` varchar(255) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`PI_rubric_ID`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=195 DEFAULT CHARSET=utf8;");



            this.createTable("CREATE TABLE `pi_type_formative` (\n" +
                    "  `FK_Formative_ID` int(11) NOT NULL,\n" +
                    "  `FK_PI_ID` int(11) NOT NULL,\n" +
                    "  PRIMARY KEY (`FK_Formative_ID`,`FK_PI_ID`),\n" +
                    "  KEY `PI_type_formative_FK_idx` (`FK_PI_ID`),\n" +
                    "  CONSTRAINT `PI_type_formative_FK_PI` FOREIGN KEY (`FK_PI_ID`) REFERENCES `performance_indicator` (`PI_Label`) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "  CONSTRAINT `PI_type_formative_FK_formative` FOREIGN KEY (`FK_Formative_ID`) REFERENCES `formative` (`Formative_ID`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;");



            this.createTable("CREATE TABLE `pi_type_summative` (\n" +
                    "  `FK_Summative_ID` int(11) NOT NULL,\n" +
                    "  `FK_PI_ID` int(11) NOT NULL,\n" +
                    "  PRIMARY KEY (`FK_Summative_ID`,`FK_PI_ID`),\n" +
                    "  KEY `PI_type_summative_FK_PI_idx` (`FK_PI_ID`),\n" +
                    "  CONSTRAINT `PI_type_summative_FK_PI` FOREIGN KEY (`FK_PI_ID`) REFERENCES `performance_indicator` (`PI_Label`) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "  CONSTRAINT `PI_type_summative_FK_summative` FOREIGN KEY (`FK_Summative_ID`) REFERENCES `summative` (`Summative_ID`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;");


            this.createTable("CREATE TABLE `program` (\n" +
                    "  `P_ID` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `P_name` varchar(255) NOT NULL,\n" +
                    "  `P_mission` varchar(3800) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`P_ID`),\n" +
                    "  UNIQUE KEY `P_name_UNIQUE` (`P_name`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=225 DEFAULT CHARSET=utf8;");




            this.createTable("CREATE TABLE `program_has_course` (\n" +
                    "  `FK_program_ID` int(11) NOT NULL,\n" +
                    "  `FK_course_code` varchar(45) NOT NULL,\n" +
                    "  PRIMARY KEY (`FK_program_ID`,`FK_course_code`),\n" +
                    "  KEY `FK_Program_has_course_Course_idx` (`FK_course_code`),\n" +
                    "  CONSTRAINT `FK_Program_has_course_Course` FOREIGN KEY (`FK_course_code`) REFERENCES `course` (`C_code`) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "  CONSTRAINT `FK_Program_has_course_program` FOREIGN KEY (`FK_program_ID`) REFERENCES `program` (`P_ID`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;");



            this.createTable("CREATE TABLE `section` (\n" +
                    "  `Section_ID` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `FK_T` int(11) DEFAULT NULL,\n" +
                    "  `FK_F` int(11) DEFAULT NULL,\n" +
                    "  `FK_C` varchar(15) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`Section_ID`),\n" +
                    "  KEY `section_FK_T_idx` (`FK_T`),\n" +
                    "  KEY `section_FK_C_idx` (`FK_C`),\n" +
                    "  KEY `section_FK_F_idx` (`FK_F`),\n" +
                    "  CONSTRAINT `section_FK_C` FOREIGN KEY (`FK_C`) REFERENCES `course` (`C_code`) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "  CONSTRAINT `section_FK_F` FOREIGN KEY (`FK_F`) REFERENCES `faculty_member` (`Faculty_ID`) ON DELETE SET NULL ON UPDATE CASCADE,\n" +
                    "  CONSTRAINT `section_FK_T` FOREIGN KEY (`FK_T`) REFERENCES `term` (`T_ID`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;");



            this.createTable("CREATE TABLE `students` (\n" +
                    "  `Student_ID` int(11) DEFAULT NULL,\n" +
                    "  `Student_Name` varchar(120) DEFAULT NULL,\n" +
                    "  `S_ID` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `FK_Section` int(11) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`S_ID`),\n" +
                    "  KEY `students_FK_Section_idx` (`FK_Section`),\n" +
                    "  CONSTRAINT `students_FK_Section` FOREIGN KEY (`FK_Section`) REFERENCES `section` (`Section_ID`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=235 DEFAULT CHARSET=utf8;");




            this.createTable("CREATE TABLE `summative` (\n" +
                    "  `Summative_ID` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `Sum_threshold` int(11) DEFAULT NULL,\n" +
                    "  `Sum_submitted` tinyint(1) DEFAULT NULL,\n" +
                    "  `Sum_evidence` varchar(255) DEFAULT NULL,\n" +
                    "  `FK_Link_ID` int(11) DEFAULT NULL,\n" +
                    "  `FK_Section_ID` int(11) DEFAULT NULL,\n" +
                    "  `Sum_date` varchar(25) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`Summative_ID`),\n" +
                    "  KEY `FK_Link_ID_idx` (`FK_Link_ID`),\n" +
                    "  KEY `FK_Section_ID_idx` (`FK_Section_ID`),\n" +
                    "  CONSTRAINT `FK_Link_ID` FOREIGN KEY (`FK_Link_ID`) REFERENCES `link_out_pi` (`Link_ID`) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "  CONSTRAINT `FK_Section_ID` FOREIGN KEY (`FK_Section_ID`) REFERENCES `section` (`Section_ID`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;");



            this.createTable("CREATE TABLE `summative_rubric` (\n" +
                    "  `FK_Summative_ID` int(11) NOT NULL,\n" +
                    "  `student_rubric` varchar(255) DEFAULT NULL,\n" +
                    "  `FK_S_ID` int(11) NOT NULL,\n" +
                    "  PRIMARY KEY (`FK_Summative_ID`,`FK_S_ID`),\n" +
                    "  KEY `Summative_R_FK_SID_idx` (`FK_S_ID`),\n" +
                    "  CONSTRAINT `Summative_R_FK_SID` FOREIGN KEY (`FK_S_ID`) REFERENCES `students` (`S_ID`) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "  CONSTRAINT `Summative_R_FK_SumID` FOREIGN KEY (`FK_Summative_ID`) REFERENCES `summative` (`Summative_ID`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n");



            this.createTable("CREATE TABLE `superuser` (\n" +
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
                    ") ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;");


            this.createTable("CREATE TABLE `term` (\n" +
                    "  `T_ID` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `T_name` varchar(255) DEFAULT NULL,\n" +
                    "  `T_year` varchar(255) DEFAULT NULL,\n" +
                    "  `FK_Cycle_ID` int(11) DEFAULT NULL,\n" +
                    "  `current` tinyint(1) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`T_ID`),\n" +
                    "  KEY `Term_FK_idx` (`FK_Cycle_ID`),\n" +
                    "  CONSTRAINT `Term_FK` FOREIGN KEY (`FK_Cycle_ID`) REFERENCES `cycle` (`Cycle_ID`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=365 DEFAULT CHARSET=utf8;");


            this.createTable("CREATE TABLE `term_contains_courses` (\n" +
                    "  `FK_C_code` varchar(15) NOT NULL,\n" +
                    "  `FK_T_ID` int(11) NOT NULL,\n" +
                    "  PRIMARY KEY (`FK_C_code`,`FK_T_ID`),\n" +
                    "  KEY `Term_Contains_Courses_FK_T_idx` (`FK_T_ID`),\n" +
                    "  CONSTRAINT `Term_Contains_Courses_FK_C` FOREIGN KEY (`FK_C_code`) REFERENCES `course` (`C_code`) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "  CONSTRAINT `Term_Contains_Courses_FK_T` FOREIGN KEY (`FK_T_ID`) REFERENCES `term` (`T_ID`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;");


            this.createTable("CREATE TABLE `university` (\n" +
                    "  `Uni_name` varchar(255) NOT NULL,\n" +
                    "  `College_name` varchar(100) DEFAULT NULL,\n" +
                    "  `Uni_logo` varchar(255) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`Uni_name`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;");


            this.createTable("CREATE TABLE `update_formative` (\n" +
                    "  `FK_Formative_ID` int(11) NOT NULL,\n" +
                    "  `FK_Faculty_ID` int(11) NOT NULL,\n" +
                    "  `Date` date DEFAULT NULL,\n" +
                    "  `Submit` tinyint(1) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`FK_Formative_ID`,`FK_Faculty_ID`),\n" +
                    "  KEY `Update_formative_FK_faculty_idx` (`FK_Faculty_ID`),\n" +
                    "  CONSTRAINT `Update_formative_FK_faculty` FOREIGN KEY (`FK_Faculty_ID`) REFERENCES `faculty_member` (`Faculty_ID`) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "  CONSTRAINT `Update_formative_FK_formative` FOREIGN KEY (`FK_Formative_ID`) REFERENCES `formative` (`Formative_ID`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;");


            this.createTable("CREATE TABLE `update_summative` (\n" +
                    "  `FK_Summative_ID` int(11) NOT NULL,\n" +
                    "  `FK_Faculty_ID` int(11) NOT NULL,\n" +
                    "  `Date` date DEFAULT NULL,\n" +
                    "  `Submit` tinyint(1) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`FK_Summative_ID`,`FK_Faculty_ID`),\n" +
                    "  KEY `Update_summative_FK_idx` (`FK_Faculty_ID`),\n" +
                    "  CONSTRAINT `Update_summative_FK_faculty` FOREIGN KEY (`FK_Faculty_ID`) REFERENCES `faculty_member` (`Faculty_ID`) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "  CONSTRAINT `Update_summative_FK_summative` FOREIGN KEY (`FK_Summative_ID`) REFERENCES `summative` (`Summative_ID`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;");


            this.createTable("CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `abetasdb`.`users` AS select `abetasdb`.`superuser`.`Adm_ID` AS `lvl`," +
                    "`abetasdb`.`superuser`.`Super_Username` AS `username`,`abetasdb`.`superuser`.`Super_Password` AS `password`,`abetasdb`.`superuser`.`Super_Email` AS `email`," +
                    "`abetasdb`.`superuser`.`Super_ID` AS `ID` from `abetasdb`.`superuser` union all select 2 AS `2`,`abetasdb`.`faculty_member`.`Faculty_Username` AS `Faculty_Username`," +
                    "`abetasdb`.`faculty_member`.`Faculty_Password` AS `Faculty_Password`,`abetasdb`.`faculty_member`.`Faculty_Email` AS `Faculty_Email`,`abetasdb`.`faculty_member`.`Faculty_ID` " +
                    "AS `Faculty_ID` from `abetasdb`.`faculty_member` union all select 3 AS `3`,`abetasdb`.`evaluator`.`E_Username` AS `E_Username`,`abetasdb`.`evaluator`.`E_Password` AS `E_Password`," +
                    "NULL AS `NULL`,`abetasdb`.`evaluator`.`E_ID` AS `E_ID` from `abetasdb`.`evaluator`;\n");



















            pout.println("<h4>Tables Created!</h4>");



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

    public void deleteDB() throws ClassNotFoundException, SQLException {

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
            String query = "DROP DATABASE abetasdb";

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

        //Connection connection = null;
        //PreparedStatement preparedStatement = null;

        //ResultSet rs = null;
        boolean rsr = false;

        Connection conn = null;
        Statement s = null;
        ResultSet Result = null;

        try {

            Class.forName(jdbcDriver);
            conn = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=abetas");
            s = conn.createStatement();



            String query = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = 'abetasdb';";

            Result = s.executeQuery(query);

            /*
             *  Get connection from the DataSource
             */

            //connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            //preparedStatement = connection.prepareStatement(query);
            //preparedStatement.setInt(1, 10);

            //rs = preparedStatement.executeQuery();
            if(Result.next()){
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

            return rsr;

        }

    }

}
