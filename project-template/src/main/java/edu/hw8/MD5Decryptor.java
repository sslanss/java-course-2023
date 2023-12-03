package edu.hw8;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MD5Decryptor {
    private static final int MAX_PASSWORD_LENGTH = 4;
    private static final String ALL_CHARACTERS =
        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+-=";
    private static final int ALL_CHARACTERS_LENGTH = 62;

    private final Map<String, String> encryptedPasswordBase;

    private final Map<String, String> decryptedPasswordBase;

    public MD5Decryptor(Map<String, String> passwordBase) {
        this.encryptedPasswordBase = passwordBase;
        decryptedPasswordBase = new HashMap<>();
    }

    public MD5Decryptor() {
        this.encryptedPasswordBase = new HashMap<>();
        decryptedPasswordBase = new HashMap<>();
    }

    private boolean hasNextPassword(int currentPasswordLength) {
        return currentPasswordLength <= MAX_PASSWORD_LENGTH;
    }

    private String nextPassword(String password, int index) {
        return password + ALL_CHARACTERS.charAt(index % ALL_CHARACTERS_LENGTH);
    }

    private void decryptBase() {
        for (int i = 1; i <= MAX_PASSWORD_LENGTH; i++) {
            List<String> permutations = decryptPasswordsBySize(i);
            System.out.println("Permutations of size " + i + ": " + permutations);
        }
    }

    //MessageDigest не является потокобезопасным.
    // Следовательно, мы должны использовать новый экземпляр для каждого потока.
    public void decryptPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(password.getBytes(StandardCharsets.UTF_8));
        byte[] digest = messageDigest.digest();
        BigInteger no = new BigInteger(1, digest);
        StringBuilder hashText = new StringBuilder(no.toString(16));
        while (hashText.length() < 32) {
            hashText = new StringBuilder("0" + hashText);
        }
        System.out.println(hashText);
    }
    public List<String> decryptPasswordsBySize(int size) {
        List<String> result = new ArrayList<>();
        int[] indices = new int[size];
        Arrays.fill(indices, 0);
        String nextPassword = generatePassword(indices);

        while (true) {
            int pointer = size - 1;
            while (pointer >= 0 && indices[pointer] == ALL_CHARACTERS_LENGTH - 1) {
                pointer--;
            }
            if (pointer < 0) {
                break;
            }
            indices[pointer]++;
            for (int i = pointer + 1; i < size; i++) {
                indices[i] = 0;
            }
            nextPassword = generatePassword(indices);
        }
        return result;
    }

    private String generatePassword(int[] indices) {
        StringBuilder sb = new StringBuilder();
        for (int index : indices) {
            sb.append(ALL_CHARACTERS.charAt(index));
        }
        return sb.toString();
    }

    public static void main(String []args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MD5Decryptor dec = new MD5Decryptor();
        String str = "qwerty";
        dec.decryptPassword(str);
        System.out.println("d8578edf8458ce06fbc5bb76a58c5ca4");
    }
}
