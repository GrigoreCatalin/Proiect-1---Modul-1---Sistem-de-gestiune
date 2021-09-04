import java.util.ArrayList;
import java.util.Locale;

public class GuestList {

    private ArrayList<Guest> list;
    private ArrayList<Guest> waitList;
    static private int spotAvailableForList;

    public GuestList() {
        this.list = new ArrayList<>();
        this.waitList = new ArrayList<>();
    }

    public static void setSpotAvailableForList(int spotAvailableForList) {
        GuestList.spotAvailableForList = spotAvailableForList;
    }

    // Adaugare obiect in ArrayList
    public int insertGuest(Guest guest) {
        if (searchGuestByEmail(guest) == true) {
            return -1;
        } else if (list.size() < spotAvailableForList) {
            list.add(guest);
            System.out.println("[" + list.get(list.size() - 1).getLastName() + " " + list.get(list.size() - 1).getFirstName() + "]"
                    + "Felicitari! Locul tau la eveniment este confirmat. Te asteptam!");
            return 0;
        } else {
            waitList.add(guest);
            System.out.println("[" + waitList.get(waitList.size() - 1).getLastName() + " " + waitList.get(waitList.size() - 1).getFirstName()
                    + "]" + " Te-ai inscris cu succes in lista de asteptare si ai primit numarul de ordine "
                    + waitList.size() + ". Te vom notifica daca un loc devine disponibil.");
            return waitList.size();
        }
    }

    public boolean searchGuestByEmail(Guest guest) {
        int searchEmail = 2;

        guest.setCompareCase(searchEmail);

        for (Guest item : list) {
            if (item.equals(guest)) {
                return true;
            }
        }
        if (waitList.size() != 0) {
            for (Guest item : waitList) {
                if (item.equals(guest)) {
                    return true;
                }
            }
        }
        return false;
    }

    //Cauatare in ArrayList  - obiectul primit ca parametru
    public boolean searchGuest(Guest guest) {
        for (Guest item : list) {
            if (item.equals(guest)) {
                return true;
            }
        }

        if (waitList.size() != 0) {
            for (Guest item : waitList) {
                if (item.equals(guest)) {
                    return true;
                }
            }
        }
        return false;
    }

    private Guest checkGuestForList(Guest guest) {
        for (Guest item : list) {
            if (item.equals(guest)) {
                guest = item;
                return guest;
            }
        }
        if (waitList.size() != 0) {
            for (Guest item : waitList) {
                if (item.equals(guest)) {
                    guest = item;
                    return guest;
                }
            }
        }
        return null;
    }

    // Stergere obiect din ArrayList
    public boolean removeGuest(Guest objectForTest) {
        if (waitList.size() == 0) {
            Guest referenceTest = checkGuestForList(objectForTest);
            if (referenceTest != null) {
                list.remove(referenceTest);
                System.out.println("Stergerea persoanei s-a realizat cu succes.");
            }
        } else { // daca waitList.size != 0
            Guest test2 = checkGuestForList(objectForTest);
            if (test2 != null) {
                list.remove(test2);
            }
            if (list.size() < spotAvailableForList) {
                list.add(waitList.get(0));
                System.out.println("Felicitari! Locul tau la eveniment este confirmat. Te asteptam!");
            }
            waitList.remove(0);
            System.out.println("Stergerea persoanei s-a realizat cu succes.");
            return true;
        }
        return false;
    }


    public Guest updateGuestDetails(Guest guest) {
        Guest guestFromArrayList = checkGuestForList(guest);

        if (guestFromArrayList != null) {
            return guestFromArrayList;
        }
        return null;
    }

    public void search (String subString){
        ArrayList<Guest> search = new ArrayList<>();
        search.addAll(list);
        search.addAll(waitList);

        ArrayList<Guest> results = new ArrayList<>();
        for(Guest item : search){
            if(search(item.getLastName(), subString) || search(item.getFirstName(), subString) || search(item.getEmail(), subString)
            || search(item.getPhoneNumber(), subString)){
                results.add(item);
                System.out.println("Contactul: " +  item.getLastName() + " " + item.getFirstName() + " " + item.getEmail() + " " + item.getPhoneNumber());
            }
        }
    }

    private boolean search (String str, String substring) {
        if (str.toLowerCase().contains(substring.toLowerCase())) {
            return true;
        }
        return false;
    }

    public void displayList() {
        int counter = 1;
        if (list.size() == 0) {
            System.out.println("Niciun participant inscris");
        } else {
            for (Guest item : list) {
                System.out.println(counter + ". Nume: " + item.getLastName() + " " + item.getFirstName() +
                        ", Email: " + item.getEmail() + ", " + "Telefon: " + item.getPhoneNumber());
                counter++;
            }
        }
    }

    public void displayWaitList() {
        int counter = 1;
        if (waitList.size() == 0) {
            System.out.println("Lista de asteptare este goala");
        } else {
            for (Guest item : waitList) {
                System.out.println(counter + ". Nume: " + item.getLastName() + " " + item.getFirstName() +
                        ", Email: " + item.getEmail() + ", " + "Telefon: " + item.getPhoneNumber());
                counter++;
            }
        }
    }

    public void guestNumber() {
        System.out.println("Numar de participanti: " + list.size());
    }

    public void waitListNumber() {
        System.out.println("Dimensiunea listei de asteptare: " + waitList.size());
    }

    public void subscribeNumber() {
        System.out.println("Numarul total de persoane inscrise: " + (list.size() + waitList.size()));
    }

    public void available() {
        System.out.println("Numarul de locuri ramase: " + (GuestList.spotAvailableForList - list.size()));
    }
}
