package EDB;

import org.apache.commons.codec.binary.Base64;

/**
 * EncDec is used to encrypt/decrypt http request parameters.
 */
public class EncDec {
    /**
     * getEncr used to encrypt the password
     * @param value is the hash value
     * @return the encrypted value
     */
    public static String getEncr(String value){
        return new String(Base64.encodeBase64(value.getBytes()));
    }

    /**
     * getdecrypt used to decrypt the password
     * @param value is the hash value
     * @return the decrypted value
     */
    public static String getDecr(String value){
        byte[] valueDecoded= Base64.decodeBase64(value);//decoding part
        return new String(valueDecoded);
    }
}
