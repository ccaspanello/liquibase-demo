package io.javamonkey.liqubase.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javamonkey.liqubase.demo.dal.entity.AddressEntity;
import io.javamonkey.liqubase.demo.dal.entity.CustomerEntity;
import io.javamonkey.liqubase.demo.dal.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootApplication
public class LiquibaseDemoMain implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(LiquibaseDemoMain.class, args);
    }

    @Autowired
    private CustomerRepository userRepo;

    public void run(String... args) throws Exception {

        if (userRepo.count() == 0) {
            loadSampleCustomers(1000);
        }

        Iterable<CustomerEntity> customers = userRepo.findAll();
        for (CustomerEntity customer : customers) {
            log.info("- {} - {}", customer.getId(), customer.getUsername());
        }

        log.info("=========================");
        long id = 1;
        log.info("Updating User: {}", id);
        // Find Customer and add new fields / address
        CustomerEntity customer = userRepo.findById(id).get();
        customer.setFirstName("myFirstName");
        customer.setFirstName("myLastName");

        if(customer.getAddress() == null){
            AddressEntity address = new AddressEntity();
            address.setAddress1("myStreetAddress1");
            address.setCity("New York");
            address.setState("NY");
            address.setZip(12345);
            customer.setAddress(address);
        }

        userRepo.save(customer);

        CustomerEntity updatedCustomer = userRepo.findById(id).get();
        ObjectMapper mapper = new ObjectMapper();
        log.info("Updated User: {}", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(updatedCustomer));
    }

    private void loadSampleCustomers(int count) {
        List<CustomerEntity> customers = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            CustomerEntity customer = new CustomerEntity();
            customer.setUsername("myUsername" + i + "@gmail.com");
            customer.setPassword("myPassword" + i);
            customers.add(customer);
        }
        userRepo.saveAll(customers);
    }

}
