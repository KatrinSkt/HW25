package pro.sky.employees.Controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.employees.Employee;
import pro.sky.employees.Service.EmployeeService;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee addNewEmployee(@RequestParam String firstName,
                                   @RequestParam String lastName,
                                   @RequestParam int salary,
                                   @RequestParam int departmentId) {
        return employeeService.add(firstName, lastName, salary, departmentId);
    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam String firstName,
                                   @RequestParam String lastName) {
        return employeeService.remove(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam String firstName,
                                 @RequestParam String lastName) {
        return employeeService.find(firstName, lastName);
    }

    @GetMapping
    public Collection<Employee> findAll() {
        return employeeService.findAll();
    }

}

