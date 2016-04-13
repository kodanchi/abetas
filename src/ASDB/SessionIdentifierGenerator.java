package ASDB;

/**
 * Created by Ibrahim Abuaqel on 1/24/2016.
 */

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * SessionIdentifierGenerator is used to generate Secure random number.
 */
public final class SessionIdentifierGenerator {
    private SecureRandom random = new SecureRandom();

    /**
     *  used to generate BigInteger, uniformly distributed over the range 0 to integer size long.
     * @return random number as String.
     */
    public String nextSessionId() {
        return new BigInteger(130, random).toString(32);
    }
}