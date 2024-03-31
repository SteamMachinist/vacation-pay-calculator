package steammachinist.vacationpaycalculator.service;

import de.focus_shift.jollyday.core.HolidayManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class VacationPayCalculatorService {
    private final int AVERAGE_WORK_DAYS_IN_MONTH = 20;
    private final HolidayManager holidayManager;

    private BigDecimal calculateDaySalary(BigDecimal monthlySalary) {
        BigDecimal salary = monthlySalary.setScale(2, RoundingMode.HALF_UP);
        return salary.divide(BigDecimal.valueOf(AVERAGE_WORK_DAYS_IN_MONTH), RoundingMode.HALF_UP);
    }

    public BigDecimal calculatePayForDaysNumber(BigDecimal monthlySalary, int daysNumber) {
        return calculateDaySalary(monthlySalary).multiply(BigDecimal.valueOf(daysNumber));
    }

    public BigDecimal calculatePayForDates(BigDecimal monthlySalary, LocalDate startDate, LocalDate endDate) {
        long businessDaysNumber = IntStream.rangeClosed(0, (int) (endDate.toEpochDay() - startDate.toEpochDay()))
                .mapToObj(startDate::plusDays)
                .filter(date ->
                        !date.getDayOfWeek().equals(java.time.DayOfWeek.SATURDAY) &&
                        !date.getDayOfWeek().equals(java.time.DayOfWeek.SUNDAY) &&
                        !holidayManager.isHoliday(date))
                .count();
        return calculateDaySalary(monthlySalary).multiply(BigDecimal.valueOf(businessDaysNumber));
    }
}
