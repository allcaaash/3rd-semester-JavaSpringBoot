package ru.ulanov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.ulanov.MySecondTestAppSpringBoot.model.Positions;

import java.time.Year;

@Service
public class AnnualBonusServiceImpl implements AnnualBonusService {
    @Override
    public double calculate(Positions positions, double salary, double bonus, int workDays) {
        return salary * bonus * Year.now().length() * positions.getPositionCoefficient() / workDays;
    }

    @Override
    public double calculateQuarterlyPremium(Positions positions, double salary, double bonus, int workDays) {
        if (!positions.isManager())
            return 0.0;

        return salary * bonus * 3 * positions.getPositionCoefficient() / workDays;
    }
}
