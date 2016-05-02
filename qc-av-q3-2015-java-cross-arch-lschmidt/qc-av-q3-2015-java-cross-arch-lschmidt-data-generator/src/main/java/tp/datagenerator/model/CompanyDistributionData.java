package tp.datagenerator.model;

public class CompanyDistributionData {
    private Company company;
    private String distribution;

    public CompanyDistributionData() {
        super();
    }

    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getDistribution() {
        return this.distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    @Override
    public String toString() {
        return "CompanyDistributionData [companyCode=" + this.company.getCode() + ", distribution=" + this.distribution
            + "]";
    }
}
