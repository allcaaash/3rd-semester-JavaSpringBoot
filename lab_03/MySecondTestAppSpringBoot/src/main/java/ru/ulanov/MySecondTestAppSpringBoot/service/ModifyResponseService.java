package ru.ulanov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.ulanov.MySecondTestAppSpringBoot.model.Response;

@Service
public interface ModifyResponseService {
    Response modify(Response response);
}
