package Week5;

import java.util.ArrayList;

public class W5D2part2 {
    public static void main(String[] args) {
        ArrayList<Delivery> deliveries = new ArrayList<>();
        StandardDelivery standard = new StandardDelivery("Санкт-Петербург");
        ExpressDelivery express = new ExpressDelivery("Москва");
        InternationalDelivery international = new InternationalDelivery("Нью-Йорк", 30);

        deliveries.add(standard);
        deliveries.add(express);
        deliveries.add(international);

        for (Delivery delivery : deliveries) {
            System.out.println("Пункт назначения: " + delivery.getDestination() + " Стоимость доставки: " + delivery.getDeliveryPrice());
        }
    }
}
abstract class Delivery {
    private String destination;

        Delivery(String destination) {
            this.destination = destination;
        }
    public String getDestination() {
        return destination;
    }
    public abstract double getDeliveryPrice();
    }
class StandardDelivery extends Delivery {
        private double price = 500;
        StandardDelivery(String destination) {
            super(destination);
        }

        @Override
    public double getDeliveryPrice() {
            return price;
        }
}
class ExpressDelivery extends Delivery {
    private double price = 500;
    private double rapidTax = 700;
    ExpressDelivery(String destination) {
        super(destination);
    }
    @Override
    public double getDeliveryPrice() {
        return price + rapidTax;
    }
}
class InternationalDelivery extends Delivery {
    private double price = 300;
    private int packageWeight;
    private int internationalTax = 1000;
    InternationalDelivery(String destination, int packageWeight) {
        super(destination);
        this.packageWeight = packageWeight;
    }
    @Override
    public double getDeliveryPrice() {
        return price * packageWeight + internationalTax;
    }
}

