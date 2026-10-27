import java.sql.SQLOutput;
import java.util.Scanner;

public class W3D1 {
    public static void main(String args[]) {
        Student s1 = new Student("Иван", 20, 4.0);
        Student s2 = new Student("Леха", 31, 2.3);
        Student s3 = new Student("Мбаппе", 28, 3.7);
        s1.averageIncrease(0.5);
        s1.printInfo();
        s1.sayHello();
        if(s1.isAdult()) {
            System.out.println("Cовершеннолетний");
        } else {
            System.out.println("Несовершеннолетний");
        }
        s2.printInfo();
        s2.sayHello();
        if(s2.isAdult()) {
            System.out.println("Cовершеннолетний");
        } else {
            System.out.println("Несовершеннолетний");
        }
        s3.printInfo();
        s3.sayHello();
        if(s3.isAdult()) {
            System.out.println("Cовершеннолетний");
        } else {
            System.out.println("Несовершеннолетний");
        }

        Car toyota = new Car(89100, 2018, "Toyota", "Camry");
        Car bMW = new Car(10200, 2023, "BMW", "M5");
        Car honda = new Car(304560, 1992, "Honda", "Civic");


        honda.drive(500);
        honda.printInfo();
        honda.drive(5000);
        honda.printInfo();
        if(honda.isOld()) {
            System.out.println(honda.brand + honda.model + " Авто старое ");
        } else {
            System.out.println(honda.brand + honda.model + " Авто новое ");
        }
        if(honda.needService()) {
            System.out.println(honda.brand + honda.model + " Необходим ТО");
        } else {
            System.out.println(honda.brand + honda.model + " ТО не нужен");
        }


        Book persiJackson = new Book("Перси Джексон", "Рик Риордан", 800);
        Book mockingbird = new Book("Убить пересмешника", "Харпер Ли", 450);
        Book theGrapes = new Book("Гроздья гнева", "Джон Стейнбек", 600);

        mockingbird.readBook();
        persiJackson.readBook();

        mockingbird.printInfo();
        persiJackson.printInfo();
        theGrapes.printInfo();

        if(mockingbird.isBigBook()) {
            System.out.println(mockingbird.title + " Книга большая");
        } else {
            System.out.println(mockingbird.title + " Книга небольшая");
        }
        if(persiJackson.isBigBook()) {
            System.out.println(persiJackson.title + " Книга большая");
        } else {
            System.out.println(persiJackson.title + " Книга небольшая");
        }

        mockingbird.increasePages(200);
        if(mockingbird.isBigBook()) {
            System.out.println(mockingbird.title + " Книга большая");
        } else {
            System.out.println(mockingbird.title + " Книга небольшая");
        }

    }
}
class Student {
    String name;
    int age;
    double average;
    Student(String name, int age, double average) {
        this.name = name;
        this.age = age;
        this.average = average;
        }
    void sayHello() {
        System.out.println("Меня зовут " + name + "\n");
    }
    boolean isAdult() {
        if(age >= 18) {
            return true;
        } else {
            return false;
            }
        }
     void averageIncrease(double value) {
        this.average = average + value;

    }
    void printInfo() {
        System.out.println("Имя: " + name + "\n" + "Возраст: " + age + "\n"+ "Средний балл: " + average + "\n");
    }
}
    class Car {
    int mileage;
    int year;
    String brand;
    String model;
        Car(int mileAge, int year, String brand, String model) {
            this.mileage = mileAge;
            this.year = year;
            this.brand = brand;
            this.model = model;
        }
        void printInfo() {
            System.out.printf("Марка автомобиля: %s, модель: %s, пробег %d, год выпуска %d \n", brand, model, mileage, year );
        }
        void drive(int km) {
            this.mileage = this.mileage + km;
        }
        boolean needService() {
            if (this.mileage >= 100000) {
                return true;
            } else {
                return false;
            }
        }
        boolean isOld() {
            if (year <= 2016) {
                return true;
            } else {
                return false;
            }
        }
    }
     class Book {
    String title;
    String author;
    int pages;
    boolean isRead = false;
        Book(String title, String author, int pages) {
            this.title = title;
            this.author = author;
            this.pages = pages;
        }
        void increasePages(int amount) {
            this.pages += amount;
        }
        boolean isBigBook() {
            if (pages >= 500) {
                return true;
            } else {
                return false;
            }
        }
        void readBook() {
            this.isRead = true;
        }
        void printInfo() {
            String readed;
            if(isRead == true) {
                readed = "Да";
            } else {
                readed = "Нет";
            }
            System.out.printf("\n Название: %s \n Автор: %s \n Количество страниц: %d \n Прочитана: %s \n", title, author, pages, readed);
        }
     }
