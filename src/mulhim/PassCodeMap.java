package mulhim;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mohammed on 1/25/2016.
 */
public final class PassCodeMap {
    private static Map<String,Integer> myMap = new HashMap<String,Integer>();

    public static void  setPassCode(String email,int passCode){
        myMap.put(email,passCode);

    }
    public static boolean  checkKey(String email){
        return myMap.containsKey(email);
    }
    public static int getpassKey(String emailCode){

        return myMap.get(emailCode);
    }

public static int getMapSize(){

    return myMap.size();
}

}
