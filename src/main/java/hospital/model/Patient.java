package hospital.model;

import hospital.interfaces.Treatable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Patient extends Person implements Treatable {

    private static final long serialVersionUID = 5L;
    private static final String DEFAULT_DIAGNOSIS = "Не установлен";
    private static final boolean DEFAULT_HOSPITALIZED = false;

    private String diagnosis;
    private boolean hospitalized;
    private int roomNumber;

    public Patient(String name, int age, String department, int roomNumber) {
        super(name, age, department);
        this.diagnosis = DEFAULT_DIAGNOSIS;
        this.hospitalized = DEFAULT_HOSPITALIZED;
        this.roomNumber = roomNumber;
    }

    @Override
    public String getRole() {
        return "Пациент";
    }

    @Override
    public void displayInfo() {
        System.out.println("ПАЦИЕНТ");
        System.out.println("Имя: " + name);
        System.out.println("Возраст: " + age + " лет");
        System.out.println("Отделение: " + department);
        System.out.println("Диагноз: " + diagnosis);
        System.out.println("Палата: " + roomNumber);
        System.out.println("Статус: " + (hospitalized ? "Госпитализирован" : "Выписан"));
    }

    @Override
    public void diagnose(String diagnosis) {
        this.diagnosis = diagnosis;
        System.out.println("Пациенту " + name + " поставлен диагноз: " + diagnosis);
    }

    @Override
    public void discharge() {
        this.hospitalized = false;
        System.out.println("Пациент " + name + " выписан из больницы.");
    }

    @Override
    public void hospitalize() {
        this.hospitalized = true;
        System.out.println("Пациент " + name + " госпитализирован в отделение: " + department);
    }
}