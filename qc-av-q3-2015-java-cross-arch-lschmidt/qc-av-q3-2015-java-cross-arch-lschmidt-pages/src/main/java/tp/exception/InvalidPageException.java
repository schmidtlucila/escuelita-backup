package tp.exception;

import java.text.MessageFormat;

public class InvalidPageException
    extends RuntimeException {
    private static final long serialVersionUID = -7782913425017164956L;
    private final String currentUrl;
    private final String expectedPage;

    public InvalidPageException(String expectedPage, String currentUrl) {
        this.expectedPage = expectedPage;
        this.currentUrl = currentUrl;
    }

    public String currentUrl() {
        return this.currentUrl;
    }

    @Override
    public String getMessage() {
        return MessageFormat.format("Current url: {0} - Expected page {1}.", this.currentUrl, this.expectedPage);
    }

}
