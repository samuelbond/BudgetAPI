package uk.co.platitech.helpers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Samuel on 11/8/2014.
 */
public class HashGenerator {
    private MessageDigest md;
    private final String stringToHash;
    private String algorithm = "SHA-512";

    public HashGenerator(String stringToHash)
    {
        this.stringToHash = stringToHash;
    }

    public HashGenerator(String stringToHash, String algorithm)
    {
        this.stringToHash = stringToHash;
        this.algorithm = algorithm;
    }

    public String generateHash()
    {
        try
        {
            md= MessageDigest.getInstance(algorithm);
            md.update(stringToHash.getBytes());
            byte[] mb = md.digest();
            String out = "";
            for (int i = 0; i < mb.length; i++)
            {
                byte temp = mb[i];
                String s = Integer.toHexString(new Byte(temp));
                while (s.length() < 2)
                {
                    s = "0" + s;
                }
                s = s.substring(s.length() - 2);
                out += s;
            }
            return out;
        }
        catch (NoSuchAlgorithmException e)
        {
            return e.getMessage();
        }
    }
}
