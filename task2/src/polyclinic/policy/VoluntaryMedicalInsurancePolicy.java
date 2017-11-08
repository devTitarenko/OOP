package polyclinic.policy;

public class VoluntaryMedicalInsurancePolicy extends MedicalInsurancePolicy {

    private int totalAmount;
    private int paidAmount;

    public VoluntaryMedicalInsurancePolicy() {
        totalAmount = 100_000;
    }

    public VoluntaryMedicalInsurancePolicy(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(int paidAmount) {
        this.paidAmount = paidAmount;
    }

    @Override
    public String toString() {
        return "VoluntaryMedicalInsurancePolicy{" +
                super.toString() +
                ", totalAmount=" + totalAmount +
                ", paidAmount=" + paidAmount +
                '}';
    }
}
