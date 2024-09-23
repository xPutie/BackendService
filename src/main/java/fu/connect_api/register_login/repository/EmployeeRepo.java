package fu.connect_api.register_login.repository;


import fu.connect_api.register_login.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

    Employee findByUserNameAndPassword(String userName, String password);

}
