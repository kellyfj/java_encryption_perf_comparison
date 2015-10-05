package com.kellyfj;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static javax.crypto.Cipher.DECRYPT_MODE;
import static javax.crypto.Cipher.ENCRYPT_MODE;
import static javax.crypto.Cipher.getInstance;


public class AES_CBC_CryptoService {

  public static final String ALGORITHM_BLOCK_PADDING = "AES/CBC/PKCS5Padding";
  private static final String ALGORITHM = "AES";

  public static byte[] decrypt(final byte[] data, String key, byte[] iv, byte[] salt) throws Exception {

    IvParameterSpec paramSpec = new IvParameterSpec(iv);

    SecretKeySpec keySpec = generateSecretKeySpec(key, salt);

    Cipher cipher = getInstance(ALGORITHM_BLOCK_PADDING);
    cipher.init(DECRYPT_MODE, keySpec, paramSpec);
    byte[] result = cipher.doFinal(Base64.decodeBase64(data));

    return result;
  }

  public static byte[] encrypt(final byte[] data, String key, byte[] iv, byte[] salt) throws Exception {
    IvParameterSpec paramSpec = new IvParameterSpec(iv);

    SecretKeySpec keySpec = generateSecretKeySpec(key, salt);

    Cipher cipher = getInstance(ALGORITHM_BLOCK_PADDING);
    cipher.init(ENCRYPT_MODE, keySpec, paramSpec);
    byte [] result = cipher.doFinal(data);

    return Base64.encodeBase64(result);
  }

  public static SecretKeySpec generateSecretKeySpec(String key, byte[] salt)
      throws NoSuchAlgorithmException, InvalidKeySpecException {
    int keySize = 256;

    // Derive a key from the password with the proper length
    SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
    PBEKeySpec spec = new PBEKeySpec(
        key.toCharArray(),
        salt, 65535,
        keySize
    );
    SecretKey secretKey = factory.generateSecret(spec);
    return new SecretKeySpec(secretKey.getEncoded(), ALGORITHM);
  }

}
