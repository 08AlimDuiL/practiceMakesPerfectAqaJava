package ru.stqa.ptf.addressbook.model;

import java.util.Objects;

public class ContactData {
    private int id;
    private String name;
    private String middleName;
    private String lastName;
    private String nickName;
    private String company;
    private String address;
    private String homePhoneNumber;
    private String mobilePhoneNumber;
    private String workPhoneNumber;
    private String fax;
    private String email;
    private String birthDay;
    private String birthMonth;
    private String birthYear;
    private String group;

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstName(String name) {
        this.name = name;
        return this;
    }

    public ContactData withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
        return this;
    }

    public ContactData withFax(String fax) {
        this.fax = fax;
        return this;
    }

    public ContactData withWorkPhoneNumber(String workPhoneNumber) {
        this.workPhoneNumber = workPhoneNumber;
        return this;
    }

    public ContactData withMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withBirthDay(String birthDay) {
        this.birthDay = birthDay;
        return this;
    }

    public ContactData withBirthMonth(String birthMonth) {
        this.birthMonth = birthMonth;
        return this;
    }

    public ContactData withBirthYear(String birthYear) {
        this.birthYear = birthYear;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }
    public ContactData() {}

    public ContactData(
            String name,
            String middleName,
            String lastName,
            String nickName,
            String company,
            String address,
            String homePhoneNumber,
            String mobilePhoneNumber,
            String workPhoneNumber,
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
        this.homePhoneNumber = homePhoneNumber;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.workPhoneNumber = workPhoneNumber;
        this.fax = fax;
        this.email = email;
        this.birthDay = birthDay;
        this.birthMonth = birthMonth;
        this.birthYear = birthYear;
        this.group = group;
    }

    public ContactData(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
        this.id = Integer.MAX_VALUE;
        this.middleName = "";
        this.nickName = "";
        this.company = "";
        this.address = "";
        this.homePhoneNumber = "";
        this.mobilePhoneNumber = "";
        this.workPhoneNumber = "";
        this.fax = "";
        this.email = "";
        this.birthDay = "";
        this.birthMonth = "";
        this.birthYear = "";
        this.group = "";
    }

    public ContactData(int id, String firstName, String lastName) {
        this.id = id;
        this.name = firstName;
        this.lastName = lastName;
        this.middleName = "";
        this.nickName = "";
        this.company = "";
        this.address = "";
        this.homePhoneNumber = "";
        this.mobilePhoneNumber = "";
        this.workPhoneNumber = "";
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

        return homePhoneNumber;
    }

    public String getMobilePhoneNumber() {

        return mobilePhoneNumber;
    }

    public String getWorkPhoneNumber() {

        return workPhoneNumber;
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
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName);
    }
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        ContactData that = (ContactData) o;
//        return Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName);
//    }
//
//    @Override
//    public int hashCode() {
//
//        return Objects.hash(name, lastName);
//    }
}