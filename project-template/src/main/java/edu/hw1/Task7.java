package edu.hw1;

public final class Task7 {

    private Task7() {
    }
        public static int rotateLeft(int n, int shift) {
            String nInBinary = Integer.toBinaryString(n);

            String resultInBinary = "";
            for (int i = 0; i < shift; ++i) {
                resultInBinary = shiftToLeft(nInBinary);
            }

            return Integer.parseInt(resultInBinary, 2);
        }

        public static int rotateRight(int n, int shift) {
            String nInBinary = Integer.toBinaryString(n);

            String resultInBinary = "";
            for (int i = 0; i < shift; ++i) {
                resultInBinary = shiftToRight(nInBinary);
            }

            return Integer.parseInt(resultInBinary, 2);
        }

        private static String shiftToLeft(String str) {
            StringBuilder shifted = new StringBuilder(str);

            char tmp = shifted.charAt(0);
            for (int i = 0; i < shifted.length() - 1; ++i) {
                shifted.setCharAt(i, shifted.charAt(i + 1));
            }
            shifted.setCharAt(shifted.length() - 1, tmp);

            return shifted.toString();
        }

        private static String shiftToRight(String str) {
            StringBuilder shifted = new StringBuilder(str);

            char tmp = shifted.charAt(shifted.length() - 1);
            for (int i = shifted.length() - 1; i > 0; --i) {
                shifted.setCharAt(i, shifted.charAt(i - 1));
            }
            shifted.setCharAt(0, tmp);

            return shifted.toString();
        }
    }
