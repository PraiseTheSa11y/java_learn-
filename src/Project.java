import java.util.Scanner;

 class Main {
     public static void main(String[] args) {
         Project p1 = new Project();
         System.out.println("Введите число ");
        int number = p1.getInt();
         p1.evenOrOdd(number);
         p1.positiveOrNegative(number);
         p1.numbersAmount(number);
         p1.sum(number);
         p1.tableX(number);
         p1.printFactorial(number);
     }
 }


public class Project {

    static Scanner sc = new Scanner(System.in);

    /* public static void main(String[] args) {
        Project programm = new Project();
        System.out.println("Введите число: ");
        int number = getInt();
       programm.sum(getInt());
       programm.tableX(getInt());
       programm.factorial(getInt());

    } */

    public static int getInt() {
        return sc.nextInt();
    }

    public void evenOrOdd(int number) {
        if (number % 2 == 0) {
            System.out.println("Число чётное ");
        } else {
            System.out.println("Число нечётное ");
        }
    }

    public void positiveOrNegative(int number) {
        if (number > 0) {
            System.out.println("Число положительное ");
        }
        if (number < 0) {
            System.out.println("Число отрицательное ");
        }
    }

    public void numbersAmount(int number) {
        int count = 0;
        number = Math.abs(number);
        if (number == 0) {
            count = 1;
        } else {
            while (number > 0) {
                number = number / 10;
                count++;
            }
        }
        System.out.println("Количество цифр: " + count);
    }

    public void sum(int number) {
        int sum = 0;
        if(number == 0) {
            System.out.println("Сумма равна 0");
            return;
        }
        if (number >= 1) {
            for (int i = 1; i <= number; i++) {
                sum += i; // sum = sum + i // getInt() = 5 // sum = 0 + 1, sum = 1 + 2, sum = 3 + 3, sum = 6 + 4
                System.out.println("Cумма чисел от 1 до " + number + " = " + sum);
            }
        } else {
            for (int i = 1; i >= number; i--) {
                sum += i;
                System.out.println("Cумма чисел от 1 до " + number + " = " + sum);
            }
        }

    }

    public void tableX(int number) {
        if(number == 0) {
            System.out.println("На ноль уможать нельзя!");
            return;
        }
        for (int i = 1; i <= 10; i++) {
            System.out.println(i + ")" + i + "*" + number + " = " + (i * number));
        }
    }
    public void printFactorial(int number) {
        if(number < 0) {
            System.out.println("Невозможно посчитать факториал");
        } else {
            System.out.println("Факториал числа " + number + " = " + factorial(number));
        }
    }

    public int factorial(int number) {
        int sum = 1;
        if (number == 0) {
            return 1;
        } else if (number > 12 || number < 0) {
            System.out.println("Введите число от 0 до 12");
            return 404;
        } else {
            /* for (int i = 1; i <= number; i++) {
                // System.out.println("ДО " + sum + " " + i);
                // sum = sum * i;
                sum *= i;
               //  System.out.println("ПОСЛЕ " + sum + " " + i);
            } */
            if (number == 1) {
                return 1;
            }
        }
        return number * factorial(number - 1);
    }

}