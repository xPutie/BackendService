package fu.api.backend.exceptions;

import fu.api.backend.common.ResponseData;
import fu.api.backend.enums.ErrorCode;
import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class GlobalHandlerException {

    private final String MIN_ATTRIBUTE = "min";

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ResponseData<Object>> handleAppException(AppException e) {
        ErrorCode errorCode = e.getErrorCode();
        return ResponseEntity.ok(ResponseData.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseData> handleValidation(MethodArgumentNotValidException exception) {
        FieldError fieldError = Objects.requireNonNull(exception.getFieldError());
        String errorCodeKey = fieldError.getCode(); // Lấy mã lỗi, ví dụ: "NotNull", "Min", ...

        ErrorCode errorCode;
        switch (errorCodeKey) {
            case "NotNull":
                errorCode = ErrorCode.NOT_NULL;
                break;
            case "NotEmpty":
                errorCode = ErrorCode.NOT_EMPTY;
                break;
            case "Min":
                errorCode = ErrorCode.MIN_VALUE;
                break;
            default:
                errorCode = ErrorCode.INVALID_KEY;
                break;
        }


        Map<String, Object> attributes = null;
        try {
            var constraintViolation = exception.getBindingResult().getAllErrors().get(0).unwrap(ConstraintViolation.class);
            attributes = constraintViolation.getConstraintDescriptor().getAttributes();
            log.info(attributes.toString());
        } catch (Exception e) {
            log.error("[handleValidation] Error: {}", e.getMessage());
        }

        log.info("[handleValidation] Code: {}", errorCode.getCode());
        log.info("[handleValidation] Message: {}", errorCode.getMessage());

        return ResponseEntity.ok(ResponseData.builder()
                .code(errorCode.getCode())
                .message(Objects.nonNull(attributes) ?
                        mapAttribute(errorCode.getMessage(), attributes) : errorCode.getMessage())
                .build());
    }


    private String mapAttribute(String message, Map<String, Object> attributes) {
        if (attributes.containsKey(MIN_ATTRIBUTE)) {
            String minValue = String.valueOf(attributes.get(MIN_ATTRIBUTE));
            return message.replace("{" + MIN_ATTRIBUTE + "}", minValue);
        }
        // Xử lý các thuộc tính khác nếu có
        return message;
    }

}
