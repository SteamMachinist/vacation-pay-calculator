package steammachinist.vacationpaycalculator.controller;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import steammachinist.vacationpaycalculator.service.VacationPayCalculatorService;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@Validated
@RequestMapping(value = "/calculate")
@RequiredArgsConstructor
public class VacationPayCalculatorController {
    private final VacationPayCalculatorService calculatorService;

    @GetMapping(params = {"monthlySalary", "daysNumber"})
    public BigDecimal calculatePayByDaysNumber(@RequestParam("monthlySalary") @Min(0) BigDecimal monthlySalary,
                                               @RequestParam("daysNumber") @Min(0) int daysNumber) {
        return calculatorService.calculatePayForDaysNumber(monthlySalary, daysNumber);
    }

    @GetMapping(params = {"monthlySalary", "startDate", "endDate"})
    public BigDecimal calculatePayByDates(@RequestParam("monthlySalary") @Min(0) BigDecimal monthlySalary,
                                          @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                          @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date must be the same day or earlier than end date");
        }
        return calculatorService.calculatePayForDates(monthlySalary, startDate, endDate);
    }
}
