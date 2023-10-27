package validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.softuni.model.entities.UserEntity;
import org.softuni.repository.UserRepository;

import java.util.Optional;

public class UniqueUserEmailValidator implements ConstraintValidator<UniqueUserEmail,String> {

    private final UserRepository userRepository;

public UniqueUserEmailValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

     return userRepository.findByEmail(value).isEmpty();
    }
}
