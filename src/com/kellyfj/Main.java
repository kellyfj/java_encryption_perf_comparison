package com.kellyfj;

import org.apache.commons.codec.binary.Base64;

import java.util.Arrays;

public class Main {
  private static final String TEST_STRING = "The quick brown fox jumped over the lazy dog";
  private static final int MAX_RUNS = 1000;

  public static void main(String[] args) throws Exception {
    IKeyFetcher kf = new KeyFetcher();
    String backAgain=null;
    byte[] encrypted = new byte[]{};
    long start =0;
    long end = 0;
    byte[] keyBytes = Base64.decodeBase64("oa7vzk8r1fvEAXsUb2mlpW/+rS8KEfVlh6by+Sv+qb0=");

    //GCM
    start = System.currentTimeMillis();
    for(int i=0; i< MAX_RUNS; i++) {
      byte[] data = TEST_STRING.getBytes();
      encrypted = AES_GCM_CryptoService.encrypt(data,keyBytes, kf.getIV());
    }
    end = System.currentTimeMillis();
    System.out.println("GCM --> " + new String(encrypted, "UTF8"));
    System.out.println("GCM encryption took an average of " + (end-start)/MAX_RUNS + " msecs");

    start = System.currentTimeMillis();
    for(int i=0; i< MAX_RUNS; i++) {
      byte[] decrypted = AES_GCM_CryptoService.decrypt(encrypted, keyBytes, kf.getIV());
      backAgain = new String(decrypted, "UTF8");
    }
    end = System.currentTimeMillis();
    System.out.println("GCM --> " + backAgain);
    System.out.println("GCM decryption took an average of " + (end-start)/MAX_RUNS + " msecs");

    System.out.println("==================");

    //ECB Encryption
    start = System.currentTimeMillis();
    for(int i=0; i< MAX_RUNS; i++) {
      byte[] data = TEST_STRING.getBytes();
      encrypted = AES_ECB_CryptoService.encrypt(data, keyBytes);
    }
    end = System.currentTimeMillis();
    System.out.println("ECB --> " + new String(encrypted, "UTF8"));
    System.out.println("ECB encryption took an average of " + (end-start)/MAX_RUNS + " msecs");

    start = System.currentTimeMillis();
    for(int i=0; i< MAX_RUNS; i++) {
      byte[] decrypted = AES_ECB_CryptoService.decrypt(encrypted, keyBytes);
      backAgain = new String(decrypted, "UTF8");
    }
    end = System.currentTimeMillis();
    System.out.println("ECB --> " + backAgain);
    System.out.println("ECB decryption took an average of " + (end-start)/MAX_RUNS + " msecs");

    System.out.println("==================");

    //CBC Encryption
    start = System.currentTimeMillis();
    for(int i=0; i< MAX_RUNS; i++) {
      byte[] data = TEST_STRING.getBytes();
      encrypted = AES_CBC_CryptoService.encrypt(data, kf.getKey(), kf.getIV(), kf.getSalt());
    }
    end = System.currentTimeMillis();
    System.out.println("CBC --> " + new String(encrypted, "UTF8"));
    System.out.println("CBC encryption took an average of " + (end-start)/MAX_RUNS + " msecs");

    //CBC Decryption
    start = System.currentTimeMillis();
    for(int i=0; i< MAX_RUNS; i++) {
      byte[] decrypted = AES_CBC_CryptoService.decrypt(encrypted, kf.getKey(), kf.getIV(), kf.getSalt());
      backAgain = new String(decrypted, "UTF8");
    }
    end = System.currentTimeMillis();
    System.out.println("CBC --> " + backAgain);
    System.out.println("CBC decryption took an average of " + (end-start)/MAX_RUNS + " msecs");

    System.out.println("==================");

  }
}
