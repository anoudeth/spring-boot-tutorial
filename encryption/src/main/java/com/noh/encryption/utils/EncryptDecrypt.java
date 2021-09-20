package com.noh.encryption.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONObject;

public class EncryptDecrypt {



    // #################

    public static String encrypt(String Data, String secret) throws Exception {
        Key key = generateKey(secret);
        Cipher c = Cipher.getInstance("AES");
        c.init(1, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = Base64.getEncoder().encodeToString(encVal);
        return encryptedValue;
    }
    public static String decrypt(String strToDecrypt, String secret) {
        try {
            Key key = generateKey(secret);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(2, key);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
            return null;
        }
    }
    public static String encodeKey(String str) {
        byte[] encoded = Base64.getEncoder().encode(str.getBytes());
        return new String(encoded);
    }

    public static Key generateKey(String secret) throws Exception {
        byte[] decoded = Base64.getDecoder().decode(secret.getBytes());
        Key key = new SecretKeySpec(decoded, "AES");
        return key;
    }


    public static void main(String[] args) throws ParseException, NoSuchAlgorithmException {
        System.out.println("--- test encrypt ---");
        String secretKey = "30F958A8260B3730C547AD520F723AAE";

        String originalString = "{\n" +
                "\t\"instid\":\"IB\",              \n" +
                "\t\"callbackurl\":\"http://merchant.com:7000/ib/callback\",        \n" +
                "\t\"redirecturl\":\"https://merchant.com/\",\n" +
                "\t\"merchrefno\":\"ref123456\",\n" +
                "\t\"merchantid\":\"010920211000234\",\n" +
                "\t\"txnamount\":\"100\",\n" +
                "\t\"paymode\":\"ON\",\n" +
                "\t\"txnccy\":\"418\",\n" +
                "\t\"customer\":{\n" +
                "\t\t\"mobile\":\"2059555555\",\n" +
                "\t\t\"name\":\"anoudeth\",\n" +
                "\t\t\"address\":\"vientiane\",\n" +
                "\t\t\"email\":\"noh@ib.com\"\n" +
                "\t}\n" +
                "}";

        try {
            String encryptedString = encrypt(originalString, encodeKey(secretKey));
            String decryptedString = decrypt(encryptedString, encodeKey(secretKey));
            System.out.println("original message: " + originalString);
            System.out.println("after encrypted : " + encryptedString);
            System.out.println("after decrypted : " + decryptedString);
        } catch (Exception e) {
            System.out.println("error: " + e.toString());
            e.printStackTrace();
        }

        System.out.println("\n\n\n-- decrypt response msg");
        String msg = "T7vc/YH0VgWsF9JHvsrz5iAwsBGlThL1jDqDpeGzZNquOYBxOUEHk0lmTtsJCQy6pIG3p49XgcOYbz00BuFtJdo6UlHotqPOluXlGC0jVFlNu1wYTCZoWg9m4DvpfVNJTp2jIxusORJmNQ6KOFExKCPgnZ9QpX+ge8rskJXNE1bCP78q39Rcqyma78FFNTfVm+DD1ywt40DBJoaSQBrScH5JjMfWi0SQN9WhDjVWSS4/mSvkH5AFjph1JMUeN0/YJXFTUauKwSb0vu13WO48z+Ixd/MgxLpa4DZv9659x4AkU2q8l04AwRO6DEhYyK2Mo91m8eo4eeBEZ46zCHFQaKXTfwPYoT87ppctgaVCBIIGh2UogbjK2C8Knl2ffsmRwVOoT1rKwY8Xww8AkbWmUA==";
        System.out.println("response decrypt: " + decrypt(msg, encodeKey("IB20185362111111")));


    }
}
