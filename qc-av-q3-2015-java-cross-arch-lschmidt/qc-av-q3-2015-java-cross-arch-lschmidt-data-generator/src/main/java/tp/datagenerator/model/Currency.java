package tp.datagenerator.model;

public class Currency {
    private Long id;
    private String code;
    private String description;

    public Currency() {
        super();
    }

    public Currency(Long id, String code, String description) {
        super();
        this.id = id;
        this.code = code;
        this.description = description;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
