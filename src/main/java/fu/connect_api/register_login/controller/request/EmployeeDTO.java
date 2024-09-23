package fu.connect_api.register_login.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private String userName;
    private String password;
    private String email;
    private String fullName;
    private int phone_number;
    private String address;
    private int loyalty_points;

}
