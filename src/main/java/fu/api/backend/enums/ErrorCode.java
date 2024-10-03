package fu.api.backend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum ErrorCode {
    NOT_NULL(1, "Properties can not be null"),
    NOT_EMPTY(2, "Properties can not be empty"),
    INVALID_KEY(3, "Invalid key"),
    MIN_VALUE(4, "Value must be greater than or equal to {min}"),
    // ... các mã lỗi khác
    ;

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    // Getters
}

