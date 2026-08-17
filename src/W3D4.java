import java.util.ArrayList;

public class W3D4 {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Сходить в зал", 3, true));
        tasks.add(new Task("Закрыть 3 неделю", 5, false));
        tasks.add(new Task("Подготовиться к аттестации на работе", 4, false));
        tasks.add(new Task("Приготовить ужин", 2, false));
        Task.printTasks(tasks);
        System.out.println("Количество выполненых задач: " + Task.countDoneTasks(tasks));
        System.out.println(Task.findFirstImportantUndone(tasks).title);
        System.out.println(Task.averagePriority(tasks));
        Task.printTaskContainsWord(tasks, "зал");
    }
}
class Task {
    String title;
    int priority;
    boolean isDone;
        Task(String title, int priority, boolean isDone) {
            this.title = title;
            this.priority = priority;
            this.isDone = isDone;
        }
        static void printTaskContainsWord(ArrayList<Task> tasks, String targetWord) {
            if(containsWord(tasks, targetWord)) {
                System.out.printf("Задачи содержат слово: %s", targetWord );
            } else {
                System.out.printf("Задачи не содержат слово: %s", targetWord );
            }
    }
        static boolean containsWord(ArrayList<Task> tasks, String targetWord) {
            for (Task task : tasks) {
                if (task.title.contains(targetWord)) {
                    return true;
                }
            } return false;
        }
        static double averagePriority(ArrayList<Task> tasks) {
            double average = 0;
            if(tasks.isEmpty()) {
                return 0;
            }
            for(Task task : tasks) {
                average += task.priority;
            }
            average = average / tasks.size();
            return average;
        }
        static Task findFirstImportantUndone(ArrayList<Task> tasks) {
            for(Task task : tasks) {
                if(!task.isDone && task.priority >= 4) {
                    return task;
                }
            } return null;
        }
        static int countDoneTasks(ArrayList<Task> tasks) {
            int counter = 0;
            for(Task task : tasks) {
                if (task.isDone) {
                    counter++;
                }
            }
            return counter;
        }
        static void printTasks(ArrayList<Task> tasks) {
            String taskDone;
            for(Task task : tasks) {
                if(task.isDone) { taskDone = "Выполнена"; }
                else { taskDone = "Не выполнена"; }
                System.out.printf("%s | %d | %s \n", task.title, task.priority, taskDone);
            }
        }
}
