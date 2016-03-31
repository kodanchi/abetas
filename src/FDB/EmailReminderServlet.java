package FDB;

import passReset.SendEmail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Mohammed on 3/30/2016.
 */
@WebServlet(name = "EmailReminderServlet", urlPatterns = {"/Emailrem"})
public class EmailReminderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String emailList = request.getParameter("emailList");
        String[] e = request.getParameterValues("emailList");
        /*ArrayList<String> e= new ArrayList<String>();
int x=0;
String ema=emailList;
        System.out.println("     "+ema);


int c=0;
        while (ema.indexOf(';')!=-1||ema.indexOf(';')!=0) {
            ema=ema.substring(0, ema.indexOf(';'));
        c++;
        }

        System.out.println(c+"          ggggg");
System.out.println(emailList);

for (int i = 0; i<2;i++) {
System.out.println("i    "+i);
    System.out.println(emailList.substring(0, emailList.indexOf(';')));
    e.add(emailList.substring(0, emailList.indexOf(';')));
    System.out.println("fdfdfffdfdfdfd");



    emailList=emailList.substring(0, emailList.indexOf(';'));*//*
}
System.out.print("dvdf");
        System.out.println("This is e    "+e);
        SendEmail sendemail = new SendEmail();

        for (int i=0;i<e.size();i++){
            sendemail.sendMsg("Hello this is reminder","Reminder","\""+e.get(i)+"\"");
        }*/

        SendEmail sendemail = new SendEmail();
System.out.println("010 010 010 010 ");

        for (int i = 0 ; i < e.length;i++){
            System.out.println(e[i]);
            if (i==0) {
                //sendemail.sendMsg("Hello this is reminder", "Reminder", e[i].substring(1, ','));
            System.out.println(e[i].substring(1, ','));
            }
            if (i==e.length-1){
                //sendemail.sendMsg("Hello this is reminder", "Reminder", e[i].substring(0, ']'));
                System.out.println(e[i].substring(1, ']'));
            }
            //sendemail.sendMsg("Hello this is reminder", "Reminder", e[i].substring(0, ',') );
            System.out.println(e[i].substring(0, ','));

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
