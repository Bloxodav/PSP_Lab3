package hospital.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public abstract class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String name;
    protected int age;
    protected String department;

    public abstract String getRole();

    public abstract void displayInfo();

    @Override
    public String toString() {
        return "[" + getRole() + "] " + name + ", " + age + " лет, отделение: " + department;
    }
}