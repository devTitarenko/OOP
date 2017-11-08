package polyclinic.card;

import polyclinic.Bill;
import polyclinic.policy.MedicalInsurancePolicy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PaidPatientsCard extends SocialPatientsCard {

    private ArrayList<Bill> bills = new ArrayList<>();

    public PaidPatientsCard() {
        super(null, null);
    }

    public PaidPatientsCard(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public PaidPatientsCard(String firstName, String lastName, String address) {
        super(firstName, lastName, address);
    }

    public PaidPatientsCard(String firstName, String lastName, String address, MedicalInsurancePolicy medicalInsurancePolicy) {
        super(firstName, lastName, address, medicalInsurancePolicy);
    }

    public PaidPatientsCard(String firstName, String lastName, String address, MedicalInsurancePolicy medicalInsurancePolicy, ArrayList<Bill> bills) {
        super(firstName, lastName, address, medicalInsurancePolicy);
        this.bills = bills;
    }

    public ArrayList<Bill> getBills() {
        return bills;
    }

    public void setBills(ArrayList<Bill> bills) {
        this.bills = bills;
    }

    public int totalAmount() {
        return bills.stream().mapToInt(Bill::getAmount).sum();
    }

    public List<Bill> billListByDate(Date date) {
        return bills.stream()
                .filter(bill -> bill.getDate().equals(date))
                .collect(Collectors.toList());
    }

    public void addBill(Bill bill) {
        bills.add(bill);
    }

    public void removeBill(Date date, int amount) {
        bills = new ArrayList<>(
                bills.stream()
                        .filter(b -> !b.getDate().equals(date) || b.getAmount() != amount)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public String toString() {
        return "PaidPatientsCard{" +
                "firstName='" + super.getFirstName() + '\'' +
                ", lastName='" + super.getLastName() + '\'' +
                ", address='" + super.getAddress() + '\'' +
                ", medicalInsurancePolicy=" + super.getMedicalInsurancePolicy() +
                ", bills=" + bills +
                '}';
    }
}
