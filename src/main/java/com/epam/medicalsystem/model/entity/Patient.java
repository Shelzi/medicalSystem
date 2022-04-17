package com.epam.medicalsystem.model.entity;

import java.time.LocalDate;

public class Patient {
    private long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private Gender gender;
    private LocalDate birthday;
    private Address address;

    public Patient(long id) {
        this.id = id;
    }


    public Patient(String firstName, String lastName, String middleName, Gender gender,
                   LocalDate birthday, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
    }

    public Patient(String firstName, String lastName, String middleName, Gender gender,
                   LocalDate birthday, String homeTown, String homeAddress, String homeNumber, String apartmentNumber,
                   String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.gender = gender;
        this.birthday = birthday;
        this.address = new Address(homeTown, homeAddress, homeNumber, apartmentNumber, phoneNumber);
    }

    public Patient(long id, String firstName, String lastName, String middleName, Gender gender,
                   LocalDate birthday, String homeTown, String homeAddress, String homeNumber, String apartmentNumber,
                   String phoneNumber) {
        this(firstName, lastName, middleName, gender, birthday, homeTown, homeAddress, homeNumber, apartmentNumber,
                phoneNumber);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public static class Address {
        private String homeTown;
        private String homeAddress;
        private String homeNumber;
        private String apartmentNumber;
        private String phoneNumber;

        public Address(String homeTown, String homeAddress, String homeNumber, String apartmentNumber, String phoneNumber) {
            this.homeTown = homeTown;
            this.homeAddress = homeAddress;
            this.homeNumber = homeNumber;
            this.apartmentNumber = apartmentNumber;
            this.phoneNumber = phoneNumber;
        }


        public String getHomeTown() {
            return homeTown;
        }

        public void setHomeTown(String homeTown) {
            this.homeTown = homeTown;
        }

        public String getHomeAddress() {
            return homeAddress;
        }

        public void setHomeAddress(String homeAddress) {
            this.homeAddress = homeAddress;
        }

        public String getHomeNumber() {
            return homeNumber;
        }

        public void setHomeNumber(String homeNumber) {
            this.homeNumber = homeNumber;
        }

        public String getApartmentNumber() {
            return apartmentNumber;
        }

        public void setApartmentNumber(String apartmentNumber) {
            this.apartmentNumber = apartmentNumber;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        @Override
        public String toString() {
            return "Address{" +
                    "homeTown='" + homeTown + '\'' +
                    ", homeAddress='" + homeAddress + '\'' +
                    ", homeNumber='" + homeNumber + '\'' +
                    ", apartmentNumber='" + apartmentNumber + '\'' +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", address=" + address +
                '}';
    }
}