package tp.config;

public enum ProjectNameHeaders {
    PROJECT_AUTOMATION_SERVICES_XUOW("X-UOW"), PROJECT_AUTOMATION_GUI_XUOW("X-UOW-CUSTOM"), X_VERSION_OVERRIDE(
                    "X-Version-Override");

    private String code;

    private ProjectNameHeaders(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

}
