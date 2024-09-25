package fu.api.backend.service.impl;

import fu.api.backend.controller.request.CustomerDTO;
import fu.api.backend.controller.request.LoginDTO;
import fu.api.backend.entity.Customer;
import fu.api.backend.enums.ErrorCode;
import fu.api.backend.exceptions.AppException;
import fu.api.backend.repository.CustomerRepo;
import fu.api.backend.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerIMPL implements CustomerService {

    private final CustomerRepo customerRepo;

    @Override
    public Customer addCustomer(CustomerDTO customerDTO) {

        Customer customer = Customer.builder()
                .userName(customerDTO.getUserName())
                .email(customerDTO.getEmail())
                .password(customerDTO.getPassword())
                .address(customerDTO.getAddress())
                .fullName(customerDTO.getFullName())
                .phoneNumber(customerDTO.getPhone_number())
                .loyaltyPoints(customerDTO.getLoyalty_points())
                .createdAt(new Date())
                .build();
        if(customer.getUserName() == null){
            throw new AppException(ErrorCode.NOT_NULL);
        }

        return customerRepo.save(customer);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepo.findAll();
    }

    @Override
    public Customer insertCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public Customer updateCustomer(int customerID, CustomerDTO customerDTO) {
        Customer customer = customerRepo.findById(customerID).orElseThrow(() -> new RuntimeException("Not found customer with id: " + customerID));
        if (customer != null) {
            customer.setUserName(customerDTO.getUserName());
            customer.setPassword(customerDTO.getPassword());
            customer.setEmail(customerDTO.getEmail());
            customer.setFullName(customerDTO.getFullName());
            customer.setPhoneNumber(customerDTO.getPhone_number());
            customer.setAddress(customerDTO.getAddress());
            customer.setLoyaltyPoints(customerDTO.getLoyalty_points());
            customer.setCreatedAt(new Date());
        }
        return customerRepo.save(customer);
    }

    @Override
    public void deleteCustomer(int customerID) {
        customerRepo.deleteById(customerID);
    }

    @Override
    public Customer getCustomerById(int customerID) {
        return customerRepo.findById(customerID).orElseThrow(() -> new RuntimeException("Not found customer with ID: " + customerID));
    }

    @Override
    public Customer loginCustomer(LoginDTO loginDTO) {
        Customer customer = customerRepo.findByUserNameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());

        if (customer == null) {
            throw new RuntimeException("Not found customer with user name: " + loginDTO.getUsername());
        }

        return customer;
    }
}
