package fu.connect_api.register_login.controller;


import fu.connect_api.register_login.controller.request.EmployeeDTO;
import fu.connect_api.register_login.controller.request.LoginDTO;
import fu.connect_api.register_login.controller.response.ResponseData;
import fu.connect_api.register_login.entity.Employee;
import fu.connect_api.register_login.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//đc coi là 1 restcontroller
@RestController
@CrossOrigin
@RequestMapping("api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    //HTTP VERB GET, POST, PUT, DELELTE
    @GetMapping("/")
    public ResponseData<List<Employee>> fetchAllEmployees() {
        return ResponseData.<List<Employee>>builder()
                .code(200)
                .message("Get all users success")
                .data(employeeService.getAllEmployees())
                .build();
    }

    @PostMapping(path = "/save")
    public ResponseEntity<Employee> saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeService.addEmployee(employeeDTO));
    }

    @GetMapping("/{employeeID}")
    public ResponseEntity<Employee> getEmployee(@PathVariable int employeeID){
        return ResponseEntity.ok(employeeService.getEmployeeById(employeeID));
    }

    @PutMapping("/{employeeID}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int employeeID, @RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeService.updateEmployee(employeeID, employeeDTO));
    }

    @DeleteMapping("/{employeeID}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int employeeID) {
        employeeService.deleteEmployee(employeeID);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Employee> loginEmployee(@RequestBody LoginDTO loginDTO){
        return ResponseEntity.ok(employeeService.loginEmployee(loginDTO));
    }
}
