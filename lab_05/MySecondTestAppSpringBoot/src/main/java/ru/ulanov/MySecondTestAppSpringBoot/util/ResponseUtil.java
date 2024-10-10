package ru.ulanov.MySecondTestAppSpringBoot.util;

import ru.ulanov.MySecondTestAppSpringBoot.model.*;

import java.util.Date;

public class ResponseUtil {
    public static Response createSuccessResponse(Request request) {
        return Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();
    }
}
