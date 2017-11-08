package polyclinic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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
            if (card.getInsurancePolicyId() == policyId) {
                return card;
            }
        }
        return null;
    }

    public PatientsCard[] getPatientsCardByAddress(String address) {
        List<PatientsCard> patientsCardList = new ArrayList<>();
        for (PatientsCard card : patientsCards) {
            if (card.getAddress().equals(address)) {
                patientsCardList.add(card);
            }
        }
        return patientsCardList.toArray(new PatientsCard[0]);
    }

    public void removePatientsCardByPolicyId(int policyId) {
        for (int cardPosition = 0; cardPosition < patientsCards.length; cardPosition++) {
            if (patientsCards[cardPosition].getInsurancePolicyId() == policyId) {
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

    @Override
    public String toString() {
        return "Polyclinic{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", patientsCards=" + Arrays.toString(patientsCards) +
                '}';
    }
}
