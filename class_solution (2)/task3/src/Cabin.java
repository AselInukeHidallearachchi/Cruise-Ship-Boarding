import java.io.Serializable;

public class Cabin implements Serializable {
    private int cabinNumber;
    private Passenger[] cabinpassengers;

    public Cabin(int cabinNumber, Passenger[] cabinpassengers)
    {
        this.cabinNumber = cabinNumber;
        this.cabinpassengers = cabinpassengers;
    }

    public void setCabinNumber(int cabinNumber) {
        this.cabinNumber = cabinNumber;
    }

    public void setCabinpassengers(Passenger[] cabinpassengers)
    {
        this.cabinpassengers = cabinpassengers;
    }

    public int getCabinNumber() {
        return cabinNumber;
    }

    public Passenger[] getCabinpassengers() {
        return cabinpassengers;
    }
}
