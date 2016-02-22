package mulhim;

import sun.util.BuddhistCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Mohammed on 2/11/2016.
 */
@WebServlet(name = "tim",
        urlPatterns = {"/Tim"})
public class testTime extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//            Date date1 = sdf.parse("31/12/2009");
//            Date date2 = sdf.parse("31/10/2010");
            String date3 = sdf.format(now);
//            System.out.println(sdf.format(date1));
//            System.out.println(sdf.format(date2));
            System.out.println(sdf.format(date3));


//            if(date3.compareTo(date2)>0){
//                System.out.println("Date3 is after Date2");
//            }else if(date1.compareTo(date2)<0){
//                System.out.println("Date1 is before Date2");
//            }else if(date1.compareTo(date2)==0){
//                System.out.println("Date1 is equal to Date2");
//            }else{
//                System.out.println("How to get here?");
//            }

        }catch(ParseException ex){
            ex.printStackTrace();
        }
    }
}
