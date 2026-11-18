package Week5;
import java.util.ArrayList;
import java.util.Scanner;

public class W5D5 {
    public static void main(String[] args) {
        LibraryService libraryService = new LibraryService();
        Scanner sc = new Scanner(System.in);
        while (true) {
            libraryService.printMenu();
            int choice = libraryService.readInt(sc);
            if (choice == 0) {
                break;
            }
            switch (choice) {
                case 1:
                    libraryService.addBook(sc);
                    break;
                case 2:
                    libraryService.deleteBook(sc);
                    break;
                case 3:
                    libraryService.printBooks();
                    break;
                case 4:
                    libraryService.findBook(sc);
                    break;
                case 5:
                    System.out.println(libraryService.calculateAveragePrice());
                    break;
                case 6:
                    libraryService.showBorrowables();
                    break;
                case 7:
                    try {
                        libraryService.borrowItem(sc);
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 8:
                    try {
                        libraryService.returnItem(sc);
                    } catch (IllegalStateException e ) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Выберите пункт меню от 0 до 8");
            }
        }
    }
}
class LibraryService {

    private ArrayList<LibraryItem> books = new ArrayList<>();

    public void borrowItem(Scanner sc) {
        if (!showBorrowables()) {
            throw new IllegalStateException("Нечего взять с собой");
        }
        System.out.println("Введите номер предмета который хотите взять: ");
        int choice = readInt(sc);
        Borrowable selected = findBorrowableNum(choice);
        if (selected == null) {
            System.out.println("Неверный номер предмета");
            return;
        }
        try {
            selected.borrow();
            System.out.println("Вы успешно взяли предмет");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    public void returnItem(Scanner sc) {
        if (!showBorrowables()) {
            throw new IllegalStateException("Нет доступных предметов");
        }
        System.out.println("Введите номер предмета, который хотите вернуть: ");
        int choice = readInt(sc);
        Borrowable selected = findBorrowableNum(choice);
        if (selected == null) {
            System.out.println("Неверный номер предмета.");
            return;
        }
        try {
            selected.returnItem();
            System.out.println("Предмет успешно возвращен в библиотеку!");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    private Borrowable findBorrowableNum(int target) {
        int current = 1;
        for(LibraryItem book : books)  {
            if(book instanceof Borrowable borrowableItem) {
                if(current == target) {
                    return borrowableItem;
                }
                current++;
            }
        }
        return null;
    }

    public boolean showBorrowables() {
        int i = 1;
        for(LibraryItem book : books) {
            if(book instanceof Borrowable) {
                System.out.println(i + ". " + book.getTitle() + " " + book.getItemType().getRusItemType());
                i++;
            }
        }
        if(i == 1) {
            System.out.println("В каталоге нет подходящих предметов.");
            return false;
        }
        return true;
    }

    public double calculateAveragePrice() {
        if (books.isEmpty()) {
            System.out.println("Каталог пуст, среднюю цену рассчитать невозможно.");
            return 0;
        }
        double average = 0;
        for(LibraryItem libraryItem : books) {
            average += libraryItem.getPrice();
        }
        average = average / books.size();
        return average;
    }

    public void addBook(Scanner sc) {
        try {
            System.out.println("Введите название: ");
            String userTitle = sc.nextLine();
            System.out.println("Введите год выпуска: ");
            int userYear = readInt(sc);
            System.out.println("Укажите цену: ");
            int userPrice = readInt(sc);
            System.out.println("Укажите тип: ");
            int i = 1;
            for (ItemType itemType : ItemType.values()) {
                System.out.println(i + ": " + itemType.getRusItemType());
                i++;
            }
            int userItemInt = readInt(sc);
            ItemType userItemType = ItemType.BOOK;
            if (userItemInt >= 1 && userItemInt <= 3) {
                if (userItemInt == 1) {
                    userItemType = ItemType.BOOK;
                } else if (userItemInt == 2) {
                    userItemType = ItemType.MAGAZINE;
                } else {
                    userItemType = ItemType.AUDIOBOOK;
                }
            } else {
                System.out.println("Неверный тип! По умолчанию выбрана Книга.");
            }
            LibraryItem userItem = null;
            switch (userItemType) {
                case BOOK:
                    userItem = new Book(userTitle, userYear, userPrice, userItemType);
                    break;
                case MAGAZINE:
                    userItem = new Magazine(userTitle, userYear, userPrice, userItemType, false);
                    break;
                case AUDIOBOOK:
                    System.out.println("Для аудиокниги надо дополнительно указать длительность в часах: ");
                    int duration = readInt(sc);
                    userItem = new AudioBook(userTitle, userYear, userPrice, duration, userItemType);
                    break;
            }
            if (userItem != null) {
                books.add(userItem);
                System.out.println("Успешно добавлено в коллекцию.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
            System.out.println("Попробуйте добавить заново");
        }

    }

    public void deleteBook(Scanner sc) {
        int userNum = 0;
        LibraryItem userBook;
        if (books.isEmpty()) {
            System.out.println("Каталог пуст");
            return;
        }
        printBooks();
        System.out.println("Выберите книгу для удаления: ");
        userNum = readInt(sc);
        userNum -= 1;
        if (userNum >= 0 && userNum < books.size()) {
            userBook = books.get(userNum);
            books.remove(userBook);
            System.out.println("Книга " + userBook.getTitle() + " удалена успешно");
        } else {
            System.out.println("Введите корректный номер книги");
        }
    }

    public void printBooks() {
        if (books.isEmpty()) {
            System.out.println("Библиотека пуста");
            return;
        }
        System.out.println();
        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ". " + books.get(i).getTitle() + ", " + books.get(i).getItemType().getRusItemType());
        }
    }

    public void printMenu() {
        System.out.println(
                "========= \n" +
                "1. Добавить книгу \n" +
                "2. Удалить книгу \n" +
                "3. Посмотреть все книги \n" +
                "4. Найти книгу \n" +
                "5. Средняя цена коллекции  \n" +
                "6. Показать список предметов которые можно взять с собой \n" +
                "7. Взять книгу с собой \n" +
                "8. Вернуть книгу \n" +
                "0. Выход \n" +
                "===========");

    }

    public int readInt(Scanner sc) {
        while(true) {
            try {
                String userInput = sc.nextLine();
                int price = Integer.parseInt(userInput);
                return price;
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод! Введите целое число.");
            }
        }
    }

    public LibraryItem findBook(Scanner sc) {
        try {
            System.out.println("Введите слово для поиска: ");
            String targetWord = sc.nextLine();
            if (targetWord.isBlank()) {
                throw new IllegalArgumentException();
            }
            targetWord = targetWord.toLowerCase();
            String title;

            for (LibraryItem book : books) {
                title = book.getTitle().toLowerCase();
                if (title.contains(targetWord)) {
                    System.out.println("Найдено: " + book.getTitle());

                    return book;
                }
            }
            return null;
        } catch (IllegalArgumentException e) {
            System.out.println("Передана пустая строка или строка из пробелов");
        }
        return null;
    }
}
abstract class LibraryItem {

    private String title;
    private int year;
    private int price;
    private ItemType itemType;

    LibraryItem(String title, int year, int price, ItemType itemType) {
        if(title == null || title.isBlank()) {
            throw new IllegalArgumentException("Неверно указано название");
        }
        this.title = title;
        if(year <= 0 || year > 2026) {
            throw new IllegalArgumentException("Год не может быть отрицательным или больше 2026");
        }
        this.year = year;
        if(price < 0) {
            throw new IllegalArgumentException("Неверно указана стоимость");
        }
        this.price = price;
        if(itemType == null) {
            throw new IllegalArgumentException("Неверно указан тип");
        }
        this.itemType = itemType;
    }
    public String getTitle() {
        return title;
    }
    public int getYear() {
        return year;
    }
    public int getPrice() {
        return price;
    }
    public ItemType getItemType() {
        return itemType;
    }
}
class Book extends LibraryItem implements Borrowable {

    private boolean isBorrowed = false;

    Book(String title, int year, int price, ItemType itemType) {
        super(title, year, price, itemType);
    }
    @Override
    public void borrow() {
        if(isBorrowed) {
            throw new IllegalStateException("Этa книга уже выдана");
        }
        this.isBorrowed = true;
    }
    @Override
    public void returnItem() {
        if(!isBorrowed) {
            throw new IllegalStateException("Книга и так находится в библиотеке");
        }
        isBorrowed = false;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }
}
class Magazine extends LibraryItem implements Borrowable {
    private boolean isBorrowed = false;
    Magazine(String title, int year, int price, ItemType itemType, boolean isBorrowed) {
        super(title, year, price, itemType);
        this.isBorrowed = isBorrowed;
    }
    @Override
    public void borrow() {
        if(isBorrowed) {
            throw new IllegalStateException("Этот журнал уже выдан");
        }
        this.isBorrowed = true;
    }
    @Override
    public void returnItem() {
        if (!isBorrowed) {
            throw new IllegalStateException("Этот журнал уже в библиотеке");
        }
        isBorrowed = false;
    }

    public void setBorrowed(boolean borrowed) {
        this.isBorrowed = borrowed;
    }
}
class AudioBook extends LibraryItem {
    private int duration;
    AudioBook(String title, int year, int price, int duration, ItemType itemType) {
        super(title, year, price, itemType);
        if(duration <= 0) {
            throw new IllegalArgumentException("Неправильная продолжительность аудио");
        }
        this.duration = duration;
    }

}
enum ItemType {

        BOOK("Книга"),
        MAGAZINE("Журнал"),
        AUDIOBOOK("Аудиокнига");

    private final String itemType;

        ItemType(String rusItemType) {
            this.itemType = rusItemType;
        }
        public String getRusItemType() {
            return itemType;
        }
}
