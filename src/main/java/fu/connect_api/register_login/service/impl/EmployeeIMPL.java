package fu.connect_api.register_login.service.impl;

import fu.connect_api.register_login.controller.request.EmployeeDTO;
import fu.connect_api.register_login.controller.request.LoginDTO;
import fu.connect_api.register_login.entity.Employee;
import fu.connect_api.register_login.repository.EmployeeRepo;
import fu.connect_api.register_login.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeIMPL implements EmployeeService {

    private final EmployeeRepo employeeRepo;

    // private final PasswordEncoder passwordEncoder;

    @Override
    public Employee addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = Employee.builder()
                .userName(employeeDTO.getUserName())
                .email(employeeDTO.getEmail())
                .password(employeeDTO.getPassword())
                .address(employeeDTO.getAddress())
                .fullName(employeeDTO.getFullName())
                .phoneNumber(employeeDTO.getPhone_number())
                .loyaltyPoints(employeeDTO.getLoyalty_points())
                .createdAt(new Date())
                .build();
        return employeeRepo.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee insertEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public Employee updateEmployee(int employeeId, EmployeeDTO employee) {
        Employee e = employeeRepo.findById(employeeId).orElseThrow(() -> new RuntimeException("Not found employee with id: " + employeeId));
        if (e != null) {
            e.setUserName(employee.getUserName());
            e.setPassword(employee.getPassword());
            e.setEmail(employee.getEmail());
            e.setFullName(employee.getFullName());
            e.setPhoneNumber(employee.getPhone_number());
            e.setAddress(employee.getAddress());
            e.setLoyaltyPoints(employee.getLoyalty_points());
            e.setCreatedAt(new Date());
        }
        return employeeRepo.save(e);
    }

    @Override
    public void deleteEmployee(int Employee) {
        employeeRepo.deleteById(Employee);
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        return employeeRepo.findById(employeeId).orElseThrow(() -> new RuntimeException("Not found employee with ID: " + employeeId));
    }

    @Override
    public Employee loginEmployee(LoginDTO loginDTO) {
        Employee userName = employeeRepo.findByUserNameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());

        if (userName == null) {
            throw new RuntimeException("Not found employee with user name: " + loginDTO.getUsername());
        }

        return userName;
    }
}
