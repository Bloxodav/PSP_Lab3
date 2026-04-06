package hospital.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}