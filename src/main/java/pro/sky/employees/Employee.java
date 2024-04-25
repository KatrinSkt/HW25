package pro.sky.employees;

import java.util.Objects;

public class Employee {

    private String firstName;
    private String lastName;

    public Employee(String name, String surname) {
        this.firstName = name;
        this.lastName = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String name() {
        return firstName;
    }

    public String surname() {
        return lastName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return "Сотрудник: " + firstName + " " + lastName;
    }
}

