package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileWriter; // Import the FileWriter class
import java.io.IOException; // Import the IOException class to handle errors or(java.io.*)

public class Task1shipmenu {
    public static Scanner input = new Scanner(System.in);



    public static void main(String[] args) {
        String[] cabins = new String[12];
        initialise(cabins);
        String CabinName=null;
        String Selection;
        int n = cabins.length;



        do
        {
            System.out.println();
            System.out.println("//Cruise Ship Boarding Programme.// \n");


            System.out.println("A: Add user to the cabin.");
            System.out.println("E: Display Empty Rooms.");
            System.out.println("V: View all Rooms.");
            System.out.println("D: Delete customer from room.");
            System.out.println("F: Find room from customer name.");
            System.out.println("S: Store program data in to file.");
            System.out.println("L: Load program data from file.");
            System.out.println("O: View rooms Ordered alphabetically by name.");
            System.out.println("X: To end the programme.");
            System.out.println("Please select one of the options: ");
            Selection = input.next();
            Selection = Selection.toUpperCase();
            switch (Selection)
            {
                case "A":
                    adduser(cabins);
                    break;
                case "E":
                    checkifempty(cabins);
                    break;
                case "V":
                    view(cabins);
                    break;
                case "D":
                    DeleteCustomerFromCabin(cabins, CabinName);
                    break;
                case "F":
                    FindCabinFromCustomerName(cabins);
                    break;
                case "S":
                    Store(cabins);
                    break;
                case "L":
                    Load();
                    break;
                case "O":
                    ViewCabinsOrderedAlphabeticallyByName(cabins,n);
                    break;
                case "X":
                    System.out.println("YOU ENDED THE PROGRAMME\nThank you!\n");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid Selection");
                    break;
            }
        }  while (Selection != "X");
    }
    private static void initialise(String[] cabinArr)
    {// Initialise the cabin array as empty

        for (int x = 0; x < cabinArr.length; x++) {
            cabinArr[x] = "e";
        }
    }


    // Adding Passenger To cabin
    public static void adduser(String[] cabins){
        System.out.print("Enter passenger name: ");
        String passengerName = input.next();
        System.out.println("Enter cabin number 1 to 12: ");
        int cabinNumber = input.nextInt();
        cabins[cabinNumber - 1] = passengerName;
    }

    // View all cabins
    public static void view(String[] cabins){
        for (int j=0; j< cabins.length; j++)
        {   if(cabins[j].equals("e")){
            System.out.println("Cabin "+(j+1)+" is empty");
        }else{
            System.out.println("Cabin "+(j+1)+" occupied by "+cabins[j]);
        }

        }
    }
    //View empty cabins
    private static void checkifempty(String[] cabins)
    {
        for (int j = 0; j < cabins.length; j++)
        {
            if (cabins[j].equals("e"))
            {
                System.out.println("Cabin " + (j + 1) + " is empty");
            }
        }
    }
    private static void FindCabinFromCustomerName(String[] cabins) //find ustomer by name
    {
        Scanner input = new Scanner(System.in);
        String CabinName;
        System.out.println("Enter name to Search for:");
        CabinName = input.next();
        int x;
        boolean Checker = false;
        for (x = 0; x < cabins.length; x++)
        {
            if (CabinName.equals(cabins[x]))
            {
                System.out.println("The Cabin That Matches That name is in cabin number " + (x+1));
                Checker = true;
            }
        }
        if (Checker == false)
        {
            System.out.println("There are no Cabins Booked with that name\n(make sure you've used the correct CAP's)");
        }
    }
    private static void DeleteCustomerFromCabin(String[] cabins, String CabinName )
    {

        System.out.println("Enter name to Delete:");
        CabinName = input.next();
        for (int c=0 ; c<12; c++)
        {
            if (CabinName.equals(cabins[c]))
            {
                cabins[c]="e" ;
            }

        }
    }
    private static void ViewCabinsOrderedAlphabeticallyByName(String[] cabins,int n)
    {
        {
            String temp;

            // Sorting strings using bubble sort
            for (int j = 0; j < n - 1; j++)
            {
                for (int i = j + 1; i < n; i++)
                {
                    if (cabins[j].compareTo(cabins[i]) > 0)
                    {
                        temp = cabins[j];
                        cabins[j] = cabins[i];
                        cabins[i] = temp;
                    }
                }
            }
        }
        System.out.println("Names in sorted order are : ");
        for (int i = 0; i < n; i++)
        {
            if (cabins[i].equals("e"))
            {

            }else{
                System.out.println(cabins[i]);
            }
        }

    }
    private static void Store(String[] cabins) {
        // Storing the data in a text file
        try {
            FileWriter filewriter = new FileWriter("Outputdata.txt");
            filewriter.write("//////////////////////////CABIN DATA////////////////////////" + "\n");
            filewriter.write("\n");
            for (int x = 0; x < 12; x++) {
                if (cabins[x].equals("e")) {
                    filewriter.write("---------------------------------------------------------------" + "\n");
                    filewriter.write("cabin number " + (x+1) + " is empty \n");
                } else {
                    filewriter.write("---------------------------------------------------------------" + "\n");
                    filewriter.write("cabin number " + (x+1) + " is occupied by " + cabins[x] + "\n");


                }

            }
            filewriter.close();
            System.out.println("Data Successfully Stored in a file");
        } catch (IOException e) {
            System.out.println("An error occurred");
            //e.printStackTrace();
        }
    }
    private static void Load() {
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



}