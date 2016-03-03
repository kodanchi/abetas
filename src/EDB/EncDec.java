package EDB;

import org.apache.commons.codec.binary.Base64;

/**
 * Created by Mojahed on 3/3/2016.
 */
public class EncDec {

    public static String getEncr(String value){
        return new String(Base64.encodeBase64(value.getBytes()));
    }
    public static String getDecr(String value){
        byte[] valueDecoded= Base64.decodeBase64(value);//decoding part
        return new String(valueDecoded);
    }
}
