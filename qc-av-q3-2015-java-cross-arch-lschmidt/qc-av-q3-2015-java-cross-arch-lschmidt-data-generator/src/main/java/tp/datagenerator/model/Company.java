package tp.datagenerator.model;

public class Company {
    private Long id;
    private String code;
    private String name;
    private Long operatorId;

    public Company() {
        super();
    }

    public Company(Long id, String code, String name, Long operatorId) {
        super();
        this.id = id;
        this.code = code;
        this.name = name;
        this.operatorId = operatorId;
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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOperatorId() {
        return this.operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }
}
