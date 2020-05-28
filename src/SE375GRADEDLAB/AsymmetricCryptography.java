package SE375GRADEDLAB;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import java.util.Base64;
public class AsymmetricCryptography {

    static byte[] plain_text;
    static byte[] encrypted_text;
    static byte[] decrypted_text;
    static Cipher cipher;

    public static String printBytes(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (byte b : data) {
            sb.append(String.format("%02X:", b));
        }
        return sb.toString();
    }


    public String EncryptFromClient(String rawMessage) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException {

        try {
            plain_text = rawMessage.getBytes("UTF-8");
            //System.out.println("Original data:" + rawMessage);
            System.out.println(printBytes(plain_text));

            byte[] keyBytes = Files.readAllBytes(new File("ClientKeyStore/privateKey").toPath());
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = kf.generatePrivate(spec);

            keyBytes = Files.readAllBytes(new File("ClientKeyStore/publicKey").toPath());
            X509EncodedKeySpec spec2 = new X509EncodedKeySpec(keyBytes);
            PublicKey publicKey = kf.generatePublic(spec2);

            // STEP 2. Get a Cipher for the desired transformation
            cipher = Cipher.getInstance("RSA");

            // STEP 3. Choose a method
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            // STEP 4. Perform the operation
            encrypted_text = cipher.doFinal(plain_text);

        } catch (UnsupportedEncodingException ex) {
            System.err.println("Couldn't create key: " + ex.getMessage());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            System.err.println(e.getMessage());
        }  catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | InvalidKeySpecException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return encrypted_text.toString();
    }

    public String DecryptFromClient(String rawMessage) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException {
        try {
            plain_text = rawMessage.getBytes("UTF-8");
            //System.out.println("Original data:" + msg);
            //System.out.println(printBytes(plain_text));

            // STEP 1. Generate the Keys. Read them from a file.
            byte[] keyBytes = Files.readAllBytes(new File("ClientKeyStore/privateKey").toPath());
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = kf.generatePrivate(spec);

            keyBytes = Files.readAllBytes(new File("ClientKeyStore/publicKey").toPath());
            X509EncodedKeySpec spec2 = new X509EncodedKeySpec(keyBytes);
            PublicKey publicKey = kf.generatePublic(spec2);

            // STEP 2. Get a Cipher for the desired transformation
            cipher = Cipher.getInstance("RSA");

            // STEP 3. Choose a method
            //cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            // STEP 4. Perform the operation
            //System.out.println("Encrypted data:" + printBytes(encrypted_text));
            //String msgBase64 = Base64.getEncoder().encodeToString(encrypted_text);
            //System.out.println("Base64 Encoded String (Basic) :" + msgBase64);

            // STEP 5. Undo the operation

            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            decrypted_text = cipher.doFinal(encrypted_text);
            //System.out.println("Decrypted data:" + printBytes(decrypted_text));

         } catch (UnsupportedEncodingException ex) {
            System.err.println("Couldn't create key: " + ex.getMessage());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            System.err.println(e.getMessage());
        }  catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | InvalidKeySpecException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return decrypted_text.toString();
    }

    public String EncryptFromServer(String rawMessage)
    {

        try {
            plain_text = rawMessage.getBytes("UTF-8");
            //System.out.println("Original data:" + rawMessage);
            //System.out.println(printBytes(plain_text));

            byte[] keyBytes = Files.readAllBytes(new File("ServerKeyStore/privateKey").toPath());
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = kf.generatePrivate(spec);

            keyBytes = Files.readAllBytes(new File("ServerKeyStore/publicKey").toPath());
            X509EncodedKeySpec spec2 = new X509EncodedKeySpec(keyBytes);
            PublicKey publicKey = kf.generatePublic(spec2);

            // STEP 2. Get a Cipher for the desired transformation
            cipher = Cipher.getInstance("RSA");

            // STEP 3. Choose a method
            //cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            // STEP 4. Perform the operation
            encrypted_text = cipher.doFinal(plain_text);

        } catch (UnsupportedEncodingException ex) {
            System.err.println("Couldn't create key: " + ex.getMessage());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            System.err.println(e.getMessage());
        }  catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | InvalidKeySpecException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return encrypted_text.toString();

    }

    public String DecryptFromServer(String rawMessage)
    {
        try {
            plain_text = rawMessage.getBytes("UTF-8");
            //System.out.println("Original data:" + rawMessage);
            //System.out.println(printBytes(plain_text));

            byte[] keyBytes = Files.readAllBytes(new File("ServerKeyStore/privateKey").toPath());
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = kf.generatePrivate(spec);

            keyBytes = Files.readAllBytes(new File("ServerKeyStore/publicKey").toPath());
            X509EncodedKeySpec spec2 = new X509EncodedKeySpec(keyBytes);
            PublicKey publicKey = kf.generatePublic(spec2);

            // STEP 2. Get a Cipher for the desired transformation
            cipher = Cipher.getInstance("RSA");

            // STEP 3. Choose a method
            //cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            //
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            decrypted_text = cipher.doFinal(encrypted_text);
            //System.out.println("Decrypted data:" + printBytes(decrypted_text));


        } catch (UnsupportedEncodingException ex) {
            System.err.println("Couldn't create key: " + ex.getMessage());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            System.err.println(e.getMessage());
        }  catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | InvalidKeySpecException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return decrypted_text.toString();

    }


}
