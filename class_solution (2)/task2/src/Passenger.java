import java.io.Serializable;

public class Passenger implements Serializable {
        private String firstName;
        private String surname;
        private Double expenses;

        public Passenger(String firstName, String surname, Double expenses)
        {
            this.firstName = firstName;
            this.surname = surname;
            this.expenses = expenses;
        }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setExpenses(Double expenses) {
        this.expenses = expenses;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public Double getExpense() {
        return expenses;
    }

}

