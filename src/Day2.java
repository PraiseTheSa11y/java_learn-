public class Day2 {

    public static void main(String[] args) {
        int[] arr = {-1, 0, 6, -3, 4, 7, -5, 9, 13, 0, -5, -19, 20, 0, -0};
        int[] array1 = {1, 2, 4, 5, 6, 17, 8, 9, 11, 14, 15, 17, 17,};
        binaryXFinder(array1, 14);
        xFinder(arr, 7);
        positiveCounter(arr);
        oddAndEvenSum(array1);
        oddAndEvenSum(arr);
        firstMaxIndex(arr);
        firstMaxIndex(array1);
        elementEnterCounter(arr, 0);
        elementEnterCounter(array1, 17);
    }

    static void elementEnterCounter(int[] arr, int target) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                count++;
            }
        }
        System.out.println("Количество чисел: " + target + " в массиве составляет: " + count);
    }
    static void positiveCounter(int[] arr) {
        int positive = 0;
        int negative = 0;
        int zero = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                positive++;
            }
            if (arr[i] < 0) {
                negative++;
            }
            if (arr[i] == 0) {
                zero++;
            }
        }
        System.out.println("Количество положительных чисел: " + positive);
        System.out.println("Количество отрицательных чисел: " + negative);
        System.out.println("Количество нулей: " + zero);
    }

    static int binaryXFinder(int[] arr, int point) {  //поиск X бинарным поиском
        int left = 0;
        int right = arr.length - 1;
        for (int i = 0; i < arr.length; i++) {
            int mid = (left + right) / 2;
            if (arr[mid] == point) {
                System.out.println("Загаданное число = " + arr[mid] + " А его индекс = " + mid);
                return mid;
            }
            if (arr[mid] > point) {
                right = mid - 1;

            }
            if (arr[mid] < point) {
                left = mid + 1;
            }
        }
        return -1;
    }

    static int xFinder(int[] arr, int x) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == x) {
                System.out.println("Индекс искомого числа: " + i);
                return i;
            }
        }
        return -1;
    }

    static void oddAndEvenSum(int[] arr) {
        int oddSum = 0;
        int evenSum = 0;
        int diff = 0;
        diff = Math.abs(diff);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                evenSum = evenSum + arr[i];
            }
            if (arr[i] % 2 == 1) {
                oddSum = oddSum + arr[i];
            }
        }
        diff = evenSum - oddSum;
        System.out.println("Сумма чётных: " + evenSum);
        System.out.println("Сумма нечётных: " + oddSum);
        System.out.println("Разница: " + Math.abs(diff));
    }

    static void firstMaxIndex(int[] arr) {
        int firstMax = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[firstMax] < arr[i]) {
                firstMax = i;
            }
        }
        System.out.println("Индекс первого максимума: " + firstMax + " А его значение: " + arr[firstMax]);
    }
}


/*
    static void quickSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int pivot = arr[high];
        int i = low;
        for (int j = 0; j < high; j++) {
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        quickSort(arr, low, i - 1);
        quickSort(arr, i + 1, high);
        System.out.println(arr);
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
*/
