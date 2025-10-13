package ru.stqa.ptf.addressbook.model;

import java.util.Objects;

public class ContactsData {
    private int id;
    private final String name;
    private final String middleName;
    private final String lastName;
    private final String nickName;
    private final String company;
    private final String address;
    private final String home;
    private final String mobile;
    private final String work;
    private final String fax;
    private final String email;
    private final String birthDay;
    private final String birthMonth;
    private final String birthYear;
    private final String group;

    public ContactsData(
            String name,
            String middleName,
            String lastName,
            String nickName,
            String company,
            String address,
            String homePhoneNumber,
            String mobilePhoneNumber,
            String work,
            String fax,
            String email,
            String birthDay,
            String birthMonth,

            String birthYear,
            String group) {
        this.id = Integer.MAX_VALUE;
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.company = company;
        this.address = address;
        this.home = homePhoneNumber;
        this.mobile = mobilePhoneNumber;
        this.work = work;
        this.fax = fax;
        this.email = email;
        this.birthDay = birthDay;
        this.birthMonth = birthMonth;
        this.birthYear = birthYear;
        this.group = group;
    }

    public ContactsData(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
        this.id = Integer.MAX_VALUE;
        this.middleName = "";
        this.nickName = "";
        this.company = "";
        this.address = "";
        this.home = "";
        this.mobile = "";
        this.work = "";
        this.fax = "";
        this.email = "";
        this.birthDay = "";
        this.birthMonth = "";
        this.birthYear = "";
        this.group = "";
    }

    public ContactsData(int id, String firstName, String lastName) {
        this.id = id;
        this.name = firstName;
        this.lastName = lastName;
        this.middleName = "";
        this.nickName = "";
        this.company = "";
        this.address = "";
        this.home = "";
        this.mobile = "";
        this.work = "";
        this.fax = "";
        this.email = "";
        this.birthDay = "";
        this.birthMonth = "";
        this.birthYear = "";
        this.group = "";
    }

    public String getName() {

        return name;
    }

    public String getMiddleName() {

        return middleName;
    }

    public String getLastName() {

        return lastName;
    }

    public String getNickName() {

        return nickName;
    }

    public String getCompany() {

        return company;
    }

    public String getAddress() {

        return address;
    }

    public String getHomePhoneNumber() {

        return home;
    }

    public String getMobilePhoneNumber() {

        return mobile;
    }

    public String getWork() {

        return work;
    }

    public String getFax() {

        return fax;
    }

    public String getEmail() {

        return email;
    }

    public String getBirthDay() {

        return birthDay;
    }

    public String getBirthMonth() {

        return birthMonth;
    }

    public String getBirthYear() {

        return birthYear;
    }

    public String getGroup() {

        return group;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    @Override
    public String toString() {
        return "ContactsData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactsData that = (ContactsData) o;
        return Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName);
    }
}