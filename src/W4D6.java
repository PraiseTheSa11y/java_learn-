import java.util.ArrayList;
import java.util.Scanner;

public class W4D6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LibraryService libraryService = new LibraryService();

        //Книги захардкоженны для тестов
        libraryService.testHardCodeBook();

        libraryService.printMenu(sc);
    }
}

class BookDay6 {
    private String title;
    private String author;
    private int year;
    private double rating;
    private BookStatusDay6 status;

    BookDay6(String title, String author, int year, double rating, BookStatusDay6 status) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.rating = rating;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public double getRating() {
        return rating;
    }

    public BookStatusDay6 getStatus() {
        return status;
    }

    public void setStatus(BookStatusDay6 status) {
        if (status != null) {
            this.status = status;
        }
    }
}


class LibraryService {
    private ArrayList<BookDay6> books = new ArrayList<>();
    // метод для тестов
    public void testHardCodeBook() {
        books.add(new BookDay6("Преступление и наказание", "Фёдор Достоевский", 1866, 9.2, BookStatusDay6.FINISHED));
        books.add(new BookDay6("1984", "Джордж Оруэлл", 1949, 8.7, BookStatusDay6.READING));
        books.add(new BookDay6("Мастер и Маргарита", "Михаил Булгаков", 1967, 9.8, BookStatusDay6.FINISHED));
        books.add(new BookDay6("Дюна", "Фрэнк Герберт", 1965, 8.5, BookStatusDay6.PLANNED));
    }

    public void printMenu(Scanner sc) {
        while (true) {
            System.out.println("== МЕНЮ ==");
            System.out.println("== КАТАЛОГ КНИГ == \n 1.Добавить книгу \n 2.Показать все книги \n 3.Найти книгу \n 4.Изменить статус \n 5.Удалить книгу \n 6.Статистика \n 0.Выход");

            if (sc.hasNextInt()) {
                int userInput = sc.nextInt();

                sc.nextLine();

                switch (userInput) {
                    case 1:
                        addBook(sc);
                        break;
                    case 2:
                        printBooks();
                        break;
                    case 3:
                        BookDay6 book = findByWord(sc);
                        if (book != null) {
                            System.out.println("Найдено: " + book.getTitle());
                        } else {
                            System.out.println("Ничего не найдено");
                        }
                        break;
                    case 4:
                        changeStatusBook(sc);

                        break;
                    case 5:
                        deleteBook(sc);
                        break;
                    case 6:
                        String summary = librarySummary();
                        System.out.println(summary);
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Выберите пункт меню от 0 до 6");
                }
            } else {
                System.out.println("Выберите пункт меню от 0 до 6");
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
            BookDay6 book = books.get(i);
            System.out.println((i + 1) + ". " + book.getTitle()
                    + " — " + book.getAuthor()
                    + " — " + book.getYear() + " г."
                    + " — рейтинг: " + book.getRating()
                    + " — " + book.getStatus().getStatus());
        }
    }

    public void addBook(Scanner sc) {
        System.out.println("Введите название: ");
        String title = sc.nextLine();

        while (title.trim().isEmpty()) {
            System.out.println("Название не может быть пустым. Введите название:");
            title = sc.nextLine();
        }

        System.out.println("Укажите автора: ");
        String author = sc.nextLine();

        while (author.trim().isEmpty()) {
            System.out.println("Имя автора не может быть пустым. Введите автора:");
            author = sc.nextLine();
        }

        int year;
        while (true) {
            System.out.println("Введите год издания: ");

            if (sc.hasNextInt()) {
                year = sc.nextInt();
                sc.nextLine();

                if (year > 0) {
                    break;
                }

                System.out.println("Введите корректный год издания");
            } else {
                System.out.println("Введите число");
                sc.nextLine();
            }
        }

        double rating;
        while (true) {
            System.out.println("Введите рейтинг от 0 до 10: ");

            if (sc.hasNextDouble()) {
                rating = sc.nextDouble();
                sc.nextLine();

                if (rating >= 0 && rating <= 10) {
                    break;
                }

                System.out.println("Рейтинг должен быть от 0 до 10");
            } else {
                System.out.println("Введите число");
                sc.nextLine();
            }
        }

        BookStatusDay6 status = readBookStatus(sc);
        BookDay6 book = new BookDay6(title, author, year, rating, status);
        books.add(book);

        System.out.println("Книга добавлена!");
    }

    public void deleteBook(Scanner sc) {
        if (books.isEmpty()) {
            System.out.println("Каталог пуст");
            return;
        }

        printBooks();

        System.out.println("Выберите книгу для удаления: ");

        if (sc.hasNextInt()) {
            int userNum = sc.nextInt();
            sc.nextLine();
            userNum -= 1;

            if (userNum >= 0 && userNum < books.size()) {
                BookDay6 userBook = books.remove(userNum);
                System.out.println("Книга " + userBook.getTitle() + " удалена успешно");
            } else {
                System.out.println("Введите корректный номер книги");
            }
        } else {
            System.out.println("Введите число");
            sc.nextLine();
        }
    }

    public BookDay6 findByWord(Scanner sc) {
        System.out.println("Введите название или автора: ");
        String targetWord = sc.nextLine();
        if (!targetWord.isBlank()) {
            targetWord = targetWord.toLowerCase();
            String title;
            String author;

            for (BookDay6 book : books) {
                title = book.getTitle().toLowerCase();
                author = book.getAuthor().toLowerCase();
                if (title.contains(targetWord) || author.contains(targetWord)) {
                    return book;
                }
            }
        } else {
            System.out.println("Совпадений не найдено");
            return null;

        }
        return null;
    }

    public BookStatusDay6 readBookStatus(Scanner sc) {
        int choice;

        System.out.println("Введите статус книги: \n 1.Запланирована \n 2.Читаю \n 3.Прочитана");

        while (true) {
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        return BookStatusDay6.PLANNED;
                    case 2:
                        return BookStatusDay6.READING;
                    case 3:
                        return BookStatusDay6.FINISHED;
                    default:
                        System.out.println("Выберите статус от 1 до 3");
                }
            } else {
                System.out.println("Выберите статус от 1 до 3");
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
            sc.nextLine();
            userBook -= 1;
            if (userBook >= 0 && userBook < books.size()) {
                while (true) {
                    System.out.println("Выберите новый статус: \n 1.Запланирована \n 2.Читаю \n 3.Прочитана");
                    if (sc.hasNextInt()) {
                        userStatus = sc.nextInt();
                        sc.nextLine();
                        if (userStatus >= 1 && userStatus <= 3) {
                            BookStatusDay6 newStatus = switch (userStatus) {
                                case 1 -> BookStatusDay6.PLANNED;
                                case 2 -> BookStatusDay6.READING;
                                case 3 -> BookStatusDay6.FINISHED;
                                default -> BookStatusDay6.PLANNED;
                            };
                            books.get(userBook).setStatus(newStatus);
                            System.out.println("Статус успешно изменён");
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
                System.out.println("Введите корректный номер книги");
                sc.nextLine();
            }
        } else {
            System.out.println("Введите корректный номер книги");
            sc.nextLine();
        }
    }

    public int countByStatus(BookStatusDay6 status) {
        int counter = 0;
        for (BookDay6 book : books) {
            if (book.getStatus() == status) {
                counter++;
            }
        } return counter;
    }

    public BookDay6 findBestBook() {
        BookDay6 currentBest;
        if(!books.isEmpty()) {
            currentBest = books.get(0);
            for (int i = 1; i < books.size(); i++) {
                if (currentBest.getRating() < books.get(i).getRating()) {
                    currentBest = books.get(i);
                }
            }
        } else {
            return null;
        }
        return currentBest;
    }

    public double averageFinishedRating() {
        double ratingSum = 0;
        int counter = 0;
        for (BookDay6 book : books) {
            if (book.getStatus() == BookStatusDay6.FINISHED) {
                ratingSum += book.getRating();
                counter++;
            }
        } if(counter == 0) {
            return 0;
        } else {
            ratingSum = ratingSum / counter;
            return ratingSum;
        }
    }

    public String librarySummary() {
        int i = books.size();
        int plan = countByStatus(BookStatusDay6.PLANNED);
        int read = countByStatus(BookStatusDay6.READING);
        int done = countByStatus(BookStatusDay6.FINISHED);
        double average = averageFinishedRating();
        BookDay6 book = findBestBook();
        if (book != null) {
            String summary = ("Количество книг: " + i + "\n" + "Запланировано: " + plan + "\n" + "Читаю: " + read + "\n" + "Прочитано: " + done + "\n" + "Средняя оценка прочитанных: " + average + "\n" + "Лучшая книга: " + book.getTitle());
            return summary;
        } else {
            String summary = ("Количество книг: " + i + "\n" + "Запланировано: " + plan + "\n" + "Читаю: " + read + "\n" + "Прочитано: " + done + "\n" + "Средняя оценка прочитанных: " + average);
            return summary;
        }
    }
}

enum BookStatusDay6 {
    PLANNED("Запланирована"),
    READING("Читаю"),
    FINISHED("Прочитана");

    private final String status;

    BookStatusDay6(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}