package hospital.model;

import hospital.interfaces.Payable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Nurse extends MedicalPerson implements Payable {

    private static final long serialVersionUID = 4L;
    private static final double PERCENT_DIVISOR = 100.0;

    private String shift;
    private int experience;

    public Nurse(String name, int age, String department,
                 String specialization, double salary,
                 String shift, int experience) {
        super(name, age, department, specialization, salary);
        this.shift = shift;
        this.experience = experience;
    }

    @Override
    public String getRole() {
        return "Медсестра";
    }

    @Override
    public void displayInfo() {
        System.out.println("МЕДСЕСТРА");
        System.out.println("Имя: " + name);
        System.out.println("Возраст: " + age + " лет");
        System.out.println("Отделение: " + department);
        System.out.println("Специализация: " + specialization);
        System.out.println("Смена: " + shift);
        System.out.println("Опыт: " + experience + " лет");
        System.out.println("Зарплата: " + salary + " руб.");
    }

    @Override
    public void paySalary() {
        System.out.println("Выплачена зарплата медсестре " + name + ": " + salary + " руб.");
    }

    @Override
    public void raiseSalary(double percent) {
        double oldSalary = salary;
        salary += salary * (percent / PERCENT_DIVISOR);
        System.out.println("Зарплата медсестры " + name + " повышена с "
                + oldSalary + " до " + salary + " руб. (+" + percent + "%)");
    }
}