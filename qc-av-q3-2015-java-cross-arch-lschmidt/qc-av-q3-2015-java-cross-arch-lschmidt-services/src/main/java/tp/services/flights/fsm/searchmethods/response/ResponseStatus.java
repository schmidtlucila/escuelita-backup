package tp.services.flights.fsm.searchmethods.response;

import java.util.List;

public class ResponseStatus {

    private String code;
    private List<String> allMessages;

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<String> getAllMessages() {
        return this.allMessages;
    }

    public void setAllMessages(List<String> allMessages) {
        this.allMessages = allMessages;
    }

}
