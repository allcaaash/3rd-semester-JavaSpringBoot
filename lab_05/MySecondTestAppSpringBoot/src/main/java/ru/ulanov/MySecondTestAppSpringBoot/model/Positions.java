package ru.ulanov.MySecondTestAppSpringBoot.model;

import lombok.Getter;

public enum Positions {
    DEV(2.2, false),
    HR(1.2, false),
    TL(2.6, true),
    PM(2.8, true),
    QA(2.0, false),
    SM(2.5, true);


    @Getter
    private final double positionCoefficient;
    @Getter
    private final boolean isManager;

    Positions(double positionCoefficient, boolean isManager) {
        this.positionCoefficient = positionCoefficient;
        this.isManager = isManager;
    }

}
