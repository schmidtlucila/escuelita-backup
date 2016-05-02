package tp.datagenerator.model;

public class Region {
    private String code;
    private String description;

    public Region() {
        super();
    }

    public Region(String code, String description) {
        super();
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Region [code=" + this.code + ", description=" + this.description + "]";
    }
}
