package passRestore;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by Ibrahim Abuaqel on 1/24/2016.
 * source : http://tomcat.apache.org/tomcat-6.0-doc/jndi-datasource-examples-howto.html#MySQL_DBCP_Example
 */
public class loginDB {
    private Connection conn;
    private Statement stmt;
    private ResultSet result;
    DataSource dataSource = null;


    public void connect() throws ClassNotFoundException, SQLException {

        try
        {
            /*
             *  Using JDNI lookup get the DataSource.
             */

            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/abetasDB");

        }
        catch( Exception exe )
        {
            exe.printStackTrace();
        }

    }


}
