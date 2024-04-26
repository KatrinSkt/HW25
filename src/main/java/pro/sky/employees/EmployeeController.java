package pro.sky.employees;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee addNewEmployee(@RequestParam(required = false, name = "firstName") String firstName,
                                   @RequestParam(required = false, name = "lastName") String lastName) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException {
        return employeeService.addNewEmployee(firstName, lastName);
    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam(required = false, name = "firstName") String firstName,
                                   @RequestParam(required = false, name = "lastName") String lastName) throws EmployeeNotFoundException {
        return employeeService.removeEmployee(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee findEmployee (@RequestParam(required = false, name = "firstName") String firstName,
                                  @RequestParam(required = false, name = "lastName") String lastName) throws EmployeeNotFoundException {
        return employeeService.findEmployee(firstName, lastName);
    }
    @GetMapping("/employees")
    public List<Employee> getEmployees (){
        return this.employeeService.getEmployees();
    }

}

