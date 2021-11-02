package GuestList;

public class Guest {
    private String lastName;
    private String firstName;
    private String email;
    private String phoneNumber;
    private int compareCase;

    public Guest(String lastName, String firstName, String email, String phoneNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int setCompareCase(int compareCase) {
        this.compareCase = compareCase;
        return this.compareCase;
    }

    public int getCompareCase() {
        return this.compareCase;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (this.getClass() != obj.getClass()) {
            return false;
        }

        Guest guest = (Guest) obj;

        switch (guest.compareCase) {
            case 1:
                if (this.lastName.equals(guest.lastName) && this.firstName.equals(guest.firstName)) {
                    return true;
                } else {
                    return false;
                }
            case 2:
                if (this.email.equals(guest.email)) {
                    return true;
                } else {
                    return false;
                }
            case 3:
                if (this.phoneNumber.equals(guest.phoneNumber)) {
                    return true;
                } else {
                    return false;
                }
        }
        return false;
    }


}
