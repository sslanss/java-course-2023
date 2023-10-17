package edu.hw1;

public final class Task7 {

    private Task7() {
    }

    public static boolean isShiftSizeCorrect(int binaryStringSize, int shift) {
        return binaryStringSize >= shift;
    }

    public static int rotateLeft(int number, int shift) {
        String binaryNumber = Integer.toBinaryString(number);
        if (!isShiftSizeCorrect(binaryNumber.length(), shift)) {
            return -1;
        } else {
            StringBuilder shifted = new StringBuilder(binaryNumber);
            StringBuilder tmpChars = new StringBuilder();
            for (int i = 0; i < shift; i++) {
                tmpChars.append(shifted.charAt(i));
            }
            for (int i = 0; i < shifted.length() - shift; i++) {
                shifted.setCharAt(i, shifted.charAt(i + shift));
            }
            int shiftInc = 0;
            for (int i = shifted.length() - shift; i < shifted.length(); i++) {
                shifted.setCharAt(i, tmpChars.charAt(shiftInc++));
            }
            String res = shifted.toString();
            return Integer.parseInt(res, 2);
        }
    }

    public static int rotateRight(int number, int shift) {
        String binaryNumber = Integer.toBinaryString(number);
        if (!isShiftSizeCorrect(binaryNumber.length(), shift)) {
            return -1;
        } else {
            StringBuilder shifted = new StringBuilder(binaryNumber);
            StringBuilder tmpChars = new StringBuilder();
            for (int i = shifted.length() - shift; i < shifted.length(); i++) {
                tmpChars.append(shifted.charAt(i));
            }
            for (int i = shifted.length() - 1; i >= shift; --i) {
                shifted.setCharAt(i, shifted.charAt(i - shift));
            }
            for (int i = 0; i < shift; i++) {
                shifted.setCharAt(i, tmpChars.charAt(i));
            }
            String res = shifted.toString();
            return Integer.parseInt(res, 2);
        }
    }
}
