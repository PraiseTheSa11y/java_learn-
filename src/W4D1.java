import java.util.Arrays;

public class W4D1 {
    public static void main(String[] args) {
        int[] grades = new int[] {3, 3, 5, 5, 3};
        NewStudent student = new NewStudent("Алексей", 19, grades);
        grades[0] = 1;
        System.out.println(student.getAverage());
        student.addGrade(5);
        System.out.println(student.getAverage());
        System.out.println(student.getGrades()[0]);
        try {
            NewStudent student3 = new NewStudent(null, 20, new int[]{3, 3, 4, 5, 4});
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        try {
            NewStudent student2 = new NewStudent("Кирилл", 20, new int[]{3, 3, 6, 5, 8});
        } catch (IllegalArgumentException e1) {
            System.out.println(e1.getMessage());
        }
    }
}
class NewStudent {
    private String name;
    private int age;
    private int[] grades;
        NewStudent(String name, int age, int[] grades) {
            if( name != null && !name.isBlank()) {
                this.name = name;
            } else {
                throw new IllegalArgumentException("Поле =Имя= не может быть пустым");
        }
            setAge(age);
                for(int grade : grades) {
                    if (grade < 1 || grade > 5) {
                        throw new IllegalArgumentException("Некорректный ввод оценок, введите числа от 1 до 5");
                    }
                }
            this.grades = Arrays.copyOf(grades, grades.length);
        }
        public String getName() {
            return name;
        }
        public void setAge(int age) {
            if (age > 0 && age < 99) {
                this.age = age;
            } else {
                throw new IllegalArgumentException("Некорректный возраст");
            }
        }
        public int getAge() {
            return age;
        }
        public double getAverage() {
            int sum = 0;
            double average = 0;
            for (int i = 0; i < grades.length; i++) {
                sum += grades[i];
            }
            average = (double) sum / grades.length;
            return average;
        }
        public void addGrade(int grade) {
            if (grade > 0 && grade <= 5) {
                int[] newGrades = Arrays.copyOf(grades, grades.length + 1);
                newGrades[newGrades.length - 1] = grade;
                grades = newGrades;
            } else {
                throw new IllegalArgumentException("Некорректный ввод оценок, введите числа от 1 до 5");
            }
        }
        public int[] getGrades() {
            int[] gradesCopy = Arrays.copyOf(grades, grades.length);
            return gradesCopy;
        }
}
