package fu.api.backend.controller.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CustomerResponse {

    private String userName;

    private String email;

    private String fullName;

    private String phone_number;

    private String address;

}
