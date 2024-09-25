package fu.api.backend.repository;


import fu.api.backend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    Customer findByUserNameAndPassword(String userName, String password);

    Optional<Customer> findByUserName(String userName);
}
