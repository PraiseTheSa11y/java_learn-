package Week5;

import java.util.ArrayList;

public class W5D2 {
    public static void main(String [] args) {
        ArrayList<Employee> employees = new ArrayList<>();
        Manager manager = new Manager("Артём", 80000, 5, 500000);
        Developer developer = new Developer("Васян", 200000, 15000);
        Intern intern = new Intern("Саня", 80000, 30000);
        employees.add(manager);
        employees.add(developer);
        employees.add(intern);

        for(Employee employee : employees) {
            System.out.println("Сотрудник: " + employee.getName() + " Зарплата: " + employee.calculateSalary());
        }

        printBonus(developer);
        printBonus(manager);
    }
    public static void printBonus(BonusEligible employee) {
        System.out.println("Бонус работника: " + employee.calculateBonus());
    }
}
abstract class Employee  {
    private String name;
    private double baseSalary;
        Employee(String name, double baseSalary) {
            this.name = name;
            this.baseSalary = baseSalary;
        }
        public abstract double calculateSalary();

        public String getName() {
            return name;
        }
        public double getSalary() {
            return baseSalary;
        }
}
class Manager extends Employee implements BonusEligible {
    private int bonus;
    private double kpi;
    private double sales;

    Manager(String name, double baseSalary, double kpi, double sales) {
        super(name, baseSalary);
        this.kpi = kpi;
        this.sales = sales;
    }
    @Override
    public int calculateBonus() {
      bonus = (int) Math.round(((sales * kpi) / 160));
        return bonus;
    }
    @Override
    public double calculateSalary() {
        return getSalary() + ((sales * kpi) / 100);
    }
}
class Developer extends Employee implements BonusEligible {
    private int bonus;
    Developer(String name, double baseSalary, int bonus) {
        super(name, baseSalary);
        this.bonus = bonus;
    }
    @Override
    public int calculateBonus() {

        bonus = (int) Math.round(getSalary()) / 10;
        return bonus;
    }
    @Override
    public double calculateSalary() {
        return getSalary() + bonus;
    }
}
class Intern extends Employee {

    private int fixedSalary;
    Intern(String name, double baseSalary, int fixedSalary) {
        super(name, baseSalary);
        this.fixedSalary = fixedSalary;
    }
    @Override
    public double calculateSalary() {
        return fixedSalary;
    }
}
