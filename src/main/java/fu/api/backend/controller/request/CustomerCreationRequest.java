package fu.api.backend.controller.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCreationRequest {

    @NotNull(message = "NOT_NULL")
    @NotEmpty
    private String userName;
    private String password;
    private String email;
    private String fullName;
    private String phone_number;
    private String address;
    private int loyalty_points;

}
