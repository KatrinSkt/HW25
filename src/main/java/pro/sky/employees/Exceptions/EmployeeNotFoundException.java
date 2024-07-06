package pro.sky.employees.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException {


    public EmployeeNotFoundException() {

    }

    public String getMessage() {
        return "Сотрудник с именем '%s' не найден";
    }


}
