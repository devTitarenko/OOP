package polyclinic;

public class PatientsCard {

    private String firstName;
    private String lastName;
    private String address;
    private int insurancePolicyId;

    public PatientsCard(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public PatientsCard(String firstName, String lastName, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public PatientsCard(String firstName, String lastName, String address, int insurancePolicyId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.insurancePolicyId = insurancePolicyId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getInsurancePolicyId() {
        return insurancePolicyId;
    }

    public void setInsurancePolicyId(int insurancePolicyId) {
        this.insurancePolicyId = insurancePolicyId;
    }

    @Override
    public String toString() {
        return "PatientsCard{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", insurancePolicyId=" + insurancePolicyId +
                '}';
    }
}
