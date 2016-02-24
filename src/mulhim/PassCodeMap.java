package mulhim;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

/**
 * Created by Mohammed on 1/25/2016.
 */

public final class PassCodeMap {
    private static    Map<String,String> myMap = new HashMap<String,String>();

    public static ArrayList<String> in= new ArrayList<String>();

    public   static  void  setPassCode(String email, String code){

        TimeClass currentTime = new TimeClass();
        myMap.put(email, String.valueOf(in.add(code)));
        myMap.put(email, String.valueOf(in.add(String.valueOf(currentTime.timerReset()))));
//currentTime.timerCalculate(email);

    }
    public static boolean  checkKey(String email){
        try {
            in.get(0);

        }
        catch (IndexOutOfBoundsException i){
            return false;

        }
        return myMap.containsKey(email);

    }
    public static String getpassKey(String emailCode, String code){

        if(myMap.containsKey(emailCode))

            System.out.println("The code from getpassKey is "+in.get(0));
        final String co = in.get(0);
        return co;
    }

    public static int getMapSize(){

    return myMap.size();
    }
    public static int removeElement(String email){

        myMap.remove(email);
        return myMap.size();
    }


}
