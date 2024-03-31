package steammachinist.vacationpaycalculator.configuration;

import de.focus_shift.jollyday.core.HolidayCalendar;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.ManagerParameters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomConfiguration {
    @Bean
    public HolidayManager holidayManager() {
        return HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.RUSSIA));
    }
}
