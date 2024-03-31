package steammachinist.vacationpaycalculator.controller;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
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
                                          @RequestParam("startDate") LocalDate startDate,
                                          @RequestParam("endDate") LocalDate endDate) {
        return calculatorService.calculatePayForDates(monthlySalary, startDate, endDate);
    }
}
