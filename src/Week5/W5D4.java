package Week5;

//Часть работы с интерфейсами реализована в 2 дне на классе Employee

public class W5D4 {
    public static void main(String[] args) {

        FoodProduct foodProduct = new FoodProduct("Молоко", 100, 30);

        FoodProduct foodProduct2 = new FoodProduct("Cуши", 1000, 3);

        TechProduct techProduct = new TechProduct("Плойка 5", 50000, 13);

        TechProduct techProduct2 = new TechProduct("ЭксБокс", 60000, 8);
        System.out.print("Товар: " + printProductNames(techProduct) + " ");
        printDiscount(techProduct);
        System.out.print("Товар: " + printProductNames(techProduct2) + " ");
        printDiscount(techProduct2);
        System.out.print("Товар: " + printProductNames(foodProduct2) + " ");
        printDiscount(foodProduct2);
    }
    public static void printDiscount(Discountable product) {
        System.out.println("Скидка составляет: " + product.calculateDiscount() + " руб.");
    }
    public static String printProductNames(Product product) {
        return product.getName();
    }
}
abstract class Product {
    private String name;
    private int price;
    Product(String name, int price) {
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return this.name;
    }
    public int getPrice() {
        return this.price;
    }
}
class FoodProduct extends Product implements Discountable {
    private int expirationDays;
    FoodProduct(String name, int price, int expirationDays) {
        super(name, price);
        this.expirationDays = expirationDays;
    }
    public int calculateDiscount() {
        int discount = 0;
        if(expirationDays < 7) {
            discount = getPrice() / 5;
            return discount;
        }
            return discount;
    }
}
class TechProduct extends Product implements Discountable {
    private int warrantyMonths;
    TechProduct(String name, int price, int warrantyMonths) {
        super(name, price);
        this.warrantyMonths = warrantyMonths;
    }
    public int calculateDiscount() {
        int discount = 0;
        if (warrantyMonths < 12) {
            discount = getPrice() / 10;
            return discount;
        } else {
            return discount;
        }
    }
}
