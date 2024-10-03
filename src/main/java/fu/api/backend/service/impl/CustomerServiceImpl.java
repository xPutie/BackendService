package fu.api.backend.service.impl;

import fu.api.backend.controller.request.CustomerCreationRequest;
import fu.api.backend.controller.request.LoginDTO;
import fu.api.backend.controller.response.CustomerResponse;
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
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;

    @Override
    public CustomerResponse addCustomer(CustomerCreationRequest customerCreationRequest) {

        Customer customer = Customer.builder()
                .userName(customerCreationRequest.getUserName())
                .email(customerCreationRequest.getEmail())
                .password(customerCreationRequest.getPassword())
                .address(customerCreationRequest.getAddress())
                .fullName(customerCreationRequest.getFullName())
                .phoneNumber(customerCreationRequest.getPhone_number())
                .loyaltyPoints(customerCreationRequest.getLoyalty_points())
                .createdAt(new Date())
                .build();
        if (customer.getUserName() == null) {
            throw new AppException(ErrorCode.NOT_NULL);
        }

        customerRepo.save(customer);

        return CustomerResponse.builder()
                .userName(customer.getUserName())
                .email(customer.getEmail())
                .phone_number(customer.getPhoneNumber())
                .address(customer.getAddress())
                .fullName(customer.getFullName())
                .build();
    }

    @Override
    public List<CustomerResponse> getAllCustomer() {
        return customerRepo.findAll().stream()
                .map(customer -> CustomerResponse.builder()
                        .userName(customer.getUserName())
                        .email(customer.getEmail())
                        .phone_number(customer.getPhoneNumber())
                        .address(customer.getAddress())
                        .fullName(customer.getFullName())
                        .build()).toList();
    }


    @Override
    public CustomerResponse updateCustomer(int customerID, CustomerCreationRequest customerCreationRequest) {
        Customer customer = customerRepo.findById(customerID).orElseThrow(() -> new RuntimeException("Not found customer with id: " + customerID));

        if (customer != null) {
            customer.setUserName(customerCreationRequest.getUserName());
            customer.setPassword(customerCreationRequest.getPassword());
            customer.setEmail(customerCreationRequest.getEmail());
            customer.setFullName(customerCreationRequest.getFullName());
            customer.setPhoneNumber(customerCreationRequest.getPhone_number());
            customer.setAddress(customerCreationRequest.getAddress());
            customer.setLoyaltyPoints(customerCreationRequest.getLoyalty_points());
            customer.setCreatedAt(new Date());
            customerRepo.save(customer);
        }


        return CustomerResponse.builder()
                .userName(customer.getUserName())
                .email(customer.getEmail())
                .phone_number(customer.getPhoneNumber())
                .address(customer.getAddress())
                .fullName(customer.getFullName())
                .build();
    }

    @Override
    public void deleteCustomer(int customerID) {
        customerRepo.deleteById(customerID);
    }

    @Override
    public CustomerResponse getCustomerById(int customerID) {
        var customer = customerRepo.findById(customerID).orElseThrow(() -> new RuntimeException("Not found customer with id: " + customerID));
        return CustomerResponse.builder()
                .userName(customer.getUserName())
                .email(customer.getEmail())
                .phone_number(customer.getPhoneNumber())
                .address(customer.getAddress())
                .fullName(customer.getFullName())
                .build();
    }

    @Override
    public CustomerResponse loginCustomer(LoginDTO loginDTO) {
        Customer customer = customerRepo.findByUserNameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());

        if (customer == null) {
            throw new RuntimeException("Not found customer with user name: " + loginDTO.getUsername());
        }

        return CustomerResponse.builder()
                .userName(customer.getUserName())
                .email(customer.getEmail())
                .phone_number(customer.getPhoneNumber())
                .address(customer.getAddress())
                .fullName(customer.getFullName())
                .build();
    }
}
