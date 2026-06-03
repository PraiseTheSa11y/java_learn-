import java.util.Arrays;
import java.util.Scanner;

public class Day3 {
    public static void main(String[] args) {
        String s = new String("java");
        String s1 = new String("Java");
        String s2 = new String("");
        String s3 = new String("    ");

        char charTest = s1.charAt(1);
        System.out.println(charTest);

        boolean res = s.equals(s1);
        System.out.println(res);
        System.out.println(s2.isBlank());
        System.out.println(s3.isEmpty());

        Scanner sc = new Scanner(System.in);
        String user = sc.nextLine();
        System.out.println(user.length());
        System.out.println(user.charAt(0) + " " + user.charAt(user.length() - 1));
        System.out.println(spaceCount(user));
        System.out.println(aCounter(user));
        reversedString(user);
        isPalidrome(user);
        System.out.println(isPalidrome3(user));

        String user2 = sc.nextLine();
        System.out.println(isAnagram(user, user2));


    }

    static int spaceCount(String line) {
        int count = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ' ') {
                count++;
            }
        }
        return count;
    }

    static int aCounter(String line) {
        int count = 0;
        line = line.toLowerCase();
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == 'a') {
                count++;
            }
        }
        return count;
    }

    static void reversedString(String line) {
        String reversed = "";
        for (int i = line.length() - 1; i >= 0; i--) {
            reversed += line.charAt(i);
        }
        System.out.println(reversed);
    }

    static String isPalidrome(String line) {
        int left = 0;
        int right = line.length() - 1;
        while (left < right) {
            if (line.charAt(left) == line.charAt(right)) {
                left++;
                right--;
            } else {
                System.out.println("Не палидром");
                return "Не палидром";
            }
        }
        System.out.println("Успех!");
        return "Палидром";
    }

    /* static String isPalidrome2(String line) {
        int left = 0;
        int right = line.length() - 1;
        while (left < right) {
            if (Character.isLetter(line.charAt(left)) && Character.isLetter(line.charAt(right))) {
                if (line.charAt(left) == line.charAt(right)) {
                    left++;
                    right--;
                } else {
                    System.out.println("Не палидром");
                    return "Не палидром";
                }
            } else {
                left++;
                right--;
            }
        }
        System.out.println("Успех!");
        return "Палидром";
    }

     */


    static boolean isPalidrome3(String line) {
        int left = 0;
        int right = line.length() - 1;
        line = line.toLowerCase();
        while (left < right) {
            if (!Character.isLetter(line.charAt(left))) {
                left++;
            } else if (!Character.isLetter(line.charAt(right))) {
                right--;
            } else if (line.charAt(left) == line.charAt(right)) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }

    static boolean isAnagram(String line1, String line2) {
        line1 = line1.toLowerCase();
        line2 = line2.toLowerCase();

        char[] chars1 = line1.toCharArray();
        char[] chars2 = line2.toCharArray();

        if (chars1.length != chars2.length) {
            return false;
        }
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] != chars2[i]) {
                return false;
            }
        }
        return true;
    }
}



