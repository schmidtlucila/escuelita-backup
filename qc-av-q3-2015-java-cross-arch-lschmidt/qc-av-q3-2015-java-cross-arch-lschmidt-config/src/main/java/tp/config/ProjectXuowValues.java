package tp.config;

public enum ProjectXuowValues {
    PROJECT_AUTOMATION_SERVICES("project-automation-tests-services"), PROJECT_AUTOMATION_GUI("project-automation-tests-gui");

    private String code;

    private ProjectXuowValues(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

}
