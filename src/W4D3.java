import java.util.ArrayList;

public class W4D3 {
    public static void main(String[] args) {
        Task3 task1 = new Task3("Сходить в зал");
        Task3 task2 = new Task3("Закрыть 4 неделю");
        Task3 task3 = new Task3("Сделать ужин");
        Task3 task4 = new Task3("Поиграть в апекс");
        Task3 task5 = new Task3("Подготовиться к аттестации");

        TaskManager3 taskManager = new TaskManager3();
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);
        taskManager.addTask(task4);
        taskManager.addTask(task5);

        task1.start();
        task2.start();
        task3.start();
        task3.done();
        task4.start();
        task4.done();

        task1.printStatus();
        task2.printStatus();
        task3.printStatus();
        task4.printStatus();
        task5.printStatus();


        System.out.println("Количество не начатых задач: " + taskManager.countStatus(TaskStatus.TODO));
        System.out.println("Количество начатых задач: " + taskManager.countStatus(TaskStatus.IN_PROGRESS));
        System.out.println("Количество выполненных задач: " + taskManager.countStatus(TaskStatus.DONE));

        task1.describeStatus();
        task2.describeStatus();
        task3.describeStatus();

    }
}
enum TaskStatus {
    TODO("Не начата"),
    IN_PROGRESS("В процессе"),
    DONE("Выполнена");

    private final String status;

    TaskStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
}
class Task3 {
    private String title;
    private TaskStatus status = TaskStatus.TODO;

    Task3(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Название не может быть пустым");
        } else {
            this.title = title;
        }
    }
    public void start() {
        if (status == TaskStatus.TODO) {
            status = TaskStatus.IN_PROGRESS;
        } else {
            throw new IllegalStateException("Задача выполнена или уже в процессе");
        }
    }
    public void done() {
        if (status == TaskStatus.IN_PROGRESS) {
            status = TaskStatus.DONE;
        } else {
            throw new IllegalStateException("Задача не начата или уже выполнена");
        }
    }
    public void printStatus() {
        System.out.println(title + " : " + status.getStatus());
    }
    public String getTitle() {
        return title;
    }
    public TaskStatus getStatus() {
        return status;
    }
    public void describeStatus() {
        switch (status) {
            case TODO:
                System.out.println(title + ": задача не начата");
                break;

            case IN_PROGRESS:
                System.out.println(title + ": задача в процессе");
                break;
            case DONE:
                System.out.println(title + ": задача выполнена");
                break;
        }
    }
}
class TaskManager3 {
    private ArrayList<Task3> tasks = new ArrayList<>();

    public void addTask(Task3 task) {
        tasks.add(task);
    }
    public int countStatus(TaskStatus status) {
        int counter = 0;
        for (Task3 task : tasks) {
            if (task.getStatus() == status) {
                counter++;
            }
        }
        return counter;
    }
}
enum OrderStatus {
    NEW,
    PAID,
    SHIPPED,
    DELIVERED,
    CANCELLED
}
class Order {
    private OrderStatus status = OrderStatus.NEW;

    public void pay() {
        if (status == OrderStatus.NEW) {
            status = OrderStatus.PAID;
        } else {
            throw new IllegalStateException("Заказ уже оплачен");
        }
    }
    public void ship() {
        if(status == OrderStatus.PAID) {
            status = OrderStatus.SHIPPED;
        } else {
            throw new IllegalStateException("Заказ не оплачен или уже доставлен");
        }
    }
    public void deliver() {
        if (status == OrderStatus.SHIPPED) {
            status = OrderStatus.DELIVERED;
        } else {
            throw new IllegalStateException("Заказ не оплачен или уже доставлен");
        }
    }
    public void cancel() {
        if (status == OrderStatus.NEW || status == OrderStatus.PAID) {
            status = OrderStatus.CANCELLED;
        } else {
            throw new IllegalStateException("Заказ уже отправляется к вам");
        }
    }
    public OrderStatus getStatus() {
        return status;
    }
}
