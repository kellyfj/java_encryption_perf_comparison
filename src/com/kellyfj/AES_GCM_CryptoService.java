package com.kellyfj;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import static javax.crypto.Cipher.DECRYPT_MODE;
import static javax.crypto.Cipher.getInstance;

/**
 * Provides methods to encrypt OR decrypt using AES algorithm, and a single symmetric key.
 */
public class AES_GCM_CryptoService {

  /**
   * AES with default block mode and padding: AES/ECB/PKCS5Padding
   */
  public static final String ALGORITHM_BLOCK_PADDING = "AES/GCM/NoPadding";
  private static final String ALGORITHM = "AES";

  public static byte[] decrypt(final byte[] data, byte[] key, byte[] ivBytes) throws Exception {
    GCMParameterSpec paramSpec = new GCMParameterSpec(16 * Byte.SIZE , ivBytes);
    SecretKeySpec skeySpec = new SecretKeySpec(key, ALGORITHM);
    Cipher cipher = getInstance(ALGORITHM_BLOCK_PADDING);
    cipher.init(DECRYPT_MODE, skeySpec, paramSpec);
    byte[] result = cipher.doFinal(Base64.decodeBase64(data));

    return result;
  }

  public static byte[] encrypt(final byte[] data, byte[] key, byte[] ivBytes) throws Exception {
    GCMParameterSpec paramSpec = new GCMParameterSpec(16 * Byte.SIZE , ivBytes);
    SecretKeySpec skeySpec = new SecretKeySpec(key, ALGORITHM);
    Cipher cipher = Cipher.getInstance(ALGORITHM_BLOCK_PADDING);

    cipher.init(Cipher.ENCRYPT_MODE, skeySpec, paramSpec);

    byte[] original = cipher.doFinal(data);
    return Base64.encodeBase64(original);
  }


}
