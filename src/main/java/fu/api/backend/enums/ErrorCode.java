package fu.api.backend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NOT_NULL(1, "Properties can not be null"),
    NOT_EMPTY(2, "Properties can not be empty"),
    INVALID_KEY(3, "Invalid key"),
    ;
    private final int code;
    private final String message;
}
