package Week5;

import java.util.ArrayList;

public class W5D4part2 {
    public static void main(String[] args) {
        Taxi taxiMers = new Taxi("S класс", Brand.MERCEDES, 2000);
        Taxi taxiBmw = new Taxi("M5", Brand.BMW, 1500);

        Bus busSmall = new Bus("Мини-вэн", Brand.HYUNDAI, 900, 7);
        Bus busMedium = new Bus("Средний маршрутный автобус", Brand.MERCEDES, 1500, 16);
        Bus busLarge = new Bus("Тур-австобус", Brand.VOLKSWAGEN, 2500, 24);

        ArrayList<Transport> rentables = new ArrayList<>();
        rentables.add(taxiMers);
        rentables.add(taxiBmw);
        rentables.add(busSmall);
        rentables.add(busMedium);
        rentables.add(busLarge);

        printTotalArray(rentables);


    }
    public static void printTotalArray(ArrayList<Transport> rentables) {
        for(Transport transport : rentables) {
            System.out.println(printTotalInfo(transport));
            System.out.println(printRentalPrice(transport, 5));

        }
    }
    public static String printRentalPrice(Rentable rentable, int hours) {
        return "Стоимость аренды: " + rentable.calculateRentPrice(hours);
    }
    public static String printTotalInfo(Transport transport) {
        return "Модель и марка транспорта: " + transport.getModel() + ", " + transport.getBrand().getRusName();
    }
}
abstract class Transport implements Rentable {
    private String model;
    private Brand brand;
    private int pricePerHour;
    Transport(String model, Brand brand , int pricePerHour) {
        this.model = model;
        this.brand = brand;
        this.pricePerHour= pricePerHour;
    }
    public String getModel() {
        return model;
    }
    public Brand getBrand() {
        return brand;
    }
    public int getPricePerHour() {
        return pricePerHour;
    }
}
class Taxi extends Transport {

    Taxi(String model, Brand brand, int pricePerHour) {
        super(model, brand, pricePerHour);
    }
    public int calculateRentPrice(int hours) {
        int totalPrice = 0;
        if(getBrand().equals(Brand.MERCEDES) || getBrand().equals(Brand.BMW)) {
            totalPrice = getPricePerHour() * hours + 5000;
        }
        if(getBrand().equals(Brand.VOLKSWAGEN)) {
            totalPrice = getPricePerHour() * hours + 3500;
        }
        if(getBrand().equals(Brand.HYUNDAI) || getBrand().equals(Brand.NISSAN)) {
            totalPrice = getPricePerHour() * hours + 1000;
        }
        return totalPrice;
    }

}
class Bus extends Transport {
    private int seatingCapacity;

    Bus(String model, Brand brand, int price, int seatingCapacity) {
        super(model, brand, price);
        this.seatingCapacity = seatingCapacity;
    }

    public int calculateRentPrice(int hours) {
        int totalPrice = getPricePerHour() * hours;
        if (seatingCapacity >= 8 && seatingCapacity < 15) {
            totalPrice += 1000;
        }
        if (seatingCapacity >= 15 && seatingCapacity < 20) {
            totalPrice += 2500;
        }
        if (seatingCapacity >= 20) {
            totalPrice += 5000;
        }
        return totalPrice;
    }
}
enum Brand {
    MERCEDES("Мерседес"),
    BMW("Бмв"),
    VOLKSWAGEN("Фолькцваген"),
    NISSAN("Ниссан"),
    HYUNDAI("Хёндай");

    private final String rusName;
    Brand(String rusName) {
        this.rusName = rusName;
    }
    public String getRusName() {
        return rusName;
    }
}
