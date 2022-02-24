package com.epam.medicalsystem.util;

import org.mindrot.jbcrypt.BCrypt;

public class Encryptor {
    private Encryptor() {
    }

    /**
     * Encrypts a given password.
     *
     * @param password String object of the password to be encrypted.
     * @return String object of encrypted password.
     */
    public static String encrypt(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Checks if an unencrypted password equals to an encrypted one.
     *
     * @param password    String object of unencrypted password.
     * @param hashedValue String object of encrypted password.
     * @return boolean value. True if the unencrypted password equals to the encrypted one, false otherwise.
     */
    public static boolean check(String password, String hashedValue) {
        return BCrypt.checkpw(password, hashedValue);
    }
}
