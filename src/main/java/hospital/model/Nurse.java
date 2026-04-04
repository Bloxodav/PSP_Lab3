package hospital.model;

import hospital.interfaces.Payable;

public class Nurse extends MedicalPerson implements Payable {

    private static final long serialVersionUID = 4L;
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
        salary += salary * (percent / 100.0);
        System.out.println("Зарплата медсестры " + name + " повышена с "
                + oldSalary + " до " + salary + " руб. (+" + percent + "%)");
    }

    public String getShift() {
        return shift;
    }
    public void setShift(String shift) {
        this.shift = shift;
    }

    public int getExperience() {
        return experience;
    }
    public void setExperience(int experience) {
        this.experience = experience;
    }
}