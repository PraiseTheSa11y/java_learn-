import java.util.ArrayList;

public class W4D2 {
    public static void main(String[] args){
        Library library = new Library();
        library.addBook(new Book1("Перси Джексон", "Рик Риордан"));
        library.addBook(new Book1("Убить пересмешника", "Харпер Ли"));
        library.addBook(new Book1("Богатый папа, бедный папа", "Робер Кийосаки"));

        library.printBooks();

        library.printInfo();

        library.markAsReadBook("убить пересмешника");

        library.printBooks();
        library.printInfo();

        library.removeBook("перси джексон");
        library.printBooks();


        TaskManager taskManager = new TaskManager();
        taskManager.addTask(new Task2("Сходить в зал", 3));
        taskManager.addTask(new Task2("Закрыть 4 неделю", 5));
        taskManager.addTask(new Task2("Подготовиться к аттестации на работе", 4));

        taskManager.printInfo();



    }
}
class Book1 {
    private String title;
    private String author;
    private boolean isRead = false;

    Book1(String title, String author) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Название книги не может быть пустым");
        } else {
            this.title = title;
        }
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Поле ==Автор== не может быть пустым");
        } else {
            this.author = author;
        }
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isRead() {
        return isRead;
    }

    public void markAsRead() {
            isRead = true;
        }
    }

class Library {
    private ArrayList<Book1> books = new ArrayList<>();

    public void addBook(Book1 book) {
        books.add(book);
    }

    public void removeBook(String title) {
        boolean removed = false;
        for(int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().equalsIgnoreCase(title)) {
                System.out.println("Книга: " + books.get(i).getTitle() + " удалена");
                books.remove(i);
                removed = true;
                break;
            }
        }
        if(!removed) {
            System.out.println("Книга не найдена");
        }
    }
    public void printBooks() {
        String isReaded;
        for(Book1 book : books) {
            System.out.println("Название: " + book.getTitle());
            System.out.println("Автор: " + book.getAuthor());
            if (book.isRead()) {
                isReaded = "Да";
            } else {
                isReaded = "Нет";
            }
            System.out.println("Прочитана: " + isReaded);
        }
    }
    public int countReadBooks() {
        int count = 0;
        for(Book1 book : books) {
            if(book.isRead()) {
                count++;
            }
        }
        return count;
    }
    public void printInfo() {
        System.out.println("Количество книг в библиотеке: " + books.size());
        System.out.println("Количество прочитанных " + countReadBooks());
    }
    public void markAsReadBook(String title) {
        for(Book1 book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.markAsRead();
                return;
            }
        }
        System.out.println("Книга не найдена - проверьте корректность названия");
    }
}
class Task2 {
     private String title;
     private int priority;
     private boolean isDone = false;

     Task2(String title, int priority) {
         if (title == null || title.isBlank()) {
             throw new IllegalArgumentException("Название не может быть пустым");
         } else {
             this.title = title;
         }
         if (priority >= 1 && priority <= 5) {
             this.priority = priority;
         } else {
             throw new IllegalArgumentException("Приоритет должен быть от 1 до 5");
         }
     }
     public String getTitle() {
            return title;
     }
     public int getPriority() {
         return priority;
     }
     public boolean isDone() {return isDone;}
     public void markAsDone() {
         this.isDone = true;
     }
}
class TaskManager {
    private ArrayList<Task2> tasks = new ArrayList<>();

    public void addTask(Task2 task) {
        if(task == null) {
            throw new IllegalArgumentException("Задача не может быть пустой");
        } else {
            tasks.add(task);
        }
    }

    public void removeTask(String title) {
        for(int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTitle().equalsIgnoreCase(title)) {
                tasks.remove(i);
                return;
            }
        }
        System.out.println("Задача не найдена");
    }

    public void markTaskAsDone(String title) {
        for (Task2 task : tasks) {
            if (task.getTitle().equalsIgnoreCase(title)) {
                task.markAsDone();
                return;
            }
        } System.out.println("Задача не найдена");
    }

    public void printTasks() {
        for (Task2 task : tasks) {
            System.out.println("Название: " + task.getTitle() + " | " + "Приоритет: " + task.getPriority() + " | " + "Статус: " + task.isDone());
        }
    }

    public int countDoneTasks() {
        int counter = 0;
        for (Task2 task : tasks) {
            if (task.isDone()) {
                counter++;
            }
        }
        return counter;
    }

    public Task2 findFirstHighPriorityTask() {
        for (Task2 task : tasks) {
            if (!task.isDone() && task.getPriority() >= 4) {
                return task;
            }
        }
        System.out.println("Нет подходящих задач");
            return null;
    }
    public void printInfo() {
        System.out.println("Количество задач: " + tasks.size());
        System.out.println("Выполнено: " + countDoneTasks());
        System.out.println("Не выполнено: " + (tasks.size() - countDoneTasks()));
        System.out.print("Список задач: ");
        printTasks();
        Task2 task = findFirstHighPriorityTask();
        if(task != null) {
            System.out.println("Не выполненая задача с высоким приоритетом: " + task.getTitle());
        } else {
            throw new IllegalArgumentException("Нет подходящих по приоритету невыполненных задач");
        }
    }
}


