package opsy.validators;

import opsy.data.UsersRepository;
import opsy.entities.UserDTO;
import opsy.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class UserDTOValidator implements Validator {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        System.out.println("asdasdasdasdad");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "empty.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "empty.pass");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPass", "empty.conf.pass");
        UserDTO userDTO = (UserDTO) o;
        Users users = usersRepository.findByLogin(userDTO.getUsername());
        if(!userDTO.getPassword().equals(userDTO.getConfirmPass())){
            errors.rejectValue("password", "passwords.not.equal");
        }
        if(users != null){
            errors.rejectValue("username", "login.taken");
        }
    }
}
