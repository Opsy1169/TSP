package opsy.validators;

import opsy.entities.Articles;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class ArticleValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(Articles.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "articleBody", "empty.articlebody");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "empty.title");

    }
}
