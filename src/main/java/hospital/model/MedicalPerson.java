package hospital.model;

public abstract class MedicalPerson extends Person {

    private static final long serialVersionUID = 2L;

    protected String specialization;
    protected double salary;

    public MedicalPerson(String name, int age, String department,
                         String specialization, double salary) {
        super(name, age, department);
        this.specialization = specialization;
        this.salary = salary;
    }

    public String getSpecialization() {
        return specialization;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
}