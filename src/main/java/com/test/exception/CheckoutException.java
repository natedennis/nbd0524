package com.test.exception;

public class CheckoutException extends Throwable {
    public enum  ERROR_CODE {
        RENTAL_DAY_COUNT("Rental day count of {} is not 1 or greater"),
        DISCOUNT_RATE("Discount percentage of {} is not within the range 0-100"),
        TOOL_CODE("Tool code of {} is not valid");

        private final String message;

        ERROR_CODE(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
    public ERROR_CODE errorCode;
    public CheckoutException(String message) {
        super(message);
    }

    public CheckoutException(Throwable throwable) {
        super(throwable);
    }

    public CheckoutException(ERROR_CODE errorCode, String value) {
        super(String.format("%s: %s", errorCode.name(), errorCode.getMessage().replace("{}", value)));
        this.errorCode = errorCode;
    }

    public CheckoutException(ERROR_CODE errorCode, String value, Throwable e) {
        super(String.format("%s: %s", errorCode.name(), errorCode.getMessage().replace("{}", value)), e);
        this.errorCode = errorCode;
    }

    public ERROR_CODE getErrorCode() {
        return errorCode;
    }

}
