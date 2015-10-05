package com.kellyfj;

/**
 * Hard-coded keys used until SPC's KMS is in place
 */
public class KeyFetcher implements IKeyFetcher {

  @Override
  public String getKey() {
    return "W1S+/rgUWEURq3bhir1sUqDZ2GkXwiOd0pV4+7ZYY1c=";
  }

  @Override
  public byte[] getIV() {
    byte[] iv = {65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 61, 61};
    return iv;
  }

  @Override
  public byte[] getSalt() {
    byte[] salt = {
        (byte)0x3C, (byte)0x6B, (byte)0xD0, (byte)0x40,
        (byte)0xB0, (byte)0x7A, (byte)0x7, (byte)0x23,
        (byte)0x37, (byte)0x6D, (byte)0xAA, (byte)0xDC,
        (byte)0xC1, (byte)0xC, (byte)0xA2, (byte)0xCE
    };

    return salt;
  }
}
