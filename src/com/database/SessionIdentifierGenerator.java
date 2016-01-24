package com.database;

/**
 * Created by Ibrahim Abuaqel on 1/24/2016.
 */
import java.math.BigInteger;
import java.security.SecureRandom;

public final class SessionIdentifierGenerator {
    private SecureRandom random = new SecureRandom();

    public String nextSessionId() {
        return new BigInteger(130, random).toString(32);
    }
}