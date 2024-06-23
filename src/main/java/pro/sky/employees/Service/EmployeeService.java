package pro.sky.employees.Service;

import org.springframework.stereotype.Service;
import pro.sky.employees.Employee;
import pro.sky.employees.Exceptions.EmployeeAlreadyAddedException;
import pro.sky.employees.Exceptions.EmployeeNotFoundException;
import pro.sky.employees.Exceptions.EmployeeStorageIsFullException;

import java.util.*;

@Service
public class EmployeeService {
    private int max = 10;
    public Map<String, Employee> employees;
    private ValidationService validationService;

    public EmployeeService(ValidationService validationService) {
        this.validationService = validationService;
    }


    public Employee add(String firstName, String lastName, int salary, int departmentId) {
        firstName = validationService.validateCheckName(firstName);
        lastName = validationService.validateCheckName(lastName);
        if (employees.size() >= max) {
            throw new EmployeeStorageIsFullException();
        }
        String key = doKey(firstName, lastName);
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }
        Employee employee = new Employee(firstName, lastName, salary, departmentId);
        employees.put(key, employee);
        return employee;
    }


    public Employee remove(String firstName, String lastName) {
        String key = doKey(firstName, lastName);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException();
        }
        return employees.remove(key);
    }


    public Employee find(String firstName, String lastName) {
        String key = doKey(firstName, lastName);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException();
        }
        return employees.get(key);
    }

    public String doKey(String firstName, String lastName) {
        return firstName + " " + lastName;
    }


    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employees.values());
    }
}
