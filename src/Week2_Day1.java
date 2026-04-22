import java.util.Arrays;

public class Week2_Day1 {
    public static void main(String[] args) {
        String[] first = {"a", "b", "c"};
        String[] second = {"a", "b", "c"};
        System.out.println(first.length == second.length);
        System.out.println(first.equals(second));
        System.out.println(arraysEqual(first, second)); // Сравнение массива собственным методом
        System.out.println(Arrays.equals(first, second)); // Сравнение массива встроенным методом
        String[] third = {"a", "b", "c"};
        String[] fourth = new String[third.length];
        for (int i = 0; i < third.length; i++) {   // Копирование массива из инициализированного в пустой
            fourth[i] = third[i];
            System.out.println(fourth[i]);
        }
        System.out.println(Arrays.toString(third));
        System.out.println(Arrays.toString(fourth));


        int[] fifth = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("Сумма чисел: " + arraySum(fifth));
        System.out.println("Среднее число: " + arrayMid(fifth));

        minAndMax(fifth);
        arrayReverse(fifth);
        evenCounter(fifth);
        positiveCounter(fifth);
    }

    static boolean arraysEqual(String[] first, String[] second) { // Собственный метод сравнения массивов
        if (first.length != second.length) {
            return false;
        }
        for (int i = 0; i < first.length; i++) {
            if (!first[i].equals(second[i])) {
                return false;
            }
        }
        return true;
    }

    static int arraySum(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = arr[i] + sum;
        }
        return sum;
    }

    static int arrayMid(int[] arr) {
        int mid = 0;
        mid = arraySum(arr) / arr.length;
        return mid;
    }

    static void minAndMax(int[] arr) {
        int min = arrayMid(arr);
        int max = arrayMid(arr);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        System.out.println("Минимальное число: " + min);
        System.out.println("Максимальное число: " + max);
    }

    static void arrayReverse(int[] arr) {
        System.out.print("[");
        for (int i = arr.length - 1; i >= 0; i--) {// Вывод массива в обратном порядке
            if (i > 0) {
                System.out.print(arr[i] + ", ");
            } else {
                System.out.print(arr[i]);
                System.out.print("]");
                System.out.println("");
            }

        }
    }

    static void evenCounter(int[] arr) {
        int even = 0;
        for(int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                even++;
            }
        } System.out.println("Количество чётных чисел: " + even);
    }
    static void positiveCounter(int[] arr) {
        int positive = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                positive++;
            }
        }
        System.out.println("Количество положительных чисел: " + positive);
    }
}
