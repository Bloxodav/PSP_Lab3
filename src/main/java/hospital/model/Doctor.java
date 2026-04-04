package hospital.model;

import hospital.interfaces.Payable;

public class Doctor extends MedicalPerson implements Payable {

    private static final long serialVersionUID = 3L;

    private String category;
    private int patientCount;

    public Doctor(String name, int age, String department,
                  String specialization, double salary, String category) {
        super(name, age, department, specialization, salary);
        this.category = category;
        this.patientCount = 0;
    }

    @Override
    public String getRole() {
        return "Врач";
    }
    @Override
    public void displayInfo() {
        System.out.println("ВРАЧ");
        System.out.println("Имя: " + name);
        System.out.println("Возраст: " + age + " лет");
        System.out.println("Отделение: " + department);
        System.out.println("Специализация: " + specialization);
        System.out.println("Категория: " + category);
        System.out.println("Зарплата: " + salary + " руб.");
        System.out.println("Пациентов: " + patientCount);
    }

    @Override
    public void paySalary() {
        System.out.println("Выплачена зарплата врачу " + name + ": " + salary + " руб.");
    }

    @Override
    public void raiseSalary(double percent) {
        double oldSalary = salary;
        salary += salary * (percent / 100.0);
        System.out.println("Зарплата врача " + name + " повышена с "
                + oldSalary + " до " + salary + " руб. (+" + percent + "%)");
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public int getPatientCount() {
        return patientCount;
    }
    public void setPatientCount(int patientCount) {
        this.patientCount = patientCount;
    }
}