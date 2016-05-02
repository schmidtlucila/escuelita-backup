package tp.databases.builderconnectdatabase;

public enum CruiseSiteDatabase {
    CRUISE_CHAS("cruise_chas");

    private String code;

    private CruiseSiteDatabase(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

}
