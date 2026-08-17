import java.util.ArrayList;

public class W3D2 {
    public static void main(String[] args) {

        ArrayList<Integer> nums = new ArrayList<>();

        ArrayList<String> names = new ArrayList<>();

        ArrayList<Books> books = new ArrayList<>();

        books.add(new Books("Перси Джексон", "Рик Риордан", 800));
        books.add(new Books("Убить пересмешника", "Харпер Ли", 450));
        books.add(new Books("Гроздья гнева", "Джон Стейнбек", 600));
        books.add(new Books("Финансовый менеджмент", "Юджин Бригхем", 550));

        System.out.println(booksMoreThanPages(books, 500));
        System.out.println(findFirstBook(books, 700).title);
        System.out.println(averagePages(books));
        printBigBooks(books, 500);
        markLongBooksAsRead(books, 500);
        for(Books book : books) {
            book.printInfo();
        }

        names.add("Леха");
        names.add("Кирюха");
        names.add("Андрюха");
        names.add("Глебастер");
        names.add("Илюха");

        System.out.println(findFirstLongerThanTarget(names, 5));

        nums.add(5);
        nums.add(10);
        nums.add(15);
        nums.add(20);
        nums.add(25);

        System.out.println(nums.get(0));
        nums.set(2, 100);
        System.out.println(nums.get(2));
        System.out.println(nums.size());
        System.out.println(nums);


        System.out.println(arraySummary(nums));

        System.out.println(maxArrList(nums));

        System.out.println(greaterThanCounter(nums, 12));

    }

    static int arraySummary(ArrayList<Integer> nums) {

        int sum = 0;
        for (int i = 0; i < nums.size(); i++) {
            sum += nums.get(i);
        }
        return sum;
    }

    static int maxArrList(ArrayList<Integer> nums) {
        int currentMax = nums.get(0);
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) > currentMax) {
                currentMax = nums.get(i);
            }
        }
        return currentMax;
    }

    static int greaterThanCounter(ArrayList<Integer> nums, int target) {
        int counter = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) > target) {
                counter++;
            }
        }
        return counter;
    }

    static String findFirstLongerThanTarget(ArrayList<String> names, int target) {
        for (int i = 0; i < names.size(); i++) {
            int wordLength = names.get(i).length();
            if (wordLength >= target) {
                return names.get(i);
            }
        }
        return "Не найдено";
    }

    static int booksMoreThanPages(ArrayList<Books> books, int target) {
        int counter = 0;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).pages > target) {
                counter++;
            }

        }
        return counter;
    }

    static Books findFirstBook(ArrayList<Books> books, int target) {
        for(int i = 0; i < books.size(); i++) {
            if (books.get(i).pages > target) {
                return books.get(i);
            }
        } return null;
    }
    static double averagePages(ArrayList<Books> books) {
        double average = 0;
        if(books.isEmpty()) {
            return 0;
        }
        for (int i = 0; i < books.size(); i++) {
            average += books.get(i).pages;
        }
        average = average / books.size();
        return average;
    }
    static void printBigBooks(ArrayList<Books> books, int target) {
        for (Books book : books) {
            if (book.pages >= target) {
                System.out.println(book.title);
            }
        }
    }
    static void markLongBooksAsRead(ArrayList<Books> books, int target) {
        for (Books book : books) {
            if (book.pages >= target) {
                book.isRead = true;
            }
        }
    }

}
    class Books {
        String title;
        String author;
        int pages;
        boolean isRead = false;
            Books(String title, String author, int pages) {
                this.title = title;
                this.author = author;
                this.pages = pages;
    }
    void printInfo() {
            String readed;
            if(isRead) {
                readed = "Да";
            } else {
                readed = "Нет";
            }
            System.out.printf("\n Название: %s \n Автор: %s \n Количество страниц: %d \n Прочитана: %s \n", title, author, pages, readed);
        }
}
