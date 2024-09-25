package fu.api.backend.controller;


import fu.api.backend.controller.request.CustomerDTO;
import fu.api.backend.controller.request.LoginDTO;
import fu.api.backend.controller.response.ResponseData;
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
    public ResponseData<List<Customer>> fetchAllCustomer() {
        return ResponseData.<List<Customer>>builder()
                .code(200)
                .message("Get all customers success")
                .data(customerService.getAllCustomer())
                .build();
    }

    //API ADD
    @PostMapping(path = "/save")
    public ResponseEntity<Customer> saveCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        Customer customer = customerService.addCustomer(customerDTO);
        if (customer == null) {
            throw new AppException(ErrorCode.NOT_NULL);
        }
        return ResponseEntity.ok(customer);
    }

    //API LOGIN
    @GetMapping("/{customerID}")
    public ResponseEntity<Customer> getCustomer(@PathVariable int customerID){
        return ResponseEntity.ok(customerService.getCustomerById(customerID));
    }

    //API UPDATE
    @PutMapping("/{customerID}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable int customerID, @RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerService.updateCustomer(customerID, customerDTO));
    }

    //API DELETE
    @DeleteMapping("/{customerID}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int customerID) {
        customerService.deleteCustomer(customerID);
        return ResponseEntity.noContent().build();
    }

    //API LOGIN
    @PostMapping("/authenticate")
    public ResponseEntity<Customer> authenticateCustomer(@RequestBody LoginDTO loginDTO){
        return ResponseEntity.ok(customerService.loginCustomer(loginDTO));
    }
}
