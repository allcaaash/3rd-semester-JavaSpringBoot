package ru.ulanov.MySecondTestAppSpringBoot.service;

import org.junit.jupiter.api.Test;
import ru.ulanov.MySecondTestAppSpringBoot.model.Positions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnnualBonusServiceImplTest {

    @Test
    void calculate() {
        // given
        Positions positions = Positions.HR;
        double bonus = 2.0;
        int workDays  = 243;
        double salary = 100000.00;

        // when
        double result = new AnnualBonusServiceImpl().calculate(positions,salary, bonus, workDays);

        // then
        double expected = 360493.8271604938;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void calculateQuarterlyPremium() {
        // given
        Positions positions = Positions.HR;
        double bonus = 2.0;
        int workDays  = 58;
        double salary = 100000.00;

        // when
        double result = new AnnualBonusServiceImpl().calculateQuarterlyPremium(positions,salary, bonus, workDays);

        // then
        double expected = 0.0;
        assertThat(result).isEqualTo(expected);

        // given
        positions = Positions.PM;
        salary = 160000.00;

        // when
        result = new AnnualBonusServiceImpl().calculateQuarterlyPremium(positions,salary, bonus, workDays);

        // then
        expected = 46344.8275862069;
        assertThat(result).isEqualTo(expected);
    }
}