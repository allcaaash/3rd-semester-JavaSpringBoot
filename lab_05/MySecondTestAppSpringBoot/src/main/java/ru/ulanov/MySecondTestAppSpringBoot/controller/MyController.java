package ru.ulanov.MySecondTestAppSpringBoot.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ulanov.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.ulanov.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import ru.ulanov.MySecondTestAppSpringBoot.model.*;
import ru.ulanov.MySecondTestAppSpringBoot.service.ModifyRequestService;
import ru.ulanov.MySecondTestAppSpringBoot.service.ModifyResponseService;
import ru.ulanov.MySecondTestAppSpringBoot.service.ValidationService;
import ru.ulanov.MySecondTestAppSpringBoot.util.DateTimeUtil;
import ru.ulanov.MySecondTestAppSpringBoot.util.ResponseUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RestController
public class MyController {

    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;
    private final ModifyRequestService modifyRequestService;

    @Autowired
    public MyController(ValidationService validationService,
                        @Qualifier("ModifySystemTimeResponseService")ModifyResponseService modifyResponseService,
                        @Qualifier("ModifySystemTimeRequestService") ModifyRequestService modifyRequestService) {
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
        this.modifyRequestService = modifyRequestService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request,
                                             BindingResult bindingResult) {

        log.info("request: {}", request);

        Response response = ResponseUtil.createSuccessResponse(request);

        log.info("response: {}", response);

        try {
            validationService.isCorrectUid(request);
            validationService.isValid(bindingResult);
        } catch (UnsupportedCodeException e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION);
            log.error("response: {}", response);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (ValidationFailedException e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION);
            log.error("bindingResult exception: {}", e.toString());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOWN);
            log.error("response: {}", response);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        modifyResponseService.modify(response);
        modifyRequestService.modify(request);

        log.info("response: {}", response);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
