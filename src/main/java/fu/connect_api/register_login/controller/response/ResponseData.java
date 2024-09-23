package fu.connect_api.register_login.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData<T> {

    private int code;
    private String message;
    private T data;

}
