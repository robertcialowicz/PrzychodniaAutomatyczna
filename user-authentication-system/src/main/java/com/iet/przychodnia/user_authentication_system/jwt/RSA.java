package com.iet.przychodnia.user_authentication_system.jwt;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSA {

    public static final PublicKey PUBLIC_KEY = generatePublicKey();
    public static final PrivateKey PRIVATE_KEY = generatePrivateKey();

    //TODO load key pair from properties
    private static PublicKey generatePublicKey() {
        try {
            byte[] privateKey = Base64.getDecoder().decode("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtufAP40DhonfIYH9QJbFWRqXUITrVoxAWNKrtcYJqWjpJZfl7V4Y0G0bRv5CbNTmzJmJDW8iK40xKUbdMMSTEVRKKgXn5imbvjP6zb07HiYfgwRgbUo4MuCP0dumaytSLWOuSvMFoFtzsFMfWIsvkVj31FntPMpnAtVI7zymkuYxib1kBMSDbWJFDCz8P0XWDu1YZYmBoplmZWF7F/MKDszMwRh1+56/asUwoG6/Nk5sxwI3emsdRaFplyZlI+NL0o0Pfs3ZDbJoOC1EDlWv6p3eDm3UolTNbqYNHs3xbChIGIF3EEXzXkibsHle9H1iK6HKVr/nUQ43OLptkl6R2wIDAQAB");
            X509EncodedKeySpec spec = new X509EncodedKeySpec(privateKey);

            return KeyFactory.getInstance("RSA").generatePublic(spec);
        } catch (Exception e) {
            throw new RuntimeException("Couldn't initialize public RSA key", e);
        }
    }

    private static PrivateKey generatePrivateKey() {
        try {
            byte[] privateKey = Base64.getDecoder().decode("MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC258A/jQOGid8hgf1AlsVZGpdQhOtWjEBY0qu1xgmpaOkll+XtXhjQbRtG/kJs1ObMmYkNbyIrjTEpRt0wxJMRVEoqBefmKZu+M/rNvTseJh+DBGBtSjgy4I/R26ZrK1ItY65K8wWgW3OwUx9Yiy+RWPfUWe08ymcC1UjvPKaS5jGJvWQExINtYkUMLPw/RdYO7VhliYGimWZlYXsX8woOzMzBGHX7nr9qxTCgbr82TmzHAjd6ax1FoWmXJmUj40vSjQ9+zdkNsmg4LUQOVa/qnd4ObdSiVM1upg0ezfFsKEgYgXcQRfNeSJuweV70fWIrocpWv+dRDjc4um2SXpHbAgMBAAECggEBALIfKWL6YfeQpwLLw6U170SiF7/rYgBsH3Ig3+Xq0ZfwujTJI7qxC3tW9dyCWpAAGjAgSY8SrnU5UCoBgAExPoP+0Sbxj2/fD3QAzPu1kDABMmj2Ry3HvMkXVhgqT3xIuQPI8oC9ALq0tQp/OKSTD6T9voRHezjbZ8wjh56sMxAUhHLhEsHeYQTUszxEa4vzFa2X/LKdRlyWdD4AnVX61PDlb2gvFUXiA3EcildcvAo1L0Qp3ZaFueMBkvXpxGFfzcgQUGaCHeJlZ2fPw8tyVIwapxecC9ujOqTFnoGJ4wDjinOrJCqsSeAl9QNpsgDpf14TGe0e4NGcLAUJSyGq+jkCgYEA6YqaDGuTTroH1phE1XjXJs73+mpjo2hdBehiZy+vqMZsYjR4CcqVmSRkq3uRH2Eii5uuJjtaT/TjY4DjQpc2Rvu/huaWcQ971lFrvfS0DbOXfTeqjO0RUVi2STs4Y2NcVwI1Omk6AMKMEMmKa6XlHOa5X+hzvv3AbkOr01HXNQ0CgYEAyH6SiP/PvJuNc8mjJ0RapYpPNuUAtqJw1rONRg9CJRjsg7NNvHIVHggXxOJ6UfIN7D4TYae5z9HD6H7DrFuq5oH504Kv02A+PWxCr8ZHxe2PmdsFpEsbDUBL83bFCjwYqiFJK0oMMrxnTFXnzQz/2ApxvliWEv4RcpXmjyMx+IcCgYEAgezl/vrqHOZJ7g0QXmK375M9Skdd3070UZETDazt4MtjCM9/6JKX33bruqsn9XkoqtqVbBehCtKGnpjZ1fYh8COmTfqCv6uJjZYGhstYUDcVlEgfzf8ln0fnQZ58pG5mtNE5o6Fe74VMPxqgs3q7l/SUNNMxcZPiNnXXZd0mCRECgYEAgvnUxnS/A8pmjNaTo+pOgUgqWYUegzD5H/kp+DOtlUiD8cFnrgyk6PqGdJMzL2c+Wy9uP+RvSWkSX0a4k1dK/My6JMeCD0cXQc8AfY29g3qezfa758k8wdpYA5N6QyZhQnr+Lq5v65EStlaEWHr+g+bGyX9bgsrBISb1+FoYoe8CgYBJ/u1j46VXlJtACmZQ3aY1s0iIo2lNVIi/omIj28+wuUYudZw9DdF2T++Y57bo6Smv9scJ0/QJRp8t1ek5OWKCq7csm53lNYleLTCJsY/EkdNnvpKiFDYsy/zOJtMr4y466P4fkvUrckgwRQ4gvRbf5NPSk5E4psiu1KIrvudrLw==");
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKey);

            return KeyFactory.getInstance("RSA").generatePrivate(spec);
        } catch (Exception e) {
            throw new RuntimeException("Couldn't initialize private RSA key", e);
        }
    }
}