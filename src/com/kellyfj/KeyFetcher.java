package com.kellyfj;

/**
 * Hard-coded keys used until SPC's KMS is in place
 */
public class KeyFetcher implements IKeyFetcher {

  @Override
  public String getKey() {
    return "Z1S+/rgUWEURq3bhir1sUqZZZGkXwiOd0pV4+7ZYY1c=";
  }

  @Override
  public byte[] getIV() {
    byte[] iv = {61, 61, 61, 61, 61, 61, 61, 61, 65, 65, 65, 65, 65, 61, 61, 61};
    return iv;
  }

  @Override
  public byte[] getSalt() {
    byte[] salt = {
        (byte)0x3C, (byte)0x6B, (byte)0x7A, (byte)0x40,
        (byte)0xB0, (byte)0x7A, (byte)0x7, (byte)0x23,
        (byte)0x37, (byte)0x3C, (byte)0xAA, (byte)0xDC,
        (byte)0xC1, (byte)0xC, (byte)0xA2, (byte)0xCD
    };

    return salt;
  }
}
