package com.iet.przychodnia.apigateway.jwt;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSA {

    public static final PublicKey PUBLIC_KEY = generatePublicKey();

    //TODO load public key from properties
    private static PublicKey generatePublicKey() {
        try {
            byte[] privateKey = Base64.getDecoder().decode("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtufAP40DhonfIYH9QJbFWRqXUITrVoxAWNKrtcYJqWjpJZfl7V4Y0G0bRv5CbNTmzJmJDW8iK40xKUbdMMSTEVRKKgXn5imbvjP6zb07HiYfgwRgbUo4MuCP0dumaytSLWOuSvMFoFtzsFMfWIsvkVj31FntPMpnAtVI7zymkuYxib1kBMSDbWJFDCz8P0XWDu1YZYmBoplmZWF7F/MKDszMwRh1+56/asUwoG6/Nk5sxwI3emsdRaFplyZlI+NL0o0Pfs3ZDbJoOC1EDlWv6p3eDm3UolTNbqYNHs3xbChIGIF3EEXzXkibsHle9H1iK6HKVr/nUQ43OLptkl6R2wIDAQAB");
            X509EncodedKeySpec spec = new X509EncodedKeySpec(privateKey);

            return KeyFactory.getInstance("RSA").generatePublic(spec);
        } catch (Exception e) {
            throw new RuntimeException("Couldn't initialize public RSA key", e);
        }
    }
}