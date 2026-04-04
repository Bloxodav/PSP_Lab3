package hospital.model;

import java.io.Serializable;

public abstract class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String name;
    protected int age;
    protected String department;

    public Person(String name, int age, String department) {
        this.name = name;
        this.age = age;
        this.department = department;
    }

    public abstract String getRole();

    public abstract void displayInfo();


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "[" + getRole() + "] " + name + ", " + age + " лет, отделение: " + department;
    }
}