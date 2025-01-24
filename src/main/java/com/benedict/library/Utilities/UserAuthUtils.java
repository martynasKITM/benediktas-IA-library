package com.benedict.library.Utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility class for handling user authentication operations such as password verification
 * and hashing. This class provides methods for securely storing and verifying passwords
 * using the SHA-256 algorithm, and comparing password fields.
 */
public class UserAuthUtils {

    /**
     * Verifies if the provided password matches the stored password hash.
     *
     * @param password the plain text password to verify
     * @param storedHash the stored hashed password to compare against
     * @return true if the provided password matches the stored hash, false otherwise
     */
    public static boolean verifyPassword(String password, String storedHash) {
        String passwordHash = hashPassword(password);
        return passwordHash.equals(storedHash);
    }

    /**
     * Hashes the provided password using the SHA-256 algorithm.
     *
     * @param password the plain text password to hash
     * @return the hashed password as a hexadecimal string
     * @throws RuntimeException if the SHA-256 algorithm is not available
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            return bytesToHex(hash);  // Convert bytes to hex for storage
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Password hashing error", e);
        }
    }

    /**
     * Converts a byte array into a hexadecimal string representation.
     *
     * @param bytes the byte array to convert
     * @return the string representation of the byte array in hexadecimal format
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    /**
     * Compares two password strings to check if they are identical.
     *
     * @param password1 the first password to compare
     * @param password2 the second password to compare
     * @return true if both passwords match, false otherwise
     */
    public static boolean doPasswordsMatch(String password1, String password2) {
        return password1 != null && password1.equals(password2);
    }
}
