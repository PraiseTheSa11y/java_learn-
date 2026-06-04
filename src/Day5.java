public class Day5 {
    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 5, 9, 10, 3, 11, 19, 4, 13, 20, 17};
        int[] array2 = new int[]{1, 2, 3, 3, 5, 6};
        int[] grades = new int[] {5, 2, 5, 3, 4, 5, 2};
        System.out.println(isArraySorted(array));
        System.out.println(isArraySorted(array2));
        System.out.println(lettersAndDigitsCounter("Я пожал 100 на 3 раза"));
        System.out.println(longestWord("Что вершит судьбу человечества в этом мире"));
        gradesStats(grades);
    }
    static boolean isArraySorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] <= arr[i + 1]) {
            } else {
                return false;
            }
        }
        return true;
    }
    static String longestWord(String line) {
        int maxIndex = 0;
        int maxLenght = 0;
        char[] chars = new char[line.length()];
        char[] longestWord = new char[line.length()];
        line = line + ' ';
        for (int i = 0; i < line.length(); i++) {
            if (Character.isLetter(line.charAt(i))) {
                chars[maxIndex] = line.charAt(i);
                maxIndex++;
            } else {
                if (maxIndex > maxLenght) {
                    maxLenght = maxIndex;
                    for (int j = 0; j < maxIndex; j++) {
                        longestWord[j] = chars[j];
                    }
                }
                maxIndex = 0;
            }

        }   String max = new String(longestWord, 0, maxLenght);
        return max;
    }
    static String lettersAndDigitsCounter(String line) {
        int letters = 0;
        int digits = 0;
        char[] chars = line.toCharArray();
        for(int i = 0; i < chars.length; i++) {
            if (Character.isLetter(chars[i])) {
                letters++;
            } else if (Character.isDigit(chars[i])) {
                digits++;
            }
        } return "Количество букв: " + letters + " \nКоличество цифр: " + digits;
    }
    static void gradesStats(int[] arr) {
        if(arr.length == 0) {
            System.out.println("Указан пустой массив");
            return;
        }
        double average = sumArray(arr) / arr.length;
        average = Math.round(average * 100.0) / 100.0;
        int max = maxArray(arr);
        int min = minArray(arr);
        int fivesCounter = elementEnterCounter(arr, 5);
        System.out.println("Средний балл: " + average);
        System.out.println("Максимальная оценка: " + max);
        System.out.println("Минимальная оценка: " + min);
        System.out.println("Количество пятёрок: " + fivesCounter);
    }
    static int minArray(int[] arr) {
        if(arr.length == 0) {
            System.out.println("Указан пустой массив");
            return -1;
        }
        int min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if(min > arr[i]) {
                min = arr[i];
            }
        } return min;
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
    static double sumArray(int[] arr) {
        double sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }
    static int elementEnterCounter(int[] arr, int target) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                count++;
            }
        }
        return count;
    }
}
