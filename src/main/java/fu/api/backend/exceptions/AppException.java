package fu.api.backend.exceptions;

import fu.api.backend.enums.ErrorCode;
import lombok.*;

@Getter
public class AppException extends RuntimeException {

    private final ErrorCode errorCode;

    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
