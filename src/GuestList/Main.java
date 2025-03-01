package GuestList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    static GuestList myGuestList = new GuestList();

    //Creare obiect pentru metodele remove si update
    static Guest objectForCheck = new Guest("", "", "", "");

    public static void main(String[] args) {
        try {
            myGuestList.readFromBinaryFile();
        } catch (IOException e) {
            System.out.println("Deocamdata nu exista nicio varianta de back-up");
        }

        if (myGuestList.getList().size() == 0) {
            myGuestList.setSpotAvailableForList();
        }

        while (true) {
            String keyboardCommand = display("Asteapta comanda: (help - Afiseaza lista de comenzi)");

            boolean checkCommandWord = false;

            switch (keyboardCommand) {
                case ("help"):
                    System.out.println("help  - Afiseaza aceasta lista de comenzi " + "\n" + "add - Adauga o noua persoana (inscriere)"
                            + "\n" + "check - Verifica daca o persoana este inscrisa la eveniment" + "\n" + "remove - Sterge o persoana existenta din lista" +
                            "\n" + "update - Actualizeaza detaliile unei persoane" + "\n" + "guests  - Lista de persoane care participa la eveniment" + "\n"
                            + "waitlist - Persoanele din lista de asteptare" + "\n" + "available - Numarul de locuri libere" + "\n"
                            + "guests_no - Numarul de persoane care participa la eveniment" + "\n" + "waitlist_no - Numarul de persoane din lista de asteptare" +
                            "\n" + "subscribe_no  - Numarul total de persoane inscrise" + "\n" + "search - Cauta toti invitatii conform sirului de caractere introdus"
                            + "reset - Reseteaza toate datele salvate" + "\n" + "quit - Inchide aplicatia" + "\n" );
                    checkCommandWord = true;
                    break;
                case ("add"):
                    add();
                    checkCommandWord = true;
                    break;

                case ("check"):
                    boolean guestExist = check();
                    System.out.println(guestExist);
                    checkCommandWord = true;
                    break;

                case ("remove"):
                    if (check()) {
                        myGuestList.removeGuest(objectForCheck);
                    }

                    checkCommandWord = true;
                    break;

                case ("update"):
                    System.out.println("Se actualizeaza detaliile unei persoane");
                    if (check()) {
                        //Preia referinta obiectului din ArrayList care este egal (aferent metodei equals suprascrisa in clasa Guest) cu objectForTest
                        Guest guestForUpdate = myGuestList.updateGuestDetails(objectForCheck);
                        //Schimba campul in  fuctie de CompareCase-ul introdus
                        checkedGuestForUpdate(guestForUpdate);
                    }
                    checkCommandWord = true;
                    break;
                case ("guests"):
                    myGuestList.displayList();
                    checkCommandWord = true;
                    break;

                case ("waitlist"):
                    myGuestList.displayWaitList();
                    checkCommandWord = true;
                    break;

                case ("available"):
                    myGuestList.available();
                    checkCommandWord = true;
                    break;

                case ("guests_no"):
                    myGuestList.guestNumber();
                    checkCommandWord = true;
                    break;

                case ("waitlist_no"):
                    myGuestList.waitListNumber();
                    checkCommandWord = true;
                    break;

                case ("subscribe_no"):
                    myGuestList.subscribeNumber();
                    checkCommandWord = true;
                    break;

                case ("search"):
                    System.out.println("Introduceti subsirul.");
                    String subString = sc.next();
                    myGuestList.search(subString);
                    checkCommandWord = true;
                    break;

                case ("quit"):
                    ArrayList<Guest> list = myGuestList.getList();
                    ArrayList<Guest> waitList = myGuestList.getWaitList();

                    try {
                        myGuestList.writeToBinaryFile(list, waitList);
                    } catch (IOException e) {
                        System.out.println("S-a produs o eroare");
                    }

                    System.out.println("Aplicatia se inchide");
                    checkCommandWord = true;
                    return;
            }
            if (checkCommandWord == false) {
                System.out.println("Comanda introdusa nu este valida." + "\n" + "Incercati inca o data.");
            }
        }
    }

    private static String display(String display) {
        System.out.println(display);
        return sc.next();
    }


    //Creare obiect care va fi introdus in ArrayList
    private static void add() {
        System.out.println("Se adauga o noua persoana");

        String lastName = display("Introduceti numele de familie: ");

        String firstName = display("Introduceti prenumele");

        String email = display("Introduceti email-ul:");

        String phoneNumber = display("Introduceti numarul de telefon (format: „+40733386463“)");

        Guest guest = new Guest(firstName, lastName, email, phoneNumber);

        myGuestList.insertGuest(guest);

    }

    // Verificare obiect in ArrayList
    private static boolean check() {
        System.out.println("Alege modul de autentificare, tastand: " + "\n" + "\"1\" - Nume si prenume" + "\n" +
                "\"2\" - Email" + "\n" + "\"3\" - Numar de telefon (format \"+40733386463\")");

        //Setare campuri pentru objectForTest
        objectForCheck = checkedGuest(objectForCheck);

        if (myGuestList.searchGuest(objectForCheck)) {
            return true;
        }
        return false;
    }

    private static int validNumber() {
        while (true) {
            try {
                int checkNumber = sc.nextInt();
                return checkNumber;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Introduce datele conform credentialelor de mai sus");
            }
        }
    }

    //Setare parametrii pentru objectForTest
    private static Guest checkedGuest(Guest objectForTest) {
        String checkSearch = "";
        int checkNumber = validNumber();

        switch (checkNumber) {
            case 1:
                checkSearch = display("Introduceti numele");
                objectForTest.setFirstName(checkSearch);

                checkSearch = display("Intoduceti prenumele");
                objectForTest.setLastName(checkSearch);
                break;
            case 2:
                checkSearch = display("Introduceti email-ul");
                objectForTest.setEmail(checkSearch);
                break;
            case 3:
                checkSearch = display("Introduceti numarul de telefon");
                objectForTest.setPhoneNumber(checkSearch);
                break;
        }

        //Setare compareCase pentu objectForTest
        objectForTest.setCompareCase(checkNumber);

        return objectForTest;
    }

    private static Guest checkedGuestForUpdate(Guest objectForTest) {

        System.out.println("Alege campul de actualizat, tastand: " + "\n" + "\"1\" - Nume" + "\n" + "\"2\" - Prenume" + "\n" + "\"3\" - Email"
                + "\n" + "\"4\" - Numar de telefon (format \"+40733386463\")");


        String checkSearch = "";
        int checkNumber = validNumber();
        switch (checkNumber) {
            case 1:
                checkSearch = display("Introduceti numele de familie:");
                objectForTest.setLastName(checkSearch);
                System.out.println(objectForTest.getLastName());
                break;

            case 2:
                checkSearch = display("Introduceti prenumele:");
                objectForTest.setLastName(checkSearch);
                System.out.println(objectForTest.getFirstName());
                break;

            case 3:
                checkSearch = display("Introduceti email-ul:");
                objectForTest.setEmail(checkSearch);
                break;

            case 4:
                checkSearch = display("Introduceti numarul de telefon:");
                objectForTest.setPhoneNumber(checkSearch);
                objectForTest.getPhoneNumber();
                break;
        }
        objectForTest.setCompareCase(checkNumber);

        return objectForTest;


    }
}
