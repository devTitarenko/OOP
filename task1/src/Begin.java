import polyclinic.PatientsCard;
import polyclinic.Polyclinic;

import java.util.Arrays;

public class Begin {

    public static void main(String[] args) {
        Begin begin = new Begin();
        new Begin().test(begin.init());
    }

    private Polyclinic init() {
        return new Polyclinic(1999, "USA, Portland, Gilead str, 7",
                new PatientsCard[]{
                        new PatientsCard("George", "Martin", "Winterfell str. 12", 1000),
                        new PatientsCard("Stephen", "King", "Deschain str. 19", 1001),
                        new PatientsCard("Joanne", "Rowling", "Second ave. 26", 1002),
                        new PatientsCard("Mark", "Twain", "Deschain str. 19", 1003)
                });
    }

    private void test(Polyclinic polyclinic) {
        System.out.println(polyclinic.getTotalNumberOfPatients());
        System.out.println(polyclinic.getPatientsCardByPolicyId(1001));
        System.out.println(Arrays.toString(polyclinic.getPatientsCardByAddress("Deschain str. 19")));
        polyclinic.removePatientsCardByPolicyId(1002);
        polyclinic.addPatientsCard(
                new PatientsCard("Oscar", "Wilde", "Sixth str. 54", 1004));
        System.out.println(Arrays.toString(polyclinic.getPatientsCards()));
        System.out.println(Arrays.toString(polyclinic.getSortedPatientsCardsByAddress()));

    }
}