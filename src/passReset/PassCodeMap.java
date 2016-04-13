package passReset;

import java.util.*;

public final class PassCodeMap {
    /**
     * PassCodeMap class used to store the random values that generated temporary to reset the passwor for the user
     * First thing using HashMap array to store the key and the value of the key
     * in this case the key is the user email and the value is the random number
     * Since the random number is temporary, the timer need to be used
     * the arraylist contain now the passcode (random number)
     */
    private static    HashMap<String,ArrayList<String >> myMap = new HashMap<String,ArrayList<String>>();

    public static ArrayList<String> in= new ArrayList<String>();

    /**
     *  setPassCode method will store the hash key "email" and the value "code"
     *             after that it will call time class to initiate the time that the user request the passcode
     *             the time used to clear the hash map key within 24 hours
     * @param email contatin the email that the user entered to reset the password
     * @param code contain the passcode number that has been initated to reset the password
     */
    public   static  void  setPassCode(String email, String code){


        EmailTimeClass currentTime = new EmailTimeClass();
        System.out.println("From setPassCode is "+code);
         ArrayList<String> listOne = new ArrayList<String>();

      myMap.put(email,new ArrayList<>());
        myMap.get(email).add(code);

currentTime.timerCalculate(email);

    }

    /**
     *checkKey method will return true if the passcode exist and false if it is not exist
     * @param email contatin the email that the user entered to check if there is a reset password request during the last 24 hours
     * @return will return if the passcode exist true otherwise false will be returned
     */
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

    /**
     * getpassKey method will provide the value of the key
     * @param emailCode contain the passcode number to check if it is matches or not
     * @return will return the passcode from the has value
     */
    public static String getpassKey(String emailCode){

        if(myMap.containsKey(emailCode)) {

            System.out.println("The code from index 0  is " + myMap.get(emailCode).get(0));

        }


        return myMap.get(emailCode).get(0);
    }

    /**
     * getMapSize method will measure the size of the hash map
     * @return the size of the hash map
     */
    public static int getMapSize(){

    return myMap.size();
    }

    /**
     * removeElement method sed to remove the hash map key with its value
     * @param email used to remove the hash map key with its value using the email
     * @return the new size of the hash map after removing the key
     */
    public static int removeElement(String email){

        myMap.remove(email);
        return myMap.size();
    }


}
