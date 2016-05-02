package tp.config;

public enum ApplicationCode {

    API("api"), CRUISE("cruise"), FLIGHTS("flights");

    private String code;

    private ApplicationCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

}
