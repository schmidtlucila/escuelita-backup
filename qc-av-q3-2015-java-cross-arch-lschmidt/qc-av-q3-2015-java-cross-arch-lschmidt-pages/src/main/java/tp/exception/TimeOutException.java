package tp.exception;

import java.text.MessageFormat;

public class TimeOutException
    extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final String message;

    public TimeOutException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return MessageFormat.format("TimeoutException: {0}", this.message);
    }


}
