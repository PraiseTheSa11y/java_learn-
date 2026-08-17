import java.util.ArrayList;
import java.util.Scanner;

public class W3D6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task1> tasks = new ArrayList<>();
        System.out.println("Введите количество задач: ");
        int amountTasks = sc.nextInt();
        sc.nextLine();
        Task1.inputTasks(tasks, amountTasks, sc);
        Task1.printTasks(tasks);
        Task1.printSummary(tasks);
        Task1.taskContainsWord(tasks, "Зал");
        Task1.removeTask(tasks, 2);
        Task1.printTasks(tasks);
        Task1.sortByPriority(tasks);

    }
}
class Task1 {
    String title;
    int priority;
    boolean isDone;
    Task1(String title, int priority, boolean isDone) {
        this.title = title;
        this.priority = priority;
        this.isDone = isDone;
    }
    static void sortByPriority(ArrayList<Task1> tasks) {
        for(int i = 0; i < tasks.size() - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < tasks.size() - 1 - i; j++) {
                if (tasks.get(j).priority > tasks.get(j + 1).priority) {
                    Task1 tempTask = tasks.get(j);
                    tasks.set(j, tasks.get(j + 1));
                    tasks.set(j + 1, tempTask);
                    swapped = true;
                }
            }
            if(!swapped) break;
            }
        }
    static void removeTask(ArrayList<Task1> tasks, int index) {
        if(index >= 0 && index <= tasks.size() - 1) {
            tasks.remove(index);
        } else {
            System.out.println("Недопустимый индекс задачи");
        }
    }
    static void printSummary(ArrayList<Task1> tasks) {
        System.out.println("\n Количество выполненых задач: " + Task1.countDoneTasks(tasks));
        System.out.println(" Количество невыполненых задач: " + Task1.countNotDoneTasks(tasks));
        Task1 importantTask = Task1.findFirstImportantNotDoneTask(tasks);
        if(importantTask != null) {
            System.out.println(" Невыполненая задача с высоким приоритетом: " + importantTask.title);
        } else {
            System.out.println( " Невыполненных задач с высоким приоритетом нет");
        }
        System.out.println(" Средний приоритет всех задач: " + Task1.averagePriority(tasks));
        System.out.println(" Итог количеству задач: " + Task1.getSummary(tasks));
    }
    static String getSummary(ArrayList<Task1> tasks) {
        if(tasks.isEmpty()) {
            return "Задач нет";
        }
        if(tasks.size() <= 3 ) {
            return "Низкая загруженность";
        } else if(tasks.size() > 3 && tasks.size() <= 6) {
            return "Средняя загруженность";
        } else {
            return "Высокая загруженность";
        }
    }
    static void taskContainsWord(ArrayList<Task1> tasks, String targetWord) {
        System.out.println("Задачи в которых есть слово: " + targetWord);
        for(Task1 task : tasks) {
            if(Task1.containsWord(task, targetWord)) {
                System.out.println("==" + task.title + "==");
            }
        }
    }
    static boolean containsWord(Task1 task, String targetWord) {
        String titleLowerCase = task.title.toLowerCase();
        targetWord = targetWord.toLowerCase();
        if (titleLowerCase.contains(targetWord)) {
            return true;
        } else {
            return false;
        }
    }
    static double averagePriority(ArrayList<Task1> tasks) {
        double average = 0;
        if(tasks.isEmpty()) {
            System.out.println("Список задач пуст");
            return 0;
        }
        for(Task1 task : tasks) {
            average += task.priority;
        }
        average = average / tasks.size();
        return average;
    }
    static Task1 findFirstImportantNotDoneTask(ArrayList<Task1> tasks) {
        for(Task1 task : tasks) {
            if(!task.isDone && task.priority >= 4) {
                return task;
            }
        } return null;
    }
    static int countNotDoneTasks(ArrayList<Task1> tasks) {
        int counter = 0;
        for(Task1 task : tasks) {
            if(!task.isDone) {
                counter++;
            }
        } return counter;
    }
    static int countDoneTasks(ArrayList<Task1> tasks) {
        int counter = 0;
        for(Task1 task : tasks) {
            if(task.isDone) {
                counter++;
            }
        } return counter;
    }
    static void printTasks(ArrayList<Task1> tasks) {
        String isCompleted;
        System.out.println(" == Текущий список задач ==");
        for(Task1 task : tasks) {
            if(task.isDone) {
                isCompleted = "Выполнена";
            } else {
                isCompleted = "Не выполнена";
            }
            System.out.printf("\n Название: %s | Приоритет: %d | Статус: %s ", task.title, task.priority, isCompleted );
        }
    }
    static void inputTasks(ArrayList<Task1> tasks, int amount, Scanner sc ) {
        for(int i = 0; i < amount; i++) {
            System.out.println("\nВведите название: ");
            String title = sc.nextLine();
            System.out.println("Введите приоритет: ");
            int priority;

            while(true) {
                priority = sc.nextInt();
                if (priority >= 1 && priority <= 5) {
                    break;
                } else {
                    System.out.println("Введите число от 1 до 5");
                }
            }
            sc.nextLine();
            System.out.println("Введите статус: ");

            String userInput = sc.nextLine();
            boolean isDone = false;
            if(userInput.equalsIgnoreCase("+")) {
                isDone = true;
            } else if(userInput.equalsIgnoreCase("-")) {
                isDone = false;
            } else {
                System.out.println("Повторите ввод - принимается: + / - ");
            }

            tasks.add(new Task1(title, priority, isDone) );

        }
    }
}

