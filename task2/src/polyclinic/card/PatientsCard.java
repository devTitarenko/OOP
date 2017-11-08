package polyclinic.card;

import polyclinic.policy.MedicalInsurancePolicy;

public interface PatientsCard {

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getAddress();

    void setAddress(String address);

    MedicalInsurancePolicy getMedicalInsurancePolicy();

    void setMedicalInsurancePolicy(MedicalInsurancePolicy medicalInsurancePolicy);

}
