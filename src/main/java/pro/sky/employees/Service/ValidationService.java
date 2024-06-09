package pro.sky.employees.Service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.employees.Exceptions.ValidationFailedException;

@Service
public class ValidationService {
    public String validateCheckName(String firstName){
        if(!StringUtils.isAlpha(firstName)){
            throw new ValidationFailedException();
        }
        return StringUtils.capitalize(firstName.toLowerCase());
    }

}
