package com.kellyfj;

import org.apache.commons.codec.binary.Base64;

import java.security.SecureRandom;

public class Main {
  private static final String TEST_STRING = "The quick brown fox jumped over the lazy dog";
  private static final int MAX_RUNS = 1000;
  private static final byte[] GCM_KEY_BYTES = Base64.decodeBase64("oa7vzk8r1fvEAXsUb2mlpW/+rS8KEfVlh6by+Sv+qb0=");
  private static final String SCBE_STAGING_ECB_KEY = "oa7vzk8r1fvEAXsUb2mlpW/+rS8KEfVlh6by+Sv+qb0=";

  private static final SecureRandom random = new SecureRandom();
  private static final byte[][] arrayOfIvs = new byte[MAX_RUNS][16];

  public static void main(String[] args) throws Exception {

    //Initialize enough IVs
    for(int i=0; i< MAX_RUNS; i++) {
      arrayOfIvs[i] = getRandomIV();
    }

    doGCMEncryption();
    System.out.println("==================");

    doECBEncryption();
    System.out.println("==================");

    doCBCEncryption();
    System.out.println("==================");


  }


  private  static byte[] getRandomIV() {
    byte[] iv = new byte[16];
    random.nextBytes(iv);
    return iv;
  }


  private static void doGCMEncryption() throws Exception {
    IKeyFetcher kf = new KeyFetcher();
    String backAgain = null;
    byte[] encrypted = new byte[]{};
    //GCM
    long start = System.currentTimeMillis();
    for (int i = 0; i < MAX_RUNS; i++) {
      byte[] data = (TEST_STRING + i).getBytes();
      encrypted = AES_GCM_CryptoService.encrypt(data, GCM_KEY_BYTES, kf.getIV());
    }
    long end = System.currentTimeMillis();
    System.out.println("GCM --> " + new String(encrypted, "UTF8"));
    double diff = end - start;
    System.out.println("GCM encryption took an average of " + diff / MAX_RUNS + " msecs");

    start = System.currentTimeMillis();
    for (int i = 0; i < MAX_RUNS; i++) {
      byte[] decrypted = AES_GCM_CryptoService.decrypt(encrypted, GCM_KEY_BYTES, kf.getIV());
      backAgain = new String(decrypted, "UTF8");
    }
    end = System.currentTimeMillis();
    System.out.println("GCM --> " + backAgain);
    diff = end - start;
    System.out.println("GCM decryption took an average of " + diff / MAX_RUNS + " msecs");
  }

  private static void doCBCEncryption() throws Exception {
    IKeyFetcher kf = new KeyFetcher();
    String backAgain = null;
    byte[] encrypted = new byte[]{};
    //CBC Encryption
    long start = System.currentTimeMillis();
    for (int i = 0; i < 20; i++) {
      byte[] data = (TEST_STRING + i).getBytes();
      encrypted = AES_CBC_CryptoService.encrypt(data, kf.getKey(), arrayOfIvs[i], kf.getSalt());
    }
    long end = System.currentTimeMillis();
    System.out.println("CBC --> " + new String(encrypted, "UTF8"));
    double diff = end - start;
    System.out.println("CBC encryption took an average of " + diff / 20 + " msecs");

    //CBC Decryption
    start = System.currentTimeMillis();
    for (int i = 0; i < 20; i++) {
      byte[] decrypted = AES_CBC_CryptoService.decrypt(encrypted, kf.getKey(), arrayOfIvs[i], kf.getSalt());
      backAgain = new String(decrypted, "UTF8");
    }
    end = System.currentTimeMillis();
    System.out.println("CBC --> " + backAgain);
    diff = end - start;
    System.out.println("CBC decryption took an average of " + diff / 20 + " msecs");

  }

  private static void doECBEncryption() throws Exception {
    String backAgain = null;
    byte[] encrypted = new byte[]{};
    byte[] keyBytes = Base64.decodeBase64(SCBE_STAGING_ECB_KEY);
    long start;
    long end;
    start = System.currentTimeMillis();
    for (int i = 0; i < MAX_RUNS; i++) {
      byte[] data = (TEST_STRING + i).getBytes();
      encrypted = AES_ECB_CryptoService.encrypt(data, keyBytes);
    }
    end = System.currentTimeMillis();
    System.out.println("ECB --> " + new String(encrypted, "UTF8"));
    double diff = end - start;
    System.out.println("ECB encryption took an average of " + diff / MAX_RUNS + " msecs");

    start = System.currentTimeMillis();
    for (int i = 0; i < MAX_RUNS; i++) {
      byte[] decrypted = AES_ECB_CryptoService.decrypt(encrypted, keyBytes);
      backAgain = new String(decrypted, "UTF8");
    }
    end = System.currentTimeMillis();
    System.out.println("ECB --> " + backAgain);
    diff = end - start;
    System.out.println("ECB decryption took an average of " + diff / MAX_RUNS + " msecs");

  }
}
