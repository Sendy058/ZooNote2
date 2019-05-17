package sample;

import java.math.*;
import java.security.*;

public class Encryption {

    /**
     * This class hashes password using a cryptographic hash function containing a string of digits
     * created by a one-way hashing formula.
     *
     * @author Martin Mochnacky
     * @param input password
     * @return hashed input parameter
     */

    public static String MD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

