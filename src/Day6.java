import java.util.Scanner;

public class Day6 {
    public static void main(String[] args) {
        StudentHelper helper = new StudentHelper();

        helper.mainHelper();
    }
}

class StudentHelper {
    static Scanner sc = new Scanner(System.in);

    public void mainHelper() {
        System.out.println("Введите имя: ");
        String name = studentName();
        System.out.println("Введите предмет: ");
        String schoolSubject = subject();
        System.out.println("Введите количество оценок: ");
        int[] arr = gradesAmountArray();
        System.out.println("Введите оценки по одной: ");
        gradesInput(arr);
        printGrades(arr);
        gradesStats(arr, name, schoolSubject);
        System.out.println("Обратная связь по итогам среднего балла: " + studentFeedback(sumAverage(arr)));
    }

    public String studentName() {
        return sc.nextLine();
    }

    public String subject() {
        return sc.nextLine();
    }

    public int[] gradesAmountArray() {
        int amount = sc.nextInt();
        return new int[amount];
    }

    public void gradesInput(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int temp = sc.nextInt();
            if(temp < 2) {
                System.out.println("Некорректная оценка");
                return;
            } else if (temp > 5 ) {
                System.out.println("Некорректная оценка");
                return;
            } else {
                arr[i] = temp;
            }
        }
    }

    public void printGrades(int[] arr) {
        System.out.print("Оценки: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }


    static void gradesStats(int[] arr, String name, String schoolSubject) {
    if (arr.length == 0) {
        System.out.println("Указан пустой массив");
        return;
    }
    double average = sumArray(arr) / arr.length;
    average = Math.round(average * 100.0) / 100.0;
    int max = maxArray(arr);
    int min = minArray(arr);
    int fivesCounter = gradeEnterCounter(arr, 5);
    int threesCounter = gradeLowerThanThreeCounter(arr, 3);
    System.out.println("Ученик: " + name);
    System.out.println("Предмет:  " + schoolSubject);
    System.out.println("Средний балл: " + average);
    System.out.println("Средний балл без самой слабой оценки: " + sumAverage(arr));
    System.out.println("Максимальная оценка: " + max);
    System.out.println("Минимальная оценка: " + min);
    System.out.println("Количество пятёрок: " + fivesCounter);
    System.out.println("Количество оценок ниже тройки: " + threesCounter);
}

    static int minArray(int[] arr) {
    if (arr.length == 0) {
        System.out.println("Указан пустой массив");
        return -1;
    }
    int min = arr[0];
    for (int i = 0; i < arr.length; i++) {
        if (min > arr[i]) {
            min = arr[i];
        }
    }
    return min;
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

    static int gradeEnterCounter(int[] arr, int target) {
    int count = 0;
    for (int i = 0; i < arr.length; i++) {
        if (arr[i] == target) {
            count++;
        }
    }
    return count;
}

    static int gradeLowerThanThreeCounter(int[] arr, int target) {
    int count = 0;
    for (int i = 0; i < arr.length; i++) {
        if (arr[i] < target) {
            count++;
        }
    }
    return count;
}

    public String studentFeedback(double average) {
    if (average >= 4.5) {
        return "Отлично";
    } else if (average >= 3.0 && average < 4.5) {
        return "Нормально";
    } else return "Нужно подтянуть";
}

    static double sumAverage(int[] arr) {
    double sum = 0;
    int count = 0;
    for (int i = 0; i < arr.length; i++) {
        if(arr[i] == 2) {
            count++;
            continue;
        }
        sum += arr[i];
    }
    return sum / (arr.length - count);
    }
}
