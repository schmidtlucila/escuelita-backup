package tp.datagenerator.model;

public class OfficeCompanyData {
    private String office;
    private String company;

    public OfficeCompanyData() {
        super();
    }

    public String getOffice() {
        return this.office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "OfficeCompanyData [office=" + this.office + ", company=" + this.company + "]";
    }
}
