package Week5;

import java.util.ArrayList;

import java.util.Scanner;

public class W5D6 {
    public static void main(String[] args) {

            ArrayList<Employee6> employees = new ArrayList<>();
            EmployeeService employeeService = new EmployeeService();
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("\n=== МЕНЮ ===");
                System.out.println("1. Показать всех сотрудников");
                System.out.println("2. Добавить сотрудника");
                System.out.println("3. Удалить сотрудника");
                System.out.println("4. Изменить статус сотрудника");
                System.out.println("5. Найти сотрудника по имени");
                System.out.println("6. Показать сотрудников с бонусами");
                System.out.println("7. Найти сотрудника с самой высокой зарплатой");
                System.out.println("8. Посчитать сотрудников в отделе");
                System.out.println("9. Посчитать общий фонд зарплат");
                System.out.println("10. Сводка по компании");
                System.out.println("0. Выход");

                int choice = employeeService.readInt(sc, "Выберите пункт меню: ", 0);
                if (choice == 0) {
                    break;
                }

                switch (choice) {
                    case 1:
                        employeeService.printEmployees(employees);
                        break;
                    case 2:
                        employeeService.addEmployee(employees, sc);
                        break;
                    case 3:
                        employeeService.removeEmployee(employees, sc);
                        break;
                    case 4:
                        Employee6 empToChange = employeeService.findFirstByName(employees, sc);
                        if (empToChange != null) {
                            int i = 1;
                            for (Status status : Status.values()) {
                                System.out.println(i++ + ". " + status.getRusStatus());
                            }
                            Status newStatus = employeeService.chooseStatus(sc);
                            employeeService.changeStatus(empToChange, newStatus);
                        }
                        break;
                    case 5:
                        Employee6 found = employeeService.findFirstByName(employees, sc);
                        if (found != null) {
                            System.out.println(found.getInfo());
                        }
                        break;
                    case 6:
                        employeeService.printBonusEmployees(employees);
                        break;
                    case 7:
                        Employee6 best = employeeService.findHighestSalaryEmployee(employees, sc);
                        if (best != null) {
                            System.out.println(best.getInfo());
                        }
                        break;
                    case 8:
                        int i = 1;
                        for (Department dept : Department.values()) {
                            System.out.println(i++ + ". " + dept.getRusDepartment());
                        }
                        Department selectedDept = employeeService.chooseDepartment(sc);
                        System.out.println("Количество сотрудников: " + employeeService.countByDepartment(employees, selectedDept));
                        break;
                    case 9:
                        System.out.println("Общий фонд зарплат: " + employeeService.calculateTotalSalary(employees, sc));
                        break;
                    case 10:
                        employeeService.getCompanySummary(employees, sc);
                        break;
                    default:
                        System.out.println("Выберите пункт меню от 0 до 10");
                }
            }
        }
    }

abstract class Employee6 {
        private String name;
        private int age;
        private Department department;
        private Status status;
        private int baseSalary;

        Employee6(String name, int age, Department department, Status status, int baseSalary) {
            this.name = name;

            if(age > 0) {
                this.age = age;
            } else {
                throw new IllegalArgumentException("Возраст не может быть отрицательным");
            }

            this.department = department;
            this.status = status;

            if(baseSalary >= 0 ) {
                this.baseSalary = baseSalary;
            } else {
                throw new IllegalArgumentException("Зарплата не может быть меньше нуля");
            }
        }

        public abstract int calculateSalary();

        public String getName() {
            return name;
        }

        public Department getDepartment() {
            return department;
        }
        public Status getStatus() {
            return status;
        }
        public void setStatus(Status newStatus) {
            if (newStatus == null) {
                throw new IllegalArgumentException("Некорректный статус");
            }

            if (this.status == Status.FIRED && newStatus == Status.VACATION) {
                throw new IllegalStateException("Уволенного сотрудника нельзя отправить в отпуск!");
            }

            this.status = newStatus;
        }

        public int getBaseSalary() {
            return baseSalary;
        }
        public int getAge() {
            return age;
        }
        public String getInfo() {
            return "Имя: " + getName() +
                    "\nВозраст: " + getAge() +
                    "\nОтдел: " + getDepartment().getRusDepartment() +
                    "\nСтатус: " + getStatus().getRusStatus() +
                    "\nЗарплата: " + calculateSalary();
        }
}

class Developer6 extends Employee6 {

    Developer6(String name, int age, Department department, Status status, int baseSalary) {
        super(name, age, department, status, baseSalary);
    }

    @Override
    public int calculateSalary() {
        return getBaseSalary();
    }
}

class Manager6 extends Employee6 implements BonusEligible {

   Manager6(String name, int age, Department department, Status status, int baseSalary) {
        super(name, age, department, status, baseSalary);
    }

    @Override
    public int calculateBonus() {
        return (getBaseSalary() / 5);
    }

    @Override
    public int calculateSalary() {
        return getBaseSalary();
    }
}

class Intern6 extends Employee6 {

    Intern6(String name, int age, Department department, Status status, int baseSalary) {
        super(name, age, department, status, baseSalary);
    }

    @Override
    public int calculateSalary() {
        return getBaseSalary();
    }
}

enum Department {
    DEVELOPMENT("Отдел разработки"),
    HR("Отдел кадров"),
    SUPPORT("Отдел поддержки"),
    SALES("Отдел продаж");

    Department(String rusDepartment) {
        this.rusDepartment = rusDepartment;
    }

    private final String rusDepartment;

    public String getRusDepartment() {
        return rusDepartment;
    }
}

enum Status {
    ACTIVE("Действующий"),
    VACATION("В отпуске"),
    FIRED("Уволен");

    private final String rusStatus;

    Status(String rusStatus) {
        this.rusStatus = rusStatus;
    }

    public String getRusStatus() {
        return rusStatus;
    }
}

class EmployeeService {

    public void printEmployees(ArrayList<Employee6> employees) {
        int i = 1;

        if (employees.isEmpty()) {
            System.out.println("Список сотрудников пуст");
            return;
        }

        for (Employee6 employee : employees) {
            System.out.println(i + ". " + employee.getName() + ", " + employee.getDepartment().getRusDepartment());
            i++;
        }
    }

    public void addEmployee(ArrayList<Employee6> employees, Scanner sc) {

        int i = 1;
        String name = readString(sc, "Введите имя: ");

        int age = readInt(sc, "Введите возраст: ", 18);

        int employeeType = chooseEmployeeType(sc);

        for (Department department : Department.values()) {
            System.out.println(i + ". " + department.getRusDepartment());
            i++;
        }

        i = 1;
        Department userDepartment = chooseDepartment(sc);

        for (Status status : Status.values()) {
            System.out.println(i + ". " + status.getRusStatus());
            i++;
        }

        Status userStatus = chooseStatus(sc);

        int baseSalary = readInt(sc, "Укажите зарплату сотрудника: ", 0);


        Employee6 newEmployee = switch (employeeType) {

            case 1 -> new Developer6(name, age, userDepartment, userStatus, baseSalary);
            case 2 -> new Manager6(name, age, userDepartment, userStatus, baseSalary);
            case 3 -> new Intern6(name, age, userDepartment, userStatus, baseSalary);

            default -> throw new IllegalStateException("Неизвестный тип сотрудника");
        };

        employees.add(newEmployee);

        System.out.println(newEmployee.getInfo());
    }

    public void removeEmployee(ArrayList<Employee6> employees, Scanner sc) {
        if (employees.isEmpty()) {
            System.out.println("Список сотрудников пуст, удалять некого.");
            return;
        }

        printEmployees(employees);

        while (true) {
            int userChoice = readInt(sc, "Выберите сотрудника для удаления: ", 1);
            userChoice -= 1;

            if (userChoice < employees.size()) {
                Employee6 userEmployee = employees.get(userChoice);

                if (userEmployee != null) {
                    employees.remove(userEmployee);
                    System.out.println("Сотрудник: " + userEmployee.getName() + " успешно удалён");
                    break;
                } else {
                    System.out.println("Не удалось удалить сотрудника попробуйте ещё раз");
                }
            } else {
                System.out.println("Такого номера нет в списке, попробуйте ещё раз");
            }
        }
    }

    public void changeStatus(Employee6 employee, Status newStatus) {
        try {

            if (employee == null) {
                System.out.println("Сотрудник не найден");
                return;
            }

            employee.setStatus(newStatus);
            System.out.println("Cтатус сотрудника успешно изменён");

        } catch (IllegalStateException | IllegalArgumentException e) {

            System.out.println("Не удалось поменять статус сотрудника: " + e.getMessage());
        }
    }

    public void printBonusEmployees(ArrayList<Employee6> employees) {
        for (Employee6 employee : employees) {

            if (employee instanceof BonusEligible) {
                System.out.println(employee.getName() + ", " + employee.getDepartment().getRusDepartment());
            }
        }
    }

    public void getCompanySummary(ArrayList<Employee6> employees, Scanner sc) {

        if (employees.isEmpty()) {

            System.out.println("В компании пока нет сотрудников.");
            return;
        }

        System.out.println("=== Сводка по компании ===");
        System.out.println("Всего сотрудников: " + employees.size());
        System.out.println("Общий фонд зарплат: " + calculateTotalSalary(employees, sc));

        System.out.println("\nСотрудники по отделам:");

        for (Department department : Department.values()) {
            System.out.println("- " + department.getRusDepartment() + ": " + countByDepartment(employees, department));
        }

        Employee6 highestSalaryEmployee = findHighestSalaryEmployee(employees, sc);

        if (highestSalaryEmployee != null) {

            System.out.println("\nСамый высокооплачиваемый сотрудник: " + highestSalaryEmployee.getName());
        }
    }

    public Employee6 findFirstByName(ArrayList<Employee6> employees, Scanner sc) {

        String name = readString(sc, "Введите имя для поиска: ");

        String nameLowerCase = name.toLowerCase();
        for (Employee6 employee : employees) {

            if (employee.getName().toLowerCase().contains(nameLowerCase)) {
                return employee;
            }
        }

        System.out.println("Сотрудник не найден");
        return null;
    }

    public Employee6 findHighestSalaryEmployee(ArrayList<Employee6> employees, Scanner sc) {

        if (employees.isEmpty()) {
            System.out.println("Список сотрудников пуст.");
            return null;
        }

        boolean withBonus = askWithBonus(sc);

        Employee6 highestEmployee = employees.getFirst();

        int currentBest = getSalary(highestEmployee, withBonus);

        for (Employee6 employee : employees) {
            int salary = getSalary(employee, withBonus);

            if (salary > currentBest) {
                currentBest = salary;
                highestEmployee = employee;
            }
        }

        return highestEmployee;
    }

    public int countByDepartment(ArrayList<Employee6> employees, Department department) {

        if (employees.isEmpty()) {
            System.out.println("Список сотрудников пуст");
            return 0;
        }

        int counter = 0;

        for (Employee6 employee : employees) {

            if (employee.getDepartment() == department) {
                counter++;
            }
        }

        return counter;
    }

    public int calculateTotalSalary(ArrayList<Employee6> employees, Scanner sc) {
        int total = 0;

        boolean withBonus = askWithBonus(sc);

        for (Employee6 employee : employees) {
            total += getSalary(employee, withBonus);
        }

        return total;
    }

    public int readInt(Scanner sc, String prompt, int min) {
        while (true) {
            System.out.println(prompt);

            try {
                String userInput = sc.nextLine();
                int value = Integer.parseInt(userInput);

                if (value >= min) {
                    return value;
                }

                System.out.println("Число должно быть не меньше " + min);

            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод! Введите целое число.");
            }
        }
    }

    private String readString(Scanner sc, String prompt) {
        while (true) {
            System.out.println(prompt);
            String userInput = sc.nextLine();

            if (userInput.isBlank()) {
                System.out.println("Поле не может остаться пустым");

            } else {
                return userInput.trim();
            }
        }
    }

    public Department chooseDepartment(Scanner sc) {
        while (true) {
            int userChoice = readInt(sc, "Выберите отдел от 1 до 4: ", 1);

            switch (userChoice) {
                case 1:
                    return Department.DEVELOPMENT;
                case 2:
                    return Department.HR;
                case 3:
                    return Department.SUPPORT;
                case 4:
                    return Department.SALES;
                default:
                    System.out.println("Введите число от 1 до 4: ");
            }
        }
    }

    public Status chooseStatus(Scanner sc) {
        while (true) {
            int userChoice = readInt(sc, "Выберите статус сотрудника от 1 до 3: ", 1);

            switch (userChoice) {
                case 1:
                    return Status.ACTIVE;
                case 2:
                    return Status.VACATION;
                case 3:
                    return Status.FIRED;
                default:
                    System.out.println("Введите число от 1 до 3: ");
            }
        }
    }

    private int getSalary(Employee6 employee, boolean withBonus) {
        int total = employee.calculateSalary();

        if (withBonus && employee instanceof BonusEligible bonusEligibleEmployee) {
            total += bonusEligibleEmployee.calculateBonus();
        }

        return total;
    }

    private int chooseEmployeeType(Scanner sc) {

        while (true) {

            int userChoice = readInt(sc, "Выберите тип сотрудника: \n" +
                    "1. Разработчик \n" +
                    "2. Менеджер \n " +
                    "3. Стажёр", 1);

            if (userChoice <= 3) {

                return userChoice;

            } else {

                System.out.println("Введите число от 1 до 3");
            }
        }
    }

    private boolean askWithBonus(Scanner sc) {
        int userChoice;

        while (true) {

            userChoice = readInt(sc, "Выберите условия расчёта зарплаты:\n1. Оклад\n2. Оклад + бонус", 1);

            if (userChoice == 1 || userChoice == 2) {

                return userChoice == 2;
            }
            System.out.println("Введите 1 или 2.");
        }
    }


}