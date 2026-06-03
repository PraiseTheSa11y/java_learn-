import java.util.Arrays;

public class Day4 {

    public static void main(String[] args) {

        int[] array = new int[]{1, 3, 5, 6, 9, 10};
        int[] arr = new int[]{};

        System.out.println(sumArray(array));
        System.out.println(maxArray(array));
        System.out.println(containsNumber(array, 9));
        System.out.println(wordsCounter("Жестко, пожал "));
        System.out.println(wordCounter2("    Давно,не был в гонках"));
        System.out.println(isAnagram("listen", "silent"));
    }

    static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num);
        }
    }

    static int sumArray(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }

    static int maxArray(int[] arr) {
        if (arr.length == 0) {
            System.out.println("Указан пустой массив");
            return -1;
        }
        int max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        return max;
    }

    static boolean containsNumber(int[] arr, int number) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == number) {
                return true;
            }
        }
        return false;
    }

    static int wordsCounter(String line) {
        line = line.toLowerCase();
        char[] chars = line.toCharArray();
        int gapCount = 0;

        if(chars[0] == ' ' && chars.length == 1) {
            return 0;
        }
        if (chars[0] == ' ') {
            gapCount--;
        }
        for (int i = 0; i < chars.length -1; i++) {
            if (chars[i] == ' ') {
                gapCount++;
            }

            if(chars[i + 1] == ' ' && chars[i] == ' ') {
                gapCount--;
            }
        }
        return gapCount + 1;
    }

    static int wordCounter2(String line) {
        char[] chars = line.toCharArray();
        int count = 0;
        if(chars.length == 0) {
            System.out.println("Введена пустая строка");
            return 0;
        }
        if (Character.isLetter(chars[0])) {
            count++;
        }
        for(int i = 1; i < chars.length; i++) {
            if (Character.isLetter(chars[i]) && !Character.isLetter(chars[i - 1])) {
                count++;
            }
        } return count;
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
