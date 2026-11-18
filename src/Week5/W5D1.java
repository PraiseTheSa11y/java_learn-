package Week5;

import java.util.ArrayList;

public class W5D1 {
    public static void main(String[] args) {
        Dog dog = new Dog("Шарик", "Овчарка");
        Cat cat = new Cat("Барсик");
        Bird bird = new Bird("Кеша");
        Fish fish = new Fish("Карась");
        Robot robot = new Robot("ГПТШКА");

        ArrayList<Movable> movables = new ArrayList<>();
        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(dog);
        animals.add(cat);
        animals.add(bird);
        animals.add(fish);

        movables.add(dog);
        movables.add(cat);
        movables.add(bird);
        movables.add(robot);

        for(Animal animal : animals) {
            System.out.println(animal.makeSound());
            System.out.println(animal.getInfo());
            System.out.println(animal.move());
        }

        for(Movable movable : movables)  {
            System.out.println(movable.move());
        }
    }
}
abstract class Animal implements Movable {
    private String name;

    Animal(String name) {
        this.name = name;
    }
    public abstract String makeSound();

    public String makeSound(int times) {
        String multipleSound = "";
        for(int i = 0; i < times; i++) {
           multipleSound += " " + makeSound();
        }
        return multipleSound;
    }
    public String getInfo() {
        return name;
    }
    public abstract String move();

}
class Dog extends Animal {
    private String breed;

    Dog(String name, String breed) {
        super(name);
        this.breed = breed;
    }
    public String getBreed() {
        return breed;
    }
    @Override
    public String getInfo() {
        return super.getInfo() + "," + " порода: " + getBreed();
    }
    @Override
    public String makeSound() {
        return "ГАВ";
    }
    @Override
    public String move() {
        return "Собака бегает";
    }
}
class Cat extends Animal {

    Cat(String name) {
        super(name);
    }
    @Override
    public String makeSound() {
        return "Мяу";
    }
    @Override
    public String getInfo() {
        return super.getInfo() + ", вид: Кошка";
    }
    @Override
    public String move() {
        return "Кошка ходит";
    }
}
class Bird extends Animal {

    Bird(String name) {
        super(name);
    }
    @Override
    public String makeSound() {
        return "Чирик";
    }
    @Override
    public String getInfo() {
        return super.getInfo() + ", вид: Птица";
    }
    @Override
    public String move() {
        return "Птица летает";
    }
}
class Fish extends Animal {

    Fish(String name) {
        super(name);
    }
    @Override
    public String makeSound() {
        return "Буль-буль";
    }
    @Override
    public String move() {
        return "Рыба плывёт";
    }
    @Override
    public String getInfo() {
        return super.getInfo() + ", вид: Рыба";
    }
}
class Robot implements Movable {
    private String name;

    Robot(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public String move() {
        return "Робот едет";
    }
}
