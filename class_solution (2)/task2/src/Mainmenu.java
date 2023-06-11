import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.EOFException;

public class Mainmenu{

    private Scanner sc;
    private Cabin[] customerData;

    public static void main(String[] args) {
        Mainmenu Mainmenu = new Mainmenu();
        Mainmenu.sc = new Scanner(System.in);
        Mainmenu.customerData = new Cabin[12];
        Mainmenu.launchcabinMenu();
    }

    public void launchcabinMenu()
    {
        menu:
        while (true)
        {
            System.out.println();
            System.out.println("//Cruise Ship Boarding Programme.// \n");

            System.out.println("Press A for Add a customer to a cabin");
            System.out.println("Press V for View all cabins");
            System.out.println("Press E for Display Empty cabins");
            System.out.println("Press D for Delete customer from cabin");
            System.out.println("Press F for Find cabin from customer name");
            System.out.println("Press S for Store program data into file");
            System.out.println("Press L for Load program data from file");
            System.out.println("Press O for View passengers Ordered alphabetically by name");
            System.out.println("Press T for view expenses");
            System.out.println("Press Q for quit");

            System.out.println("Enter your selection: ");
            String userInput = sc.nextLine();
            userInput = userInput.toUpperCase();

            switch (userInput) {
                case "A":
                    addCustomerToCabin();
                    break;
                case "V":
                    viewAllCabins();
                    break;
                case "E":
                    displayEmptyCabins();
                    break;
                case "D":
                    deleteCustomerFromCabin();
                    break;
                case "F":
                    findCabinFromCustomerName();
                    break;
                case "S":
                    storeProgramData();
                    break;
                case "L":
                    loadProgramData();
                    break;
                case "O":
                    viewPassengersOrderedAlphabeticallyByName();
                    break;
                case "T":
                    showPassengerExpenses();
                    break;
                case "Q":
                    break menu;
                default:
                    System.out.println("Invalid input. Please try again!");
            }
        }
    }

    public void addCustomerToCabin() {
        try {
                System.out.println("Enter Cabin number you wish to book");
                int cabinNumber = Integer.parseInt(sc.nextLine());

                if (cabinNumber > 0 && cabinNumber < 13)
                {
                    if (customerData[cabinNumber - 1] == null)
                    {
                        System.out.println("Enter passenger count");
                        int passengerCount = Integer.parseInt(sc.nextLine());

                        if (passengerCount > 0 && passengerCount < 4)
                        {
                            Passenger[] passengers = new Passenger[passengerCount];

                            for (int i=0; i< passengerCount; i++)
                            {
                                System.out.println("Enter passenger " + (i+1) + " first name");
                                String customerFirstName = sc.nextLine();
                                System.out.println("Enter passenger " + (i+1) + " surname");
                                String customerSurname = sc.nextLine();
                                System.out.println("Enter passenger " + (i+1) + " expenses");
                                Double customerExpenses = Double.parseDouble(sc.nextLine());

                                Passenger passenger = new Passenger(customerFirstName, customerSurname, customerExpenses);
                                passengers[i] = passenger;
                            }

                            customerData[cabinNumber - 1] = new Cabin(cabinNumber, passengers);
                            System.out.println("Boarding Success.");
                        } else {
                            System.out.println("Sorry. We are proving booking for upto 3 passengers");
                        }
                    } else {
                        System.out.println("Sorry, this cabin is already booked");
                    }
                } else {
                    System.out.println("Invalid cabin number");
                }
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }

    public void viewAllCabins() {
        for (int i = 0; i < customerData.length; i++)
        {
            if (customerData[i] == null)
            {
                System.out.println("Cabin number: " + (i + 1) + ", is EMPTY");
            } else {
                System.out.println("Cabin number: " + (i + 1) + ", The passenger details :");
                for (int j = 0; j<customerData[i].getCabinpassengers().length; j++)
                {
                    System.out.println(customerData[i].getCabinpassengers()[j].getFirstName() + " "
                            + customerData[i].getCabinpassengers()[j].getSurname());
                }
            }
        }
    }

    public void displayEmptyCabins() {
        System.out.println("Empty cabin list");
        for (int i = 0; i < customerData.length; i++) {
            if (customerData[i] == null)
            {
                System.out.println("Cabin number: " + (i + 1));
            }else{
                System.out.println("There are no empty cabins.");
            }
        }
    }

    public void deleteCustomerFromCabin() {
        try {
            System.out.println("Enter Cabin number");
            int cabinNumber = Integer.parseInt(sc.nextLine());

            if (cabinNumber > 0 && cabinNumber < 13) {
                if (customerData[cabinNumber - 1] == null) {
                    System.out.println("Sorry, this cabin has not booked");
                } else {
                    System.out.println("Removed customers from cabin number : " + cabinNumber);
                    customerData[cabinNumber - 1] = null;
                }
            } else {
                System.out.println("Invalid cabin number");
            }
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }

    public void findCabinFromCustomerName() {
        System.out.println("Enter customer First Name :");
        String customerFirstName = sc.nextLine();
        System.out.println("Enter customer Surname :");
        String customerSurname = sc.nextLine();

        boolean flag = true;
        for (int i = 0; i < customerData.length; i++)
        {
            if (customerData[i] != null)
            {
                for (int j = 0; j<customerData[i].getCabinpassengers().length; j++)
                {
                    if (customerData[i].getCabinpassengers()[j].getFirstName().equals(customerFirstName) &&
                            customerData[i].getCabinpassengers()[j].getSurname().equals(customerSurname))
                    {
                        flag = false;
                        System.out.println("Your cabin number is : " + (i + 1));
                        break;
                    }
                }
            }
        }

        if (flag) {
            System.out.println("We are sorry, unable to find cabin number for your name");
        }
    }

    public void storeProgramData() {
        try {
            FileWriter filewriter = new FileWriter("Outputdata.txt");
            filewriter.write("//////////////////////////CABIN DATA////////////////////////" + "\n");
            filewriter.write("\n");
            for (int x = 0; x < 12; x++) {
                if (customerData[x] == null) {
                    filewriter.write("---------------------------------------------------------------" + "\n");
                    filewriter.write("cabin number " + (x+1) + " is empty \n");
                } else {
                    for (int j = 0; j<customerData[x].getCabinpassengers().length; j++) {
                        filewriter.write("---------------------------------------------------------------" + "\n");
                        filewriter.write("cabin number " + (x + 1) + " is occupied by " + customerData[x].getCabinpassengers()[j].getFirstName() + "\n");
                    }

                }

            }
            filewriter.close();
            System.out.println("Data Successfully Stored in a file");
        } catch (IOException e) {
            System.out.println("An error occurred");
            //e.printStackTrace();
        }
    }


    public void loadProgramData() {
        // Loading data of the Stored file.
        System.out.println("LOADED DATA: ");
        try {
            File object = new File("Outputdata.txt");
            Scanner reader = new Scanner(object);
            while (reader.hasNextLine()) {
                String info = reader.nextLine();
                System.out.println(info);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred");
            //e.printStackTrace();
        }
        System.out.println("/////////////////////////////////////////////////////////////////// ");
    }


    public void viewPassengersOrderedAlphabeticallyByName()
    {
        ArrayList<String> tempPassengerNames = new ArrayList<>();
        for (Cabin customerDatum : customerData)
        {
            if (customerDatum != null)
            {
                for (int j = 0; j < customerDatum.getCabinpassengers().length; j++)
                {
                    tempPassengerNames.add(customerDatum.getCabinpassengers()[j].getFirstName() + " " + customerDatum.getCabinpassengers()[j].getSurname());
                }
            }
        }

        String tempName;
        for (int i = 0; i < tempPassengerNames.size(); i++)
        {
            for (int j = i + 1; j < tempPassengerNames.size(); j++)
            {
                if (tempPassengerNames.get(i).compareTo(tempPassengerNames.get(j)) > 0)
                {
                    tempName = tempPassengerNames.get(i);
                    tempPassengerNames.set(i, tempPassengerNames.get(j));
                    tempPassengerNames.set(j, tempName);
                }
            }
        }

        System.out.println("Passenger name list in alphabetically order :");
        for (String tempPassengerName : tempPassengerNames)
        {
            System.out.println(tempPassengerName);
        }
    }

    public void showPassengerExpenses()
    {
        for(int i=0; i<customerData.length; i++)
        {
            if(customerData[i] != null)
            {
                Double total = 0.0;
                for(int j = 0; j<customerData[i].getCabinpassengers().length; j++)
                {
                    System.out.println("Passenger - " + customerData[i].getCabinpassengers()[j].getFirstName() + " "
                            + customerData[i].getCabinpassengers()[j].getSurname() + " expense is : "
                            + customerData[i].getCabinpassengers()[j].getExpense());
                    total += customerData[i].getCabinpassengers()[j].getExpense();
                }
                System.out.println("Cabin " + (i+1) + " passengers total expenses is : " + total);
            }
        }
    }

}