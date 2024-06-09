package pro.sky.employees.Service;

import pro.sky.employees.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee employeeWithMaxSalary(int departmentId);

    Employee employeeWithMinSalary(int departmentId);

    Collection<Employee> findAllByDepartmentId(int departmentId);


    Map<Integer, List<Employee>> findAllByDepartmentId();

}
