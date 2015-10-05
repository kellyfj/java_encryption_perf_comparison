Compare Java Encryption performance

Sample output from my Macbook Pro (2.3 GHz Intel Core i7)

```
ECB --> xmnG286w4Lss/eyGJadqK/2OdqaMKc4xkk/dobcOLipPBYOQE97VkajCnz9yT/Xd
ECB encryption took an average of 0.292 msecs
ECB --> The quick brown fox jumped over the lazy dog999
ECB decryption took an average of 0.037 msecs
==================
CBC --> GsZ8jQePZe8EKeLY7TfaON+QDv5GduL2W5SxrzV7yT0+AZugyzYyBEnwwk/CpbKO
CBC encryption took an average of 206.75 msecs
CBC --> The quick brown fox jumped over the lazy dog19
CBC decryption took an average of 174.4 msecs
==================
GCM --> JXaoLXzG46ad0KxNzzGNZvqf4V0p6+dUAhRPsN+ThgwMk+jcqrTzUpfL1CuVn6JUAVsXedkLX9x4v521fX1n
GCM encryption took an average of 0.073 msecs
GCM --> The quick brown fox jumped over the lazy dog999
GCM decryption took an average of 0.034 msecs
==================
```
