package fu.api.backend.controller;


import fu.api.backend.controller.request.CustomerCreationRequest;
import fu.api.backend.controller.request.LoginDTO;
import fu.api.backend.common.ResponseData;
import fu.api.backend.controller.response.CustomerResponse;
import fu.api.backend.entity.Customer;
import fu.api.backend.enums.ErrorCode;
import fu.api.backend.exceptions.AppException;
import fu.api.backend.service.CustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//đc coi là 1 restcontroller
@RestController
@CrossOrigin
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
@Tag(name = "CustomerController")
public class CustomerController {

    private final CustomerService customerService;

    //HTTP VERB GET, POST, PUT, DELELTE
    @GetMapping("/")
    public ResponseData<List<CustomerResponse>> fetchAllCustomer() {
        return ResponseData.<List<CustomerResponse>>builder()
                .code(200)
                .message("Get all customers success")
                .data(customerService.getAllCustomer())
                .build();
    }

    //API ADD
    @PostMapping(path = "/save")
    public ResponseData<CustomerResponse> saveCustomer(@RequestBody @Valid CustomerCreationRequest customerCreationRequest) {
        return ResponseData.<CustomerResponse>builder()
                .code(201)
                .message("Save customer success")
                .data(customerService.addCustomer(customerCreationRequest))
                .build();
    }

    //API LOGIN
    @GetMapping("/{customerID}")
    public ResponseData<CustomerResponse> getCustomer(@PathVariable int customerID) {
        return ResponseData.<CustomerResponse>builder()
                .code(200)
                .message("Get customer success")
                .data(customerService.getCustomerById(customerID))
                .build();
    }

    //API UPDATE
    @PutMapping("/{customerID}")
    public ResponseData<CustomerResponse> updateCustomer(@PathVariable int customerID, @RequestBody CustomerCreationRequest customerCreationRequest) {
        return ResponseData.<CustomerResponse>builder()
                .code(200)
                .message("Update customer success")
                .data(customerService.updateCustomer(customerID, customerCreationRequest))
                .build();
    }

    //API DELETE
    @DeleteMapping("/{customerID}")
    public ResponseData<Void> deleteCustomer(@PathVariable int customerID) {
        customerService.deleteCustomer(customerID);
        return ResponseData.<Void>builder()
                .code(204)
                .message("Delete customer success")
                .build();
    }

    //API LOGIN
    @PostMapping("/authenticate")
    public ResponseData<CustomerResponse> authenticateCustomer(@RequestBody LoginDTO loginDTO) {
        return ResponseData.<CustomerResponse>builder()
                .code(200)
                .message("Authenticate customer success")
                .data(customerService.loginCustomer(loginDTO))
                .build();
    }
}
