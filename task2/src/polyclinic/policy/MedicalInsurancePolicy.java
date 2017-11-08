package polyclinic.policy;

public abstract class MedicalInsurancePolicy {

    private int policyId;
    private String companyName;

    public MedicalInsurancePolicy() {
    }

    public MedicalInsurancePolicy(int policyId, String companyName) {
        this.policyId = policyId;
        this.companyName = companyName;
    }

    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "policyId=" + policyId +
                ", companyName='" + companyName + '\'';
    }
}
