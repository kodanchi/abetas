package mulhim;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

/**
 * Created by Mohammed on 1/25/2016.
 */

public final class PassCodeMap {
    private static    HashMap<String,ArrayList<String >> myMap = new HashMap<String,ArrayList<String>>();

    public static ArrayList<String> in= new ArrayList<String>();

    public   static  void  setPassCode(String email, String code){

        TimeClass currentTime = new TimeClass();
        System.out.println("From setPassCode is "+code);
         ArrayList<String> listOne = new ArrayList<String>();

      myMap.put(email,new ArrayList<>());
        myMap.get(email).add(code);
        myMap.get(email).add(currentTime.timerReset());
        //myMap.put(email, String.valueOf(in.add(String.valueOf(currentTime.timerReset()))));
currentTime.timerCalculate(email);

    }
    public static boolean  checkKey(String email){
        try {
            myMap.get(email).get(0);

        }
        catch (IndexOutOfBoundsException i){
            return false;

        }
        catch (NullPointerException i){

            return false;
        }
        return myMap.containsKey(email);

    }
    public static String getpassKey(String emailCode){

        if(myMap.containsKey(emailCode)) {

            System.out.println("The code from index 0  is " + myMap.get(emailCode).get(0));

        }


        return myMap.get(emailCode).get(0);
    }

    public static int getMapSize(){

    return myMap.size();
    }
    public static int removeElement(String email){

        myMap.remove(email);
        return myMap.size();
    }


}
