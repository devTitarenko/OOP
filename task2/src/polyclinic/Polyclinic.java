package polyclinic;

import polyclinic.card.*;
import polyclinic.policy.*;

import java.time.LocalDate;
import java.util.*;

public class Polyclinic {

    private int id;
    private String address;
    private PatientsCard[] patientsCards;

    public Polyclinic(int id, String address) {
        this.id = id;
        this.address = address;
        patientsCards = new PatientsCard[0];
    }

    public Polyclinic(int id, String address, PatientsCard[] patientsCards) {
        this.id = id;
        this.address = address;
        this.patientsCards = patientsCards;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotalNumberOfPatients() {
        return patientsCards.length;
    }

    public PatientsCard getPatientsCardByPolicyId(int policyId) {
        for (PatientsCard card : patientsCards) {
            if (card.getMedicalInsurancePolicy().getPolicyId() == policyId) {
                return card;
            }
        }
        return null;
    }

    public PatientsCard[] getPatientsCardByAddress(String address) {
        List<PatientsCard> PatientsCardList = new ArrayList<>();
        for (PatientsCard card : patientsCards) {
            if (card.getAddress().equals(address)) {
                PatientsCardList.add(card);
            }
        }
        return PatientsCardList.toArray(new PatientsCard[PatientsCardList.size()]);
    }

    public void removePatientsCardByPolicyId(int policyId) {
        for (int cardPosition = 0; cardPosition < patientsCards.length; cardPosition++) {
            if (patientsCards[cardPosition].getMedicalInsurancePolicy().getPolicyId() == policyId) {
                PatientsCard[] newPatientsCardsArray = new PatientsCard[patientsCards.length - 1];
                System.arraycopy(patientsCards, 0, newPatientsCardsArray, 0, cardPosition);
                System.arraycopy(patientsCards, cardPosition + 1, newPatientsCardsArray, cardPosition, newPatientsCardsArray.length - cardPosition);
                patientsCards = newPatientsCardsArray;
                return;
            }
        }
    }

    public void addPatientsCard(PatientsCard card) {
        PatientsCard[] newPatientsCardsArray = Arrays.copyOf(patientsCards, patientsCards.length + 1);
        newPatientsCardsArray[patientsCards.length] = card;
        patientsCards = newPatientsCardsArray;
    }

    public PatientsCard[] getPatientsCards() {
        return patientsCards;
    }

    public PatientsCard[] getSortedPatientsCardsByAddress() {
        PatientsCard[] newPatientsCardsArray = Arrays.copyOf(patientsCards, patientsCards.length);
        Arrays.sort(newPatientsCardsArray, Comparator.comparing(PatientsCard::getAddress));
        return newPatientsCardsArray;
    }

    public int getObligatoryPolicyQuatity() {
        int result = 0;
        for (PatientsCard card : patientsCards) {
            if (card.getMedicalInsurancePolicy() instanceof ObligatoryMedicalInsurancePolicy) {
                result++;
            }
        }
        return result;
    }

    public int getVoluntaryPolicyQuatity() {
        int result = 0;
        for (PatientsCard card : patientsCards) {
            if (card.getMedicalInsurancePolicy() instanceof VoluntaryMedicalInsurancePolicy) {
                result++;
            }
        }
        return result;
    }

    public int getPaidPatientsQuatity() {
        int result = 0;
        for (PatientsCard card : patientsCards) {
            if (card instanceof PaidPatientsCard) {
                result++;
            }
        }
        return result;
    }

    public int totalAmountByMonthAndYear(int year, int month) {
        int result = 0;
        for (PatientsCard card : patientsCards) {
            if (card instanceof PaidPatientsCard) {
                PaidPatientsCard paidPatientsCard = (PaidPatientsCard) card;
                Date startOfMonth = java.sql.Date.valueOf(LocalDate.of(year, month, 1).minusDays(1));
                Date endOfMonth = java.sql.Date.valueOf(LocalDate.of(year, month + 1, 1));
                result += paidPatientsCard.getBills().stream().filter(
                        bill -> bill.getDate().after(startOfMonth) &&
                                bill.getDate().before(endOfMonth))
                        .mapToInt(Bill::getAmount).sum();
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "Polyclinic{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", patientsCards=" + Arrays.toString(patientsCards) +
                '}';
    }
}
