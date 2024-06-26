package steammachinist.vacationpaycalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "build/generated-snippets")
public class VacationPayCalculatorMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .alwaysDo(document("{method-name}",
                        preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
                .build();
    }

    @Test
    public void calculatePayByDaysNumberTest() throws Exception {
        this.mockMvc.perform(get("/calculate")
                        .param("monthlySalary", "20")
                        .param("daysNumber", "10"))
                .andExpect(status().isOk())
                .andExpect(content().string("10.00"));
    }

    @Test
    public void calculatePayByDaysNumberNegativeDaysNumberTest() throws Exception {
        this.mockMvc.perform(get("/calculate")
                        .param("monthlySalary", "20")
                        .param("daysNumber", "-1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("[\"daysNumber must be greater than or equal to 0\"]"));
    }

    @Test
    public void calculatePayByDatesWeekendsTest() throws Exception {
        this.mockMvc.perform(get("/calculate")
                        .param("monthlySalary", "20")
                        .param("startDate", "2024-04-01")
                        .param("endDate", "2024-04-07"))
                .andExpect(status().isOk())
                .andExpect(content().string("5.00"));
    }

    @Test
    public void calculatePayByDatesHolidayTest() throws Exception {
        this.mockMvc.perform(get("/calculate")
                        .param("monthlySalary", "20")
                        .param("startDate", "2024-03-04")
                        .param("endDate", "2024-03-10"))
                .andExpect(status().isOk())
                .andExpect(content().string("4.00"));
    }

    @Test
    public void calculatePayByDatesNegativeMonthlySalaryTest() throws Exception {
        this.mockMvc.perform(get("/calculate")
                        .param("monthlySalary", "-1")
                        .param("startDate", "2024-03-04")
                        .param("endDate", "2024-03-10"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("[\"monthlySalary must be greater than or equal to 0\"]"));
    }

    @Test
    public void calculatePayByDatesStartGreaterThenEndTest() throws Exception {
        this.mockMvc.perform(get("/calculate")
                        .param("monthlySalary", "20")
                        .param("startDate", "2024-03-12")
                        .param("endDate", "2024-03-10"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Start date must be the same day or earlier than end date"));
    }
}

