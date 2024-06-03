package pro.sky.employees;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {
    public String validateCheckName(String firstName){
        if(!StringUtils.isAlpha(firstName)){
            throw new ValidationFailedException();
        }
        return StringUtils.capitalize(firstName.toLowerCase());
    }

}
