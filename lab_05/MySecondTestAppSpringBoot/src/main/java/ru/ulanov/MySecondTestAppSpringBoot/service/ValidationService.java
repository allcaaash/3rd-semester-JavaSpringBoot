package ru.ulanov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.ulanov.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.ulanov.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import ru.ulanov.MySecondTestAppSpringBoot.model.Request;

@Service
public interface ValidationService {
    void isValid(BindingResult bindingResult) throws ValidationFailedException;
    void isCorrectUid(Request request) throws UnsupportedCodeException;
}
