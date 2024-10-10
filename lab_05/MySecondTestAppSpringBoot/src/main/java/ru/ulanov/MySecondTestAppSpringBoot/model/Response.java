package ru.ulanov.MySecondTestAppSpringBoot.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    /***
     * Уникальный идентификатор сообщения
     */
    private String uid;
    /***
     * Уникальный идентификатор операции
     */
    private String operationUid;
    /***
     * Время создание сообщения
     */
    private String systemTime;
    /***
     * Код ответа
     */
    private Codes code;
    /***
     * Ежегодный бонус
     */
    private Double annualBonus;
    /***
     * Код ошибки
     */
    private ErrorCodes errorCode;
    /***
     * Сообщение ошибки
     */
    private ErrorMessages errorMessage;

    }
