package polyclinic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import polyclinic.card.*;
import polyclinic.policy.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTests {
    PatientsCard patientsCard0;
    PatientsCard patientsCard1;
    PatientsCard patientsCard2;
    PatientsCard patientsCard3;
    PatientsCard patientsCard4;

    Bill bill0 = new Bill();
    Bill bill1 = new Bill();
    Bill bill2 = new Bill();
    Bill bill3 = new Bill();
    Bill bill4 = new Bill();
    Bill bill5 = new Bill();

    Polyclinic polyclinic;

    @BeforeEach
    public void init() {
        ObligatoryMedicalInsurancePolicy policy0 = new ObligatoryMedicalInsurancePolicy();
        policy0.setPolicyId(1000);
        policy0.setCompanyName("obligatoryCompany1");

        ObligatoryMedicalInsurancePolicy policy1 = new ObligatoryMedicalInsurancePolicy();
        policy1.setPolicyId(1001);
        policy1.setCompanyName("obligatoryCompany2");

        VoluntaryMedicalInsurancePolicy policy2 = new VoluntaryMedicalInsurancePolicy(6543);
        policy2.setPolicyId(1002);
        policy2.setCompanyName("voluntaryCompany1");
        policy2.setPaidAmount(36);

        VoluntaryMedicalInsurancePolicy policy3 = new VoluntaryMedicalInsurancePolicy(555);
        policy3.setPolicyId(1003);
        policy3.setCompanyName("voluntaryCompany2");

        VoluntaryMedicalInsurancePolicy policy4 = new VoluntaryMedicalInsurancePolicy(120);
        policy4.setPolicyId(1004);
        policy4.setCompanyName("voluntaryCompany3");
        policy4.setPaidAmount(99);

        insertValuesToBills();

        patientsCard0 = new PaidPatientsCard("George", "Martin", "Winterfell str. 12", policy0,
                new ArrayList<Bill>() {{
                    add(bill0);
                    add(bill1);
                }});
        patientsCard1 = new PaidPatientsCard("Stephen", "King", "Deschain str. 19", policy1,
                new ArrayList<Bill>() {{
                    add(bill2);
                    add(bill3);
                    add(bill4);
                }});
        patientsCard2 = new PaidPatientsCard("Joanne", "Rowling", "Second ave. 26", policy2);
        patientsCard3 = new SocialPatientsCard("Mark", "Twain", "Deschain str. 19", policy3);
        patientsCard4 = new SocialPatientsCard("Oscar", "Wilde", "Sixth str. 54", policy4);

        polyclinic = new Polyclinic(1999, "USA, Portland, Gilead str, 7",
                new PatientsCard[]{
                        patientsCard0,
                        patientsCard1,
                        patientsCard2,
                        patientsCard3
                });
    }

    @Test
    public void testPolyclinic1() {
        assertTrue(polyclinic.getTotalNumberOfPatients() == 4);
        assertFalse(polyclinic.getTotalNumberOfPatients() != 4);

        assertTrue(polyclinic.getPatientsCardByPolicyId(1001).getLastName().equals("King"));
        assertTrue(polyclinic.getPatientsCardByPolicyId(1001).equals(patientsCard1));
        assertFalse(polyclinic.getPatientsCardByPolicyId(1001).equals(patientsCard2));

        assertTrue(Arrays.equals(polyclinic.getPatientsCardByAddress(null), new PatientsCard[0]));
        assertTrue(Arrays.equals(
                polyclinic.getPatientsCardByAddress("Deschain str. 19"),
                new PatientsCard[]{
                        patientsCard1,
                        patientsCard3
                })
        );

        polyclinic.removePatientsCardByPolicyId(1002);

        assertNull(polyclinic.getPatientsCardByPolicyId(1002));
        assertTrue(polyclinic.getTotalNumberOfPatients() == 3);

        polyclinic.addPatientsCard(patientsCard4);
        assertTrue(polyclinic.getTotalNumberOfPatients() == 4);

        assertTrue(Arrays.equals(polyclinic.getPatientsCards(),
                new PatientsCard[]{
                        patientsCard0,
                        patientsCard1,
                        patientsCard3,
                        patientsCard4
                })
        );

        assertTrue(Arrays.equals(polyclinic.getSortedPatientsCardsByAddress(),
                new PatientsCard[]{
                        patientsCard1,
                        patientsCard3,
                        patientsCard4,
                        patientsCard0
                })
        );
    }

    @Test
    public void testPolyclinic2() {
        assertTrue(polyclinic.getObligatoryPolicyQuatity() == 2);

        assertTrue(polyclinic.getVoluntaryPolicyQuatity() == 2);
        polyclinic.addPatientsCard(patientsCard4);
        assertTrue(polyclinic.getVoluntaryPolicyQuatity() == 3);

        assertTrue(polyclinic.getPaidPatientsQuatity() == 3);

        assertTrue(polyclinic.totalAmountByMonthAndYear(2007, 7) == 1111);
        assertTrue(polyclinic.totalAmountByMonthAndYear(2012, 10) == 2735 + 567);
    }

    @Test
    public void testPaidPatientsCard() {
        assertTrue(((PaidPatientsCard) patientsCard2).totalAmount() == 0);
        assertTrue(((PaidPatientsCard) patientsCard1).totalAmount() == 1111 + 29 + 567);

        assertEquals(
                ((PaidPatientsCard) patientsCard1).billListByDate(null),
                Collections.EMPTY_LIST);
        assertEquals(
                ((PaidPatientsCard) patientsCard1).billListByDate(
                        makeUtilDate(2006, 11, 17)),
                Collections.EMPTY_LIST);
        assertEquals(
                ((PaidPatientsCard) patientsCard1).billListByDate(
                        makeUtilDate(2010, 11, 17)),
                new ArrayList<Bill>() {{
                    add(bill3);
                }});
        ((PaidPatientsCard) patientsCard1).addBill(bill5);
        assertEquals(
                ((PaidPatientsCard) patientsCard1).billListByDate(
                        makeUtilDate(2012, 10, 1)),
                new ArrayList<Bill>() {{
                    add(bill4);
                    add(bill5);
                }});

        ((PaidPatientsCard) patientsCard1).removeBill(makeUtilDate(2012, 10, 1), 567);
        ((PaidPatientsCard) patientsCard1).removeBill(null, 567);

        assertEquals(((PaidPatientsCard) patientsCard1).getBills(),
                new ArrayList<Bill>() {{
                    add(bill2);
                    add(bill3);
                    add(bill5);
                }}
        );
    }

    private void insertValuesToBills() {
        bill0.setAmount(2735);
        bill0.setDate(makeUtilDate(2012, 10, 16));
        bill0.setServiceType(Bill.ServiceType.ENDOCRINOLOGY);

        bill1.setAmount(946);
        bill1.setDate(makeUtilDate(2014, 7, 7));
        bill1.setServiceType(Bill.ServiceType.STOMATOLOGY);

        bill2.setAmount(1111);
        bill2.setDate(makeUtilDate(2007, 7, 22));
        bill2.setServiceType(Bill.ServiceType.OPHTHALMOLOGY);

        bill3.setAmount(29);
        bill3.setDate(makeUtilDate(2010, 11, 17));
        bill3.setServiceType(Bill.ServiceType.PROF_INSPECTION);

        bill4.setAmount(567);
        bill4.setDate(makeUtilDate(2012, 10, 1));
        bill4.setServiceType(Bill.ServiceType.NEUROLOGY);

        bill5.setAmount(789);
        bill5.setDate(makeUtilDate(2012, 10, 1));
        bill5.setServiceType(Bill.ServiceType.SURGERY);
    }

    private Date makeUtilDate(int year, int month, int day) {
        return java.sql.Date.valueOf(LocalDate.of(year, month, day));
    }
}
