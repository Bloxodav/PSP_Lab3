package hospital.model;

import hospital.interfaces.Treatable;

public class Patient extends Person implements Treatable {

    private static final long serialVersionUID = 5L;

    private String diagnosis;

    private boolean hospitalized;

    private int roomNumber;

    public Patient(String name, int age, String department, int roomNumber) {
        super(name, age, department);
        this.diagnosis = "Не установлен";
        this.hospitalized = false;
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

    public String getDiagnosis() {
        return diagnosis;
    }
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public boolean isHospitalized() {
        return hospitalized;
    }
    public void setHospitalized(boolean hospitalized) {
        this.hospitalized = hospitalized;
    }

    public int getRoomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}