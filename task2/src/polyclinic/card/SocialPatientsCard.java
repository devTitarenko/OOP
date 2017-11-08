package polyclinic.card;

import polyclinic.policy.MedicalInsurancePolicy;

import java.util.Objects;

public class SocialPatientsCard implements PatientsCard {

    private String firstName;
    private String lastName;
    private String address;
    private MedicalInsurancePolicy medicalInsurancePolicy;

    public SocialPatientsCard(String firstName, String lastName) {
        new SocialPatientsCard(firstName, lastName, null, null);
    }

    public SocialPatientsCard(String firstName, String lastName, String address) {
        new SocialPatientsCard(firstName, lastName, address, null);
    }

    public SocialPatientsCard(String firstName, String lastName, String address, MedicalInsurancePolicy medicalInsurancePolicy) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.medicalInsurancePolicy = medicalInsurancePolicy;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public MedicalInsurancePolicy getMedicalInsurancePolicy() {
        return medicalInsurancePolicy;
    }

    @Override
    public void setMedicalInsurancePolicy(MedicalInsurancePolicy medicalInsurancePolicy) {
        this.medicalInsurancePolicy = medicalInsurancePolicy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SocialPatientsCard that = (SocialPatientsCard) o;

        return Objects.equals(this.firstName, that.firstName) &&
                Objects.equals(this.lastName, that.lastName) &&
                Objects.equals(this.address, that.address) &&
                Objects.equals(this.medicalInsurancePolicy, that.medicalInsurancePolicy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, address, medicalInsurancePolicy);
    }

    @Override
    public String toString() {
        return "SocialPatientsCard{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", medicalInsurancePolicy=" + medicalInsurancePolicy +
                '}';
    }
}
