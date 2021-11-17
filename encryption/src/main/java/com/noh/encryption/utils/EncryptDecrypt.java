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
        System.out.println("encodeSecretKey: " + new String(encoded));
        return new String(encoded);
    }

    public static Key generateKey(String secret) throws Exception {
        byte[] decoded = Base64.getDecoder().decode(secret.getBytes());
        System.out.println("decodeSecretKey: " + new String(decoded));
        Key key = new SecretKeySpec(decoded, "AES");
        System.out.println("generateKey: " + key);
        return key;
    }


    public static void main(String[] args) throws ParseException, NoSuchAlgorithmException {
        System.out.println("--- test encrypt ---");
        String secretKey = "9EF9323CC26D0015BC81F870C3A6CFFB";

//        String originalString = "{\n" +
//                "\t\"instid\":\"IB\",              \n" +
//                "\t\"callbackurl\":\"https://ibcooluat.iblaos.com/ibecommerce/home\",        \n" +
//                "\t\"redirecturl\":\"https://merchant.com/\",\n" +
//                "\t\"merchrefno\":\"ref123456\",\n" +
//                "\t\"merchantid\":\"140920211000235\",\n" +
//                "\t\"txnamount\":\"100\",\n" +
//                "\t\"paymode\":\"ON\",\n" +
//                "\t\"txnccy\":\"418\",\n" +
//                "\t\"customer\":{\n" +
//                "\t\t\"mobile\":\"2059366665\",\n" +
//                "\t\t\"name\":\"noh der\",\n" +
//                "\t\t\"address\":\"vientiane\",\n" +
//                "\t\t\"email\":\"noh@gmail.com\"\n" +
//                "\t}\n" +
//                "}";
        String originalString = "{\"instid\":\"IB\",\"callbackurl\":\"http://192.168.33.29:8894/core/api/callback/test\", \"redirecturl\":\"https://www.google.com\", \"merchrefno\":\"00000000001\",\"merchantid\":\"140920211000235\",\"txnamount\":\"10000\",\"paymode\":\"ON\",\"txnccy\":\"418\",\"customer\":{\"mobile\":\"2055555555\",\"name\":\"AnoudethDer\",\"address\":\"Vientiane, Laos\",\"email\":\"nohder@gmail.com\"}}";

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
        String msg = "ECoxGxB8AMGSIlzSG76dHCVWvZjjfpaXKn/uqiKYkoITkJGMXFM0PXsbOy7a+mpARbcS7ux8mpZwwbRM0HNda8ITjCMoA81hIZ2w2rXi5nYXtS5Po/I38canuJgB/0AYjvaY/+avrhGNOH1hvH278GqSgLbGFHzphnaioajZxtZxlJjpwz9tRyRHa5smR1BnbYHlOchDkDrS5AHF7at2sWPnpiMWUIiEr2kA6eNdcQJTlEJC0C/9wjgNCV7ucNRYk8izJp7UrMZ4JpUD3da7f+4ELAWPp9/deELsom+KnnNYNTvPctsFTPn8q2UT3fbCpR9/jVZouTl3p1BD/to+UcalBgY9HVQWtjobbJP1YtYykye2BzAkP9PVQ9MbURhXyYpEbwJuspEsCtHqwSxznFdBEuyfbG2Y9bpfVuagm2lm1u9rgWsJtQkyweOIg7IkRcuXKzbVZbLp2syDmKOpdlD9Ty4nOJ+4ZkS18sSDCdXrvvBkJUt+Q+qTaJ3ZLDlK";
        System.out.println("response decrypt: " + decrypt(msg, encodeKey(secretKey)));


    }
}
