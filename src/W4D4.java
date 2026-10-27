import java.util.ArrayList;
import java.util.Scanner;

public class W4D4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu();
        BookManager books = new BookManager();
        while (true) {
            menu.printMenu();
            int choice = menu.readInt(sc);
            if (choice == 0) {
                break;
            }
            switch (choice) {
                case 1:
                    books.addBook(sc);
                    break;
                case 2:
                    books.printBooks();
                    break;
                case 3:
                    books.findByTitle(sc);
                    break;
                case 4:
                    books.changeStatusBook(sc);
                    break;
                case 5:
                    books.deleteBook(sc);
                    break;
                case 6:
                    books.printStats();
                    break;
                default:
                    System.out.println("Выберите пункт меню от 0 до 6");
            }
        }
    }
}

class Menu {
    public void printMenu() {
        System.out.println("== КАТАЛОГ КНИГ == \n 1.Добавить книгу \n 2.Показать все книги \n 3.Найти книгу \n 4.Изменить статус \n 5.Удалить книгу \n 6.Статистика \n 0.Выход");
    }

    public int readInt(Scanner sc) {
        int choice = 0;
        while (true) {
            if (!sc.hasNextInt()) {
                System.out.println("Выберите пункт от 0 до 6");
                sc.nextLine();
                continue;
            }
            choice = sc.nextInt();
            if (choice >= 0 && choice <= 6) {
                return choice;
            } else {
                System.out.println("Выберите пункт от 0 до 6");
            }
        }
    }
}

class Book2 {
    private String title;
    private String author;
    private int pages;
    private BookStatus status;

    Book2(String title, String author, int pages, BookStatus status) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPages() {
        return pages;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        if (status != null) {
            this.status = status;
        }
    }
}

class BookManager {
    private ArrayList<Book2> books = new ArrayList<>();

    public void printStats() {
        int plan = 0;
        int start = 0;
        int finish = 0;
        int drop = 0;
        System.out.println(" == Статистика ==");
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getStatus() == BookStatus.PLANNED) {
                plan++;
            }
            if (books.get(i).getStatus() == BookStatus.STARTED) {
                start++;
            }
            if (books.get(i).getStatus() == BookStatus.FINISHED) {
                finish++;
            }
            if (books.get(i).getStatus() == BookStatus.DROPPED) {
                drop++;
            }
        }
        System.out.println("Запланированно: " + plan);
        System.out.println("Начато: " + start);
        System.out.println("Закончено: " + finish);
        System.out.println("Заброшенно: " + drop);
    }

    public void deleteBook(Scanner sc) {
        int userNum = 0;
        Book2 userBook;
        if (books.isEmpty()) {
            System.out.println("Каталог пуст");
            return;
        }
        printBooks();
        System.out.println("Выберите книгу для удаления: ");
        if (sc.hasNextInt()) {
            userNum = sc.nextInt();
            userNum -= 1;
            if (userNum >= 0 && userNum < books.size()) {
                userBook = books.get(userNum);
                books.remove(userBook);
                System.out.println("Книга " + userBook.getTitle() + " удалена успешно");
            } else {
                System.out.println("Введите корректный номер книги");
            }
        } else {
            System.out.println("Введите число");
            sc.nextLine();
        }
    }

    public void addBook(Scanner sc) {
        sc.nextLine();
        System.out.println("Введите название: ");
        String title = sc.nextLine();
        System.out.println("Укажите автора: ");
        String author = sc.nextLine();
        while (true) {
            System.out.println("Введите количество страниц: ");
            if (sc.hasNextInt()) {
                int pages = sc.nextInt();
                if (pages <= 0) {
                    System.out.println("Введите корректное число страниц");
                    sc.nextLine();
                } else {
                    BookStatus status = readBookStatus(sc);
                    Book2 book = new Book2(title, author, pages, status);
                    books.add(book);
                    System.out.println("Книга добавлена!");
                    return;
                }
            } else {
                System.out.println("Введите число");
                sc.nextLine();
            }
        }
    }

    public void changeStatusBook(Scanner sc) {
        int userBook = 0;
        int userStatus = 0;
        if (books.isEmpty()) {
            System.out.println("Каталог пуст");
            return;
        }
        printBooks();
        System.out.println("Введите номер книги: ");
        if (sc.hasNextInt()) {
            userBook = sc.nextInt();
            userBook -= 1;
            if (userBook >= 0 && userBook < books.size()) {
                while (true) {
                    System.out.println("Выберите новый статус: \n 1.Запланирована \n 2.Начата \n 3.Закончена \n 4.Заброшена");
                    if (sc.hasNextInt()) {
                        userStatus = sc.nextInt();
                        if (userStatus >= 1 && userStatus <= 4) {
                            BookStatus newStatus = switch (userStatus) {
                                case 1 -> BookStatus.PLANNED;
                                case 2 -> BookStatus.STARTED;
                                case 3 -> BookStatus.FINISHED;
                                case 4 -> BookStatus.DROPPED;
                                default -> BookStatus.PLANNED;
                            };
                            books.get(userBook).setStatus(newStatus);
                            return;
                        } else {
                            System.out.println("Выберите корректный статус");
                        }
                    } else {
                        System.out.println("Выберите корректный статус");
                        sc.nextLine();
                    }
                }
            } else {
                System.out.println("Введите число");
                sc.nextLine();
            }
        } else {
            System.out.println("Введите корректный номер книги");
        }
    }

public BookStatus readBookStatus(Scanner sc) {
    BookStatus status;
    int choice = 0;
    System.out.println("Введите статус книги: \n 1.Запланированна \n 2.Начата \n 3.Закончена \n 4.Заброшена");
    while (true) {
        if (sc.hasNextInt()) {
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    status = BookStatus.PLANNED;
                    return status;
                case 2:
                    status = BookStatus.STARTED;
                    return status;
                case 3:
                    status = BookStatus.FINISHED;
                    return status;
                case 4:
                    status = BookStatus.DROPPED;
                    return status;
                default:
                    System.out.println("Выберите статус от 1 до 4");
            }
        } else {
            System.out.println("Выберите статус от 1 до 4");
            sc.nextLine();
        }
    }
}

public void printBooks() {
    if (books.isEmpty()) {
        System.out.println("Каталог пуст");
        return;
    }
    System.out.println("Каталог книг: ");
    for (int i = 0; i < books.size(); i++) {
        System.out.println((books.indexOf(books.get(i)) + 1) + ". " + books.get(i).getTitle() + " — " + books.get(i).getAuthor() + " — " + books.get(i).getPages() + " стр." + " — " + books.get(i).getStatus().getStatus());
    }
}

public void findByTitle(Scanner sc) {
    System.out.println("Введите название или автора: ");
    sc.nextLine();
    String targetWord = sc.nextLine();
    targetWord = targetWord.toLowerCase();
    String title;
    String author;
    boolean found = false;
    for (Book2 book : books) {
        title = book.getTitle().toLowerCase();
        author = book.getAuthor().toLowerCase();
        if (title.contains(targetWord)) {
            System.out.println("Найдено: " + book.getTitle());
            found = true;
        }
        if (author.contains(targetWord)) {
            System.out.println("Найдено: " + book.getTitle());
            found = true;
        }
    }
    if (!found) {
        System.out.println("Совпадений не найдено");
    }
}
}

enum BookStatus {
    PLANNED("Запланирована"),
    STARTED("Начата"),
    FINISHED("Закончена"),
    DROPPED("Заброшена");

    private final String status;

    BookStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
