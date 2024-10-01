package com.ninjaone.dundie_awards;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninjaone.dundie_awards.dto.EmployeeDTO;
import com.ninjaone.dundie_awards.dto.OrganizationDTO;
import com.ninjaone.dundie_awards.repository.ActivityRepository;
import com.ninjaone.dundie_awards.repository.EmployeeRepository;
import com.ninjaone.dundie_awards.repository.OrganizationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DundieAwardsApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private AwardsCache awardsCache;

    @Autowired
    private MessageBroker messageBroker;

    @BeforeEach
    void loadData() {
        DataLoader dataLoader = new DataLoader(employeeRepository, organizationRepository, awardsCache, activityRepository, messageBroker);
        dataLoader.run();
    }

    @Test
    public void getAllEmployeesAPI() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/employees")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(7)));
    }

    @Test
    public void getEmployeeByIdAPI() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/employees/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", is("John")));
    }

    @Test
    public void postCreateEmployeeAPI() throws Exception {
        OrganizationDTO organizationPikashu = new OrganizationDTO(1L, "Pikashu");
        EmployeeDTO employeeDTO = new EmployeeDTO("John8", "Doe8", organizationPikashu);

        ResultActions result = mvc.perform(post("/api/v1/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employeeDTO)));

        result.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/api/v1/employees/8"));
    }

    @Test
    public void putUpdateEmployeeAPI() throws Exception {
        OrganizationDTO organizationPikashu = new OrganizationDTO(1L, "Pikashu");
        EmployeeDTO employeeDTO = new EmployeeDTO("JohnUpd", "DoeUpd", organizationPikashu);

        ResultActions result = mvc.perform(put("/api/v1/employees/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employeeDTO)));

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", is("JohnUpd")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", is("DoeUpd")));
    }

    @Test
    public void deleteEmployeeAPI() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/employees/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.deleted").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.deleted", is(Boolean.TRUE)));
    }

}
