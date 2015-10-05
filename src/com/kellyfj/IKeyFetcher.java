package com.kellyfj;

public interface IKeyFetcher {
  String getKey();
  byte[] getIV();
  byte[] getSalt();
}

