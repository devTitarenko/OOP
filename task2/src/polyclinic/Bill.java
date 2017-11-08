package polyclinic;

import java.util.Date;

public class Bill {

    private Date date;
    private int amount;
    private ServiceType serviceType;

    public enum ServiceType {
        STOMATOLOGY,
        ENDOCRINOLOGY,
        SURGERY,
        PROF_INSPECTION,
        OPHTHALMOLOGY,
        GYNECOLOGY,
        NEUROLOGY
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }
}
