package com.ninjaone.dundie_awards;

import com.ninjaone.dundie_awards.model.Activity;
import com.ninjaone.dundie_awards.model.Employee;
import com.ninjaone.dundie_awards.model.Organization;
import com.ninjaone.dundie_awards.repository.ActivityRepository;
import com.ninjaone.dundie_awards.repository.EmployeeRepository;
import com.ninjaone.dundie_awards.repository.OrganizationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;
    private final OrganizationRepository organizationRepository;
    private final AwardsCache awardsCache;
    private final ActivityRepository activityRepository;
    private MessageBroker messageBroker;

    public DataLoader(EmployeeRepository employeeRepository, OrganizationRepository organizationRepository,
                      AwardsCache awardsCache, ActivityRepository activityRepository, MessageBroker messageBroker) {
        this.awardsCache = awardsCache;
        this.employeeRepository = employeeRepository;
        this.organizationRepository = organizationRepository;
        this.activityRepository = activityRepository;
        this.messageBroker = messageBroker;
    }

    @Override
    public void run(String... args) {
        // uncomment to reseed data
        // employeeRepository.deleteAll();
        // organizationRepository.deleteAll();

        if (employeeRepository.count() == 0) {
            Organization organizationPikashu = new Organization("Pikashu");
            organizationRepository.save(organizationPikashu);

            Employee employee = new Employee("John", "Doe", organizationPikashu);
            employee.setDundieAwards(2);
            employeeRepository.save(employee);
            employeeRepository.save(new Employee("Jane", "Smith", organizationPikashu));
            employeeRepository.save(new Employee("Creed", "Braton", organizationPikashu));

            Organization organizationSquanchy = new Organization("Squanchy");
            organizationRepository.save(organizationSquanchy);

            employeeRepository.save(new Employee("Michael", "Scott", organizationSquanchy));
            employeeRepository.save(new Employee("Dwight", "Schrute", organizationSquanchy));
            employeeRepository.save(new Employee("Jim", "Halpert", organizationSquanchy));
            employeeRepository.save(new Employee("Pam", "Beesley", organizationSquanchy));
        }

        if (activityRepository.count() == 0) {
            activityRepository.save(new Activity(LocalDateTime.now(), "event 1"));
            activityRepository.save(new Activity(LocalDateTime.now(), "event 2"));
            activityRepository.save(new Activity(LocalDateTime.now(), "event 3"));
        }

        if (messageBroker.getMessages().isEmpty()) {
            messageBroker.sendMessage(new Activity(LocalDateTime.now(), "event 1"));
            messageBroker.sendMessage(new Activity(LocalDateTime.now(), "event 2"));
            messageBroker.sendMessage(new Activity(LocalDateTime.now(), "event 3"));
        }
    }
}
