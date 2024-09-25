package fu.api.backend.service;

import fu.api.backend.controller.request.CustomerDTO;
import fu.api.backend.controller.request.LoginDTO;
import fu.api.backend.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CustomerService {

    Customer addCustomer(CustomerDTO customerDTO);

    List<Customer> getAllCustomer();

    Customer insertCustomer(Customer customer);

    Customer updateCustomer(int customerID, CustomerDTO customer);

    void deleteCustomer(int customerID);

    Customer getCustomerById(int customerID);

    Customer loginCustomer(LoginDTO loginDTO);
}
