package pro.sky.employees;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    public Map<String, Employee> employees;
    private ValidationService validationService;
    public void EmployeeService(ValidationService validationService) {
        this.validationService = validationService;
    }

    @Override
    public Employee add(String firstName, String lastName) {
        firstName = validationService.validateCheckName(firstName);
        lastName = validationService.validateCheckName(lastName);
        String key = doKey(firstName, lastName);
        if(employees.containsKey(key)){
            throw new EmployeeAlreadyAddedException();
        }
        Employee employee = new Employee(firstName, lastName);
        employees.put(key, employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        String key = doKey(firstName, lastName);
        if(!employees.containsKey(key)){
            throw new EmployeeNotFoundException();
        }
        return employees.remove(key);
    }

    @Override
    public Employee find(String firstName, String lastName) {
        String key = doKey(firstName, lastName);
        if(!employees.containsKey(key)){
            throw new EmployeeNotFoundException();
        }
       return employees.get(key);
    }
    public String doKey(String firstName, String lastName) {
        return firstName + " " + lastName;
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employees.values());
    }
}
