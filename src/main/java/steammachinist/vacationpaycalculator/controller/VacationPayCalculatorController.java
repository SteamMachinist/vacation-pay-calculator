package steammachinist.vacationpaycalculator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import steammachinist.vacationpaycalculator.service.VacationPayCalculatorService;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping(value = "/calculate")
@RequiredArgsConstructor
public class VacationPayCalculatorController {
    private final VacationPayCalculatorService calculatorService;

    @GetMapping(params = {"monthlySalary", "daysNumber"})
    public BigDecimal calculatePayByDaysNumber(@RequestParam("monthlySalary") BigDecimal monthlySalary,
                                               @RequestParam("daysNumber") int daysNumber) {
        return calculatorService.calculatePayForDaysNumber(monthlySalary, daysNumber);
    }

    @GetMapping(params = {"monthlySalary", "startDate", "endDate"})
    public BigDecimal calculatePayByDates(@RequestParam("monthlySalary") BigDecimal monthlySalary,
                                          @RequestParam("startDate") LocalDate startDate,
                                          @RequestParam("endDate") LocalDate endDate) {
        return calculatorService.calculatePayForDates(monthlySalary, startDate, endDate);
    }
}
