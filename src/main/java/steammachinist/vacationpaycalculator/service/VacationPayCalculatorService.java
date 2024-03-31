package steammachinist.vacationpaycalculator.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
public class VacationPayCalculatorService {
    private final int AVERAGE_WORK_DAYS_IN_MONTH = 20;

    public BigDecimal calculatePayForDaysNumber(BigDecimal monthlySalary, int daysNumber) {
        BigDecimal salary = monthlySalary.setScale(2, RoundingMode.HALF_UP);
        BigDecimal daySalary = salary.divide(BigDecimal.valueOf(AVERAGE_WORK_DAYS_IN_MONTH), RoundingMode.HALF_UP);
        return daySalary.multiply(BigDecimal.valueOf(daysNumber));
    }

    public BigDecimal calculatePayForDates(BigDecimal monthlySalary, LocalDate startDate, LocalDate endDate) {
        BigDecimal pay = monthlySalary.setScale(2, RoundingMode.HALF_UP);
        return pay;
    }
}
