package steammachinist.vacationpaycalculator.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
public class VacationPayCalculatorService {
    public BigDecimal calculatePayForDaysNumber(BigDecimal monthlySalary, int daysNumber) {
        BigDecimal pay = monthlySalary.setScale(2, RoundingMode.HALF_UP);
        return pay;
    }

    public BigDecimal calculatePayForDates(BigDecimal monthlySalary, LocalDate startDate, LocalDate endDate) {
        BigDecimal pay = monthlySalary.setScale(2, RoundingMode.HALF_UP);
        return pay;
    }
}
