package fu.api.backend.service;

import fu.api.backend.controller.request.CustomerCreationRequest;
import fu.api.backend.controller.request.LoginDTO;
import fu.api.backend.controller.response.CustomerResponse;
import fu.api.backend.entity.Customer;

import java.util.List;


public interface CustomerService {

    CustomerResponse addCustomer(CustomerCreationRequest customerCreationRequest);

    List<CustomerResponse> getAllCustomer();

    CustomerResponse updateCustomer(int customerID, CustomerCreationRequest customer);

    void deleteCustomer(int customerID);

    CustomerResponse getCustomerById(int customerID);

    CustomerResponse loginCustomer(LoginDTO loginDTO);
}
