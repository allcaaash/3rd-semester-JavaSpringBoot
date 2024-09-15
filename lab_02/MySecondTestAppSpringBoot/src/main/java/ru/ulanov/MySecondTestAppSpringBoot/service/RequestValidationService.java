package ru.ulanov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.ulanov.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.ulanov.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import ru.ulanov.MySecondTestAppSpringBoot.model.Request;

@Service
public class RequestValidationService implements ValidationService {
    @Override
    public void isValid(BindingResult bindingResult) throws ValidationFailedException {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult.getFieldError().toString());
        }
    }
    @Override
    public void isCorrectUid(Request request) throws UnsupportedCodeException {
        if (request.getUid().equals("123")) {
            throw new UnsupportedCodeException("UnsupportedCodeException");
        }
    }
}
