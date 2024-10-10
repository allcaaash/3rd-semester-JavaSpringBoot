package ru.ulanov.MySecondTestAppSpringBoot.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    /***
     * Уникальный идентификатор сообщения
     */
    @NotBlank(message = "UID не может быть пустым")
    @Size(max = 32)
    private String uid;
    /***
     * Уникальный идентификатор операции
     */
    @Size(max = 32)
    private String operationUid;
    /***
     * Имя системы отправителя
     */
    private Systems systemName;
    /***
     * Время создания сообщения
     */
    @NotBlank
    private String systemTime;
    /***
     * Источник
     */
    private String source;
    /***
     * Наименование ресурса
     */
    private Positions position;
    /***
     * Заработок
     */
    private Double salary;
    /***
     * Бонус
     */
    private Double bonus;
    /***
     * Отработанные дни
     */
    private Integer workDays;
    /***
     * Уникальный идентификатор коммуникации
     */
    @Min(value = 1)
    @Max(value = 100000)
    private int communicationId;
    /***
     * Уникальный идентификатор шаблона
     */
    private int templateId;
    /***
     * Код продукта
     */
    private int productCode;
    /***
     * Смс код
     */
    private int smsCode;

    @Override
    public String toString() {
        return "{" +
                "uid='" + uid + '\'' +
                ", operationUid='" + operationUid + '\'' +
                ", systemName='" + systemName + '\'' +
                ", systemTime='" + systemTime + '\'' +
                ", source='" + source + '\'' +
                ", position='" + position + '\'' +
                ", salary='" + salary + '\'' +
                ", bonus='" + bonus + '\'' +
                ", workDays='" + workDays + '\'' +
                ", communicationId='" + communicationId + '\'' +
                ", templateId='" + templateId + '\'' +
                ", productCode='" + productCode + '\'' +
                ", smsCode='" + smsCode + '\'' +
                "}";
    }

}
