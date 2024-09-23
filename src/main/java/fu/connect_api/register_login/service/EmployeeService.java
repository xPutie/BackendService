package fu.connect_api.register_login.service;

import fu.connect_api.register_login.controller.request.EmployeeDTO;
import fu.connect_api.register_login.controller.request.LoginDTO;
import fu.connect_api.register_login.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    Employee addEmployee(EmployeeDTO employeeDTO);

    public List<Employee> getAllEmployees();

    public Employee insertEmployee(Employee employee);

    public Employee updateEmployee(int employeeId, EmployeeDTO employee);

    public void deleteEmployee(int Employee);

    public Employee getEmployeeById(int employeeId);

    public Employee loginEmployee(LoginDTO loginDTO);
}
